package ca.qc.bdeb.maveo.modele.tags;

/**
 * Created by nicholas on 15/11/16.
 */
public class Tags {
    String title;
    String artist;

    /**
     * Constructeur pour créer un tags avec un titre et un artiste.
     * @param title - Le titre de la musique
     * @param artist - L'artiste de la musique
     */
    public Tags(String title,String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}
