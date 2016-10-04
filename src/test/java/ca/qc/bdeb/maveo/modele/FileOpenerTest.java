package ca.qc.bdeb.maveo.modele;

import org.junit.Test;
import java.util.concurrent.ExecutionException;

// import java.util.concurrent.CompletableFuture;


/**
 * Created by nicholas on 21/09/16.
 */
public class FileOpenerTest {

    @Test
    public void testActiverOuvertureFichier() throws ExecutionException, InterruptedException {
        // ERROR: Usage of API documented as @since 1.7+

      /*  final CompletableFuture<File> future = new CompletableFuture<File>();
        PlatformImpl.startup(new Runnable() {
            @Override
            public void run() {
                FileOpener fileOpener = new FileOpener();
                File file = fileOpener.activerOuvertureFichier(null);
                future.complete(file);
            }
        });
        Assert.assertNotNull(future.get());
       */
    }
}