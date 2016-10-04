package ca.qc.bdeb.maveo.modele;

/**
 * Created by WuTchanKi on 2016-09-29.
 */
public class Media {
    private String titre;
    private String pathMedia;


    public Media(String titre, String pathMedia){
        this.titre = titre;
        this.pathMedia = pathMedia;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getPathMedia() {
        return pathMedia;
    }

    public void setPathMedia(String pathMedia) {
        this.pathMedia = pathMedia;
    }
}
