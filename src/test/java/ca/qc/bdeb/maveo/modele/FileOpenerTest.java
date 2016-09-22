package ca.qc.bdeb.maveo.modele;

import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by nicholas on 21/09/16.
 */
public class FileOpenerTest {

    @Test
    public void testActiverOuvertureFichier() throws ExecutionException, InterruptedException {
        final CompletableFuture<File> future = new CompletableFuture<File>();
        PlatformImpl.startup(new Runnable() {
            @Override
            public void run() {
                FileOpener fileOpener = new FileOpener();
                File file = fileOpener.activerOuvertureFichier(null);
                future.complete(file);
            }
        });
        Assert.assertNotNull(future.get());
    }
}