package ca.qc.bdeb.maveo.vue;

import com.sun.jna.Memory;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DefaultDirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

import java.awt.*;
import java.nio.ByteBuffer;

/**
 * Created by nicholas on 27/09/16.
 * Le code pour la génération de la vidéo provient de ce site :
 * https://github.com/caprica/vlcj-javafx/blob/master/src/test/java/uk/co/caprica/vlcj/javafx/test/resize/ResizableJavaFXPlayerTest.java
 * mais a été adapté pour le projet.
 */
public class ComposantVideo extends DirectMediaPlayerComponent {

    static final VideoBufferedCallBack videoBufferCallBack = new VideoBufferedCallBack();

    PixelWriter pixWriter;
    WritableImage writableImage;
    ImageView videoView; // L'endroit dans lequel la vidéo s'affiche

    private WritablePixelFormat<ByteBuffer> pixelFormat;

    public ComposantVideo(Pane videoPane, ImageView videoView) {
        super(videoBufferCallBack);

        this.videoView = videoView;
        pixelFormat = PixelFormat.getByteBgraPreInstance();
        writableImage = new WritableImage((int) videoPane.getWidth(), (int) videoPane.getHeight());
        this.videoView.setImage(writableImage);
        this.videoView.setSmooth(true);
        videoBufferCallBack.videoPane = videoPane;

        videoPane.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                fitImageWithSize(newValue.floatValue(), (float) videoPane.getHeight());
            }
        });
        videoPane.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                fitImageWithSize((float) videoPane.getWidth(), newValue.floatValue());
            }
        });

    }

    /**
     * Méthode qui affiche à l'écran la vidéo pixel par pixel.
     *
     * @param mediaPlayer   - Le DirectMédiaPLayer.
     * @param nativeBuffers - La mémoire du buffer.
     * @param bufferFormat  - Le buffer format pour la résolution de la vidéo.
     */
    @Override
    public void display(final DirectMediaPlayer mediaPlayer, final Memory[] nativeBuffers, final BufferFormat bufferFormat) {
        Platform.runLater(new Runnable() {

            @Override
            public void run() {
                try {
                    Memory buffMem = mediaPlayer.lock()[0];
                    ByteBuffer bytebuff = buffMem.getByteBuffer(0, buffMem.size());
                    getPixWriter().setPixels(0, 0, bufferFormat.getWidth(),
                            bufferFormat.getHeight(), pixelFormat, bytebuff, bufferFormat.getPitches()[0]);
                } catch (Exception e) {
                } finally {
                    if (mediaPlayer != null) {
                        mediaPlayer.unlock();
                    }
                }
            }
        });
    }

    /**
     * Cette méthode permet de fit l'image de la vidéo selon la grandeur de la fenêtre.
     *
     * @param newPaneWidth  - La nouvelle largeur de la fenêtre.
     * @param newPaneHeight - La nouvelle hauteur de la fenêtre.
     */
    void fitImageWithSize(float newPaneWidth, float newPaneHeight) {
        DefaultDirectMediaPlayer defaultDirectMediaPlayer = (DefaultDirectMediaPlayer) this.getMediaPlayer();

        Dimension videoResolution = defaultDirectMediaPlayer.getVideoDimension();

        if (videoResolution != null) {

            float rapportVideo = (float) calculerRapport(videoResolution);
            float rapportPane = (float) calculerRapport(new Dimension((int) newPaneWidth, (int) newPaneHeight));

            float widthEmpty = 0;
            float heightEmpty = 0;

            /**
             * Ce bloc if()..else if() fixe la taille de l'affichage vidéo pour :
             * 1. Respecter le rapport d'aspect original de la vidéo
             * 2. Encadrer la vidéo entièrement dans la fenêtre, sans la dépasser, et sans mettre des espacements inutiles
             * 3. Poser la vidéo au centre du pane.
             *
             * Gére les cas suivants:
             * 1. La vidéo doit s'afficher avec deux bandes verticales (rapport pane > rapport vidéo)
             * 2. La vidéo doit s'afficher avec deux bandes horizontales (rapport vidéo > rapprot pane)
             * 3. La vidéo doit s'afficher sans bandes horizontales ni verticales. Dans le cas où que les rapprots
             * sont égaux, les bandes sont nulles, les espacements sont nuls, et la vidéo rentre entièrement dans le
             * pane sans modification. Il n'y a donc pas de traitement à faire.
             */
            if (rapportPane > rapportVideo) { // Si la vidéo doit s'afficher avec deux bandes verticales
                widthEmpty = newPaneWidth - rapportVideo * newPaneHeight;
                newPaneWidth = rapportVideo * newPaneHeight;
            } else if (rapportVideo > rapportPane) { // Si la vidéo doit s'afficher avec deux bandes horizontales
                heightEmpty = newPaneHeight - newPaneWidth / rapportVideo;
                newPaneHeight = newPaneWidth / rapportVideo;
            }


            videoView.setFitHeight(newPaneHeight);
            videoView.setFitWidth(newPaneWidth);

            float xPosition = widthEmpty / 2;
            float yPosition = heightEmpty / 2;

            videoView.setX(xPosition);
            videoView.setY(yPosition);
        }
    }

    /**
     * Calcule le rapport largeur sur hauteur avec la dimension passée en paramètre
     *
     * @param dimension la dimension à partir de laquelle il calcule le rapport
     * @return le rapport (largeur / hauteur)
     */
    private double calculerRapport(Dimension dimension) {
        return dimension.getWidth() / dimension.getHeight();
    }

    /**
     * Rafraîchit la vidéo : recalcule les dimensions que la vidéo devrait avoir selon le rapport
     */
    public void rafraichirVideo() {
        fitImageWithSize((float) videoBufferCallBack.videoPane.getWidth(),
                (float) videoBufferCallBack.videoPane.getHeight());
    }


    /**
     * Méthode pour obtenir le pixelWriter afin d'afficher les pixels
     *
     * @return un pixel writer.
     */
    PixelWriter getPixWriter() {
        if (pixWriter == null) {
            pixWriter = writableImage.getPixelWriter();
        }
        return pixWriter;
    }


    static class VideoBufferedCallBack implements BufferFormatCallback {

        Pane videoPane;

        @Override
        public BufferFormat getBufferFormat(final int width, final int height) {
            return new RV32BufferFormat((int) videoPane.getWidth(), (int) videoPane.getHeight());
        }
    }
}

