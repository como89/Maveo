package ca.qc.bdeb.maveo.vue;

import com.sun.jna.Memory;
import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DefaultDirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

import java.nio.ByteBuffer;

/**
 * Created by nicholas on 27/09/16.
 * Le code pour la génération de la vidéo provient de ce site :
 * https://github.com/caprica/vlcj-javafx/blob/master/src/test/java/uk/co/caprica/vlcj/javafx/test/resize/ResizableJavaFXPlayerTest.java
 * mais a été adapté pour le projet.
 */
public class ComposantVideo extends DirectMediaPlayerComponent {

    static final VideoBufferedCallBack videoBufferCallBack = new VideoBufferedCallBack();
    static final FloatProperty propertyFloat = new SimpleFloatProperty(0.4f);

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
        propertyFloat.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                fitImageWithSize((long) videoPane.getWidth(), (long) videoPane.getHeight());
            }
        });
    }

    public ImageView getVideoView() {
        return videoView;
    }

    public void clearPixelWriter() {

    }

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

    void fitImageWithSize(float newPaneWidth, float newPaneHeight) {
        Bounds videoBounds = videoBufferCallBack.videoPane.getLayoutBounds();
        DefaultDirectMediaPlayer defaultDirectMediaPlayer = (DefaultDirectMediaPlayer) this.getMediaPlayer();
        BufferFormat bufferFormat = defaultDirectMediaPlayer.getBufferFormat();


        videoView.setFitHeight(newPaneHeight);
        videoView.setFitWidth(newPaneWidth);

        double fitWidth = videoView.getFitWidth();

        float widthEmpty = (float) (newPaneWidth - videoView.getFitWidth());
        float heightEmpty = (float) (newPaneHeight - videoView.getFitHeight());

        float xPosition = widthEmpty / 2;
        float yPosition = heightEmpty / 2;

        videoView.setX(xPosition);
        videoView.setY(yPosition);
    }

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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ComposantVideo.propertyFloat.set((float) height / (float) width);
                }
            });
            return new RV32BufferFormat((int) videoPane.getWidth(), (int) videoPane.getHeight());
        }
    }
}

