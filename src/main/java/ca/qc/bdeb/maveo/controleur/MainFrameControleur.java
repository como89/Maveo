package ca.qc.bdeb.maveo.controleur;

import ca.qc.bdeb.maveo.modele.Media;
import ca.qc.bdeb.maveo.modele.fichier.FileOpener;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireFactory;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMedia;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireMusique;
import ca.qc.bdeb.maveo.modele.gestionnaires.GestionnaireVideo;
import ca.qc.bdeb.maveo.vue.MainFrame;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import uk.co.caprica.vlcj.binding.LibVlcFactory;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

/**
 * Created by 1379708 on 2016-09-08.
 */
public class MainFrameControleur {

    static boolean isFreeMutexLockSliderVolume = false;

    /**
     * Tableau contenant les indices des images en ordre décroissant (de la plus grande à la plus petite) du JSONArray
     * récupéré dans le fichier JSON obtenu à partir de l'api de Last.fm.
     * Utilisé pour récupèrer le lien de l'image la plus grande.
     */
    private final int[] tabIndicesImagesJson = {4, 3, 2, 1, 0, 5};

    public MainFrameControleur() {
    }

    // vue MainFrame
    MainFrame mainFrame;

    // modele FileOpener
    FileOpener fileOpener;

    LecteurMediaControleur controleurLecteurMedia;

    /**
     * Ajoute la fenêtre principale au contrôleur
     *
     * @param mainFrame la fenêtre principale
     *
     */
    public void ajouterMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.mainFrame.addEventHandlerBtnPlay(new BtnJouerPauseEventHandler());
        this.mainFrame.addEventHandlerBtnStop(new BtnArreterEventHandler());
        this.mainFrame.addEventHandlerOuvrirFichier(new MenuItemOuvrirEventHandler());
        this.mainFrame.addChangeListenerSliderProgression(new SliderPositionChangeListener());
        this.mainFrame.addChangeListenerSliderVolume(new SliderVolumeChangeListener());


        this.mainFrame.getSliderVolume().setValue(this.mainFrame.getSliderVolume().getMax());
        this.mainFrame.getSliderProgression().setDisable(true);
        this.mainFrame.getBtnArreter().setDisable(true);
        this.mainFrame.getBtnJouerPause().setDisable(true);
        this.mainFrame.getBoutonPrecedent().setDisable(true);
        this.mainFrame.getBoutonSuivant().setDisable(true);

        this.mainFrame.addEventHandlerMenuItemMediaSousTitres(new MenuItemMediaSousTitresEventHandler());


    }

    /**
     * Ajoute un FileOpener au contrôleur
     *
     * @param fileOpener le FileOpener à ajouter
     */
    public void ajouterFileOpener(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

    /**
     * Ajoute le controleur d'évènement pour le lecteur média.
     *
     * @param lecteurMediaEventListener - Controleur d'évènement du lecteur média.
     */
    public void ajouterControleurLecteurMedia(LecteurMediaControleur lecteurMediaEventListener) {
        this.controleurLecteurMedia = lecteurMediaEventListener;
    }

    /**
     * Fixe la position du média en cours
     *
     * @param positionPourcentage la nouvelle position, en pourcentage
     */
    void fixerSliderPosition(float positionPourcentage) {
        GestionnaireFactory.getCurrentInstance().setPosition(positionPourcentage);
    }

    /**
     * Fixe le volume pour le média.
     *
     * @param volumePourcentage - Le volume en pourcentage
     */
    void fixerVolumePosition(int volumePourcentage) {
        GestionnaireFactory.getCurrentInstance().setVolume(volumePourcentage);
    }

    Media getMediaFromFile() {
        File file = fileOpener.activerOuvertureMedia(mainFrame.getFenetre());
        Media media = null;
        if (file != null) {
            media = new Media(file.getName(), file.getPath());
        }
        return media;
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'ouverture de fichier
     */
    class MenuItemOuvrirEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {

            Media media = getMediaFromFile();
            if (media != null) {
                isFreeMutexLockSliderVolume = true;
                GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
                if (gestionnaireMedia != null && gestionnaireMedia.enLecture()) {
                    gestionnaireMedia.arreter();
                }
                gestionnaireMedia = GestionnaireFactory.createInstance(media, mainFrame);
                gestionnaireMedia.preparerMedia();


                /**
                 * Si c'est une vidéo, afficher le début et:
                 * Play -> Pause suivi de Pause -> Play pour :
                 * 1. Initialiser certaines variables dans le framework qui permetteront à Maveo d'afficher la vidéo
                 * avec le bon aspect ratio;
                 * 2. Afficher le debut de la vidéo à l'écran avant que l'utilisateur ne fasse jouer.
                 */
                if (gestionnaireMedia instanceof GestionnaireVideo) {

                    gestionnaireMedia.jouerMedia();

                    // Si la vidéo n'est pas en lecture, attendre qu'il se mette en lecture
                    while (!gestionnaireMedia.enLecture()) {
                        // Attendre que la vidéo se mette en lecture
                    }

                    gestionnaireMedia.pause();

                    /**
                     * Attendre que les variables de dimensions dans le framework s'initialisent avant de redimensionner
                     *  la vidéo afin qu'elle respecte son rapport d'aspect.
                     */
                    while (GestionnaireFactory.getComposantVideo().getMediaPlayer().getVideoDimension() == null) {
                        // Attendre que les variables de dimensions dans le framework s'initialisent
                    }

                    // Redimensionne la vidéo afin qu'elle respecte son rapport d'aspect original
                    GestionnaireFactory.getComposantVideo().rafraichirVideo();
                } else if (gestionnaireMedia instanceof GestionnaireMusique) {
                    try {

                        StringBuilder stringBuilder = new StringBuilder("http://ws.audioscrobbler.com/2.0/");
                        stringBuilder.append("?method=album.getinfo");
                        stringBuilder.append("&api_key=");
                        stringBuilder.append("939ac33f69cba097acffdb5b025c0bb5");
                        stringBuilder.append("&artist=" + URLEncoder.encode("Adrian von Ziegler", "UTF-8"));
                        stringBuilder.append("&album=" + URLEncoder.encode("Vagabond", "UTF-8"));
                        stringBuilder.append("&format=json");
                        System.out.println(((GestionnaireMusique) gestionnaireMedia).getTags().getAlbum());
                        System.out.println(stringBuilder.toString());
                        System.out.println(((GestionnaireMusique) gestionnaireMedia).getTags().getArtist());

                        //  URL imageLink = new URL("https://lastfm-img2.akamaized.net/i/u/174s/aa0163bdf3a04ce58f113a874eda9a58.png");
                        //  Image image = SwingFXUtils.toFXImage(ImageIO.read(imageLink), null);

                        JSONObject jsonObjectLien = readJsonFromUrl(stringBuilder.toString());
                        JSONObject albumJsonObject = (JSONObject) jsonObjectLien.get("album");
                        JSONArray jsonArrayImage = (JSONArray) albumJsonObject.get("image");
                        Image albumArt = obtenirLaPlusGrandeImageAPartirDeJsonArray(jsonArrayImage);

                        mainFrame.getLblNomMedia().setGraphic(new ImageView(albumArt));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                gestionnaireMedia.addMediaPlayerEventListener(controleurLecteurMedia);
                mainFrame.getBtnJouerPause().setDisable(false);
                mainFrame.getSliderProgression().setDisable(false);
                mainFrame.getTraitProgress().setDisable(false);
            }
        }
    }


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    /**
     * Reçoit lien qui pointe vers une page avec du texte JSON en paramètre et retourne un objet JSON à partir de ce
     * lien
     *
     * @param url le lien qui doit contenir une page avec du texte JSON.
     * @return objet JSON à partir ce de ce lien
     */
    public static JSONObject readJsonFromUrl(String url) {
        InputStream is = null;
        JSONObject jsonObject = null;
        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(jsonText);

        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     * Retourne l'image la plus grande ju JSONArray reçu en paramètre
     *
     * @param jsonArrayImages le JSONArray qui contient des liens vers les images
     * @return l'image la plus grande trouvée (si c'st le cas)
     */
    public Image obtenirLaPlusGrandeImageAPartirDeJsonArray(JSONArray jsonArrayImages) {
        URL urlImage = null;
        Image image = null;
        try {
            boolean imageTrouvee = false;
            String lienImageTmp = "";
            JSONObject itemJsonArrayTmp;
            for (int i = 0; i < tabIndicesImagesJson.length && !imageTrouvee; i++) {
                itemJsonArrayTmp = (JSONObject) jsonArrayImages.get(tabIndicesImagesJson[i]);
                lienImageTmp = (String) itemJsonArrayTmp.get("#text");
                if (!lienImageTmp.equals("")) {
                    urlImage = new URL(lienImageTmp);
                    image = SwingFXUtils.toFXImage(ImageIO.read(urlImage), null);
                    imageTrouvee = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }


    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton de Jouer/Pause
     */
    class BtnJouerPauseEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
            if (gestionnaireMedia.enLecture()) { // En lecture -> pause
                gestionnaireMedia.pause();
                mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPause");
                mainFrame.getBtnJouerPause().getStyleClass().add("buttonPlay");
            } else { // En pause -> lecture
                gestionnaireMedia.jouerMedia();
                mainFrame.getBtnArreter().setDisable(false);
                mainFrame.getBtnJouerPause().getStyleClass().remove("buttonPlay");
                mainFrame.getBtnJouerPause().getStyleClass().add("buttonPause");
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur appuie sur le bouton d'arrêt
     */
    class BtnArreterEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            GestionnaireMedia gestionnaireMedia = GestionnaireFactory.getCurrentInstance();
            gestionnaireMedia.arreter();
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur change le slider de progression
     */
    class SliderPositionChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            if (controleurLecteurMedia.isFreeMutexLockSliderPosition) {
                float position = newValue.floatValue();
                float diviseur = 100;
                fixerSliderPosition(position / diviseur);
                mainFrame.getTraitProgress().setProgress(position / diviseur
                        + LecteurMediaControleur.PROGRESS_BAR_POSITION_CORRECTION);
            }
        }
    }

    /**
     * Déclencheur qui s'active lorsque l'utilisateur change le slider de volume
     */
    class SliderVolumeChangeListener implements ChangeListener<Number> {
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            int volumePourcentage = newValue.intValue();
            if (isFreeMutexLockSliderVolume) {
                isFreeMutexLockSliderVolume = false;
                fixerVolumePosition(volumePourcentage);
                isFreeMutexLockSliderVolume = true;
            }
            mainFrame.getProgressVolume().setProgress(volumePourcentage / 100.0);
        }
    }


    class MenuItemMediaSousTitresEventHandler implements EventHandler<ActionEvent> {

        public void handle(ActionEvent event) {
            // Si video
            if(GestionnaireFactory.getCurrentInstance() instanceof GestionnaireVideo){
                LibVlcFactory factory = LibVlcFactory.factory();

            }


        }
    }
}
