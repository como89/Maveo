package ca.qc.bdeb.maveo.modele.tags;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.farng.mp3.id3.AbstractID3;

import java.io.File;
import java.io.IOException;

/**
 * Created by nicholas on 15/11/16.
 */
public class TagsIO {

    /**
     * Constructeur par défaut qui ne prend pas d'arguments.
     */
    public TagsIO() {}

    /**
     * Méthode qui permet de récupérer les tags du fichier.
     * @param pathMedia - Le path du fichier média.
     * @return retourne les tags du fichier.
     */
    public Tags getTagsFromMedia(String pathMedia) {
        Tags tags = null;
        File file = new File(pathMedia);
        if(file.exists()) {
            try {
                MP3File mp3File = new MP3File(file);
                AbstractID3 id3 = null;
                if (mp3File.hasID3v1Tag()) {
                    id3 = mp3File.getID3v1Tag();
                } else if (mp3File.hasID3v2Tag()) {
                    id3 = mp3File.getID3v2Tag();
                }

                if (id3 != null) {
                    String title = id3.getSongTitle();
                    String artist = id3.getLeadArtist();

                    tags = new Tags(title, artist);
                    System.out.println("--------------------TITLE + ARTIST : " + title + " " + artist);
                }
                System.out.println(id3);
            } catch (IOException | TagException e) {
            }
        }
        return tags;
    }
}
