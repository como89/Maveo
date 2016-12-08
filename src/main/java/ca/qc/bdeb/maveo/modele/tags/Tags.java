package ca.qc.bdeb.maveo.modele.tags;

/**
 * Created by nicholas on 15/11/16.
 */
public class Tags {
    String title;
    String artist;
    String album;

    /**
     * Constructeur pour créer un tags avec un titre et un artiste.
     * @param title - Le titre de la musique
     * @param artist - L'artiste de la musique
     */
    public Tags(String title,String artist, String album) {
        this.title = title;
        this.artist = artist;
        this.album = album;
    }

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

    public String getAlbum() {
        return album;
    }

    public boolean hasTitle() {
        return title != null;
    }

    public boolean hasArtist() {
        return artist != null;
    }

    public boolean hasAlbum() {
        return album != null;
    }

}
