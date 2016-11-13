package ca.qc.bdeb.maveo.modele;

/**
 * Created by WuTchanKi on 2016-09-29.
 */
public class Media {
    private String titre;
    private String pathMedia;
    private String parolesMedia;

    /**
     * Constructeur qui prend en paramètre le titre et le chemin du média
     *
     * @param titre     titre du média
     * @param pathMedia chemin du média
     */
    public Media(String titre, String pathMedia) {
        this.titre = titre;
        this.pathMedia = pathMedia;
    }

    /**
     * Constructeur qui prend en paramètre le titre, le chemin et les paroles du média
     *
     * @param titre        titre du média
     * @param pathMedia    chemin du média
     * @param parolesMedia paroles du média
     */
    public Media(String titre, String pathMedia, String parolesMedia) {
        this.titre = titre;
        this.pathMedia = pathMedia;
        this.parolesMedia = parolesMedia;
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

    public String getParolesMedia() {
        return parolesMedia;
    }

    public void setParolesMedia(String parolesMedia) {
        this.parolesMedia = parolesMedia;
    }
}
