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

    public ComposantVideo(final Pane videoPane) {
        super(videoBufferCallBack);

        pixelFormat = PixelFormat.getByteBgraPreInstance();
        writableImage = new WritableImage((int) videoPane.getWidth(), (int) videoPane.getHeight());
        videoView = new ImageView(writableImage);
        videoView.setSmooth(true);
        // videoView.setPreserveRatio(true);
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
     * Méthode qui permet d'obtenir la view de la vidéo.
     *
     * @return la view de la vidéo.
     */
    public ImageView getVideoView() {
        return videoView;
    }

    /**
     * Méthode qui permet de vider les pixels affichés.
     * TODO : Méthode à refaire.
     */
    public void clearPixelWriter() {
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
                    ComposantVideo.this.getPixWriter().setPixels(0, 0, bufferFormat.getWidth(),
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
     *                      TODO : Pour l'instant, la vidéo s'adapte à la fenêtre au complet. Mais plus tard, on devra calculer le ratio
     *                      TODO      pour bien afficher la vidéo.
     */
    void fitImageWithSize(float newPaneWidth, float newPaneHeight) {
        // Bounds videoBounds = videoBufferCallBack.videoPane.getLayoutBounds();
        DefaultDirectMediaPlayer defaultDirectMediaPlayer = (DefaultDirectMediaPlayer) this.getMediaPlayer();
        //  BufferFormat bufferFormat = defaultDirectMediaPlayer.getBufferFormat();

        float rapport = (float) calculerRapport(defaultDirectMediaPlayer.getVideoDimension());

        float widthEmpty = 0;
        float heightEmpty = 0;

        /**
         * Ce bloc if()..else if() fixe la taille de l'affichage vidéo pour respecter le rapport d'aspect
         * original de la vidéo.
         */
        if (newPaneWidth > newPaneHeight) {
            widthEmpty = newPaneWidth - rapport * newPaneHeight;
            newPaneWidth = rapport * newPaneHeight;
        } else if (newPaneHeight > newPaneWidth) {
            heightEmpty = newPaneHeight - newPaneWidth / rapport;
            newPaneHeight = newPaneWidth / rapport;
        }

        videoView.setFitHeight(newPaneHeight);
        videoView.setFitWidth(newPaneWidth);

        float xPosition = widthEmpty / 2;
        float yPosition = heightEmpty / 2;

        videoView.setX(xPosition);
        videoView.setY(yPosition);
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

