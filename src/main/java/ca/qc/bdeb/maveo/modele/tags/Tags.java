package ca.qc.bdeb.maveo.modele.tags;

/**
 * Created by nicholas on 15/11/16.
 */
public class Tags {
    String title;
    String artist;

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
