package ca.qc.bdeb.maveo.modele.gestionnaires;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.fichier.AccesExtensions;
import ca.qc.bdeb.maveo.vue.ComposantVideo;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.stage.FileChooser;
import com.google.common.io.Files;


/**
 * Created by nicholas on 12/10/16.
 */
public class GestionnaireFactory {

    static GestionnaireMedia gestionnaireMedia;
    static ComposantVideo composantVideo;

    public static GestionnaireMedia createInstance(Media media, MainFrame mainFrame) {
        AccesExtensions accesExtensions = new AccesExtensions();
        String extensionFichier = Files.getFileExtension(media.getPathMedia());

        // Cherche les extensions vidéo
        FileChooser.ExtensionFilter extensionFilter = accesExtensions.getListeFiltresMedia()
                .get(accesExtensions.INDICE_LISTE_FILTRES_MEDIA_FICHIERS_VIDEO);

        extensionFichier = "*." + extensionFichier;

        // Si vidéo
        if (extensionFilter.getExtensions().contains(extensionFichier)) {
            composantVideo = new ComposantVideo(mainFrame.getPaneEcran(),mainFrame.getVideoView());
            mainFrame.switchView(MainFrame.VIDEO_VIEW);
            gestionnaireMedia = new GestionnaireVideo(composantVideo);
        } else {
            mainFrame.switchView(MainFrame.MUSIC_VIEW);
            gestionnaireMedia = new GestionnaireMusique();
        }
        gestionnaireMedia.setCheminFichier(media.getPathMedia());
        return gestionnaireMedia;
    }

    public static GestionnaireMedia getCurrentInstance() {
        return gestionnaireMedia;
    }

    public static ComposantVideo getComposantVideo() {
        return composantVideo;
    }
}
