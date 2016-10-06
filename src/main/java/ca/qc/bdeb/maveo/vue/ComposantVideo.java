package ca.qc.bdeb.maveo.vue;

import com.sun.jna.Memory;
import javafx.application.Platform;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
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

    static final VideoBufferCallBack videoBufferCallBack = new VideoBufferCallBack();
    static final FloatProperty propertyFloat = new SimpleFloatProperty(2.0f);

    private PixelWriter pixWriter;
    private WritableImage writableImage;
    private ImageView imageView;

    private WritablePixelFormat<ByteBuffer> pixelFormat;

    public ComposantVideo() {
        super(videoBufferCallBack);
        pixelFormat = PixelFormat.getByteBgraPreInstance();
    }

    public void setVideoPane(final Pane videoPane) {
            writableImage = new WritableImage((int) videoPane.getWidth(), (int) videoPane.getHeight());
            imageView = new ImageView(writableImage);
        videoBufferCallBack.videoPane = videoPane;
        videoPane.getChildren().clear();
            videoPane.getChildren().add(imageView);
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

    private void fitImageWithSize(final float width, final float height) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                float fitHeight = propertyFloat.get() * width;
                if (fitHeight > height) {
                    imageView.setFitHeight(fitHeight - 200);
                    double fitWidth = height / propertyFloat.get();
                    imageView.setFitWidth(fitWidth + 333);
                    imageView.setX(0);
                    imageView.setY(0);
                } else {
                    imageView.setFitWidth(width);
                    imageView.setFitHeight(fitHeight + 30);
                    imageView.setY(0);
                    imageView.setX(0);
                }
            }
        });
    }

    PixelWriter getPixWriter() {
        if (pixWriter == null) {
            pixWriter = writableImage.getPixelWriter();
        }
        return pixWriter;
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
}

class VideoBufferCallBack implements BufferFormatCallback {

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
