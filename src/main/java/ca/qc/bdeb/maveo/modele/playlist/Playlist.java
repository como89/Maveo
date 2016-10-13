package ca.qc.bdeb.maveo.modele.playlist;

import ca.qc.bdeb.maveo.modele.Media;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by WuTchanKi on 2016-09-29.
 */
public class Playlist implements Serializable {

    private String titrePlaylist;
    private ArrayList<Media> listeMedia;

    /**
     * Constructeur par défaut ne nécessitant aucun paramètre
     */
    public Playlist() {
    }

    /**
     * Constructeur nécessitant tous les paramètres
     *
     * @param nom le nom de la palylist
     */
    public Playlist(String nom) {
        this.titrePlaylist = nom;
        this.listeMedia = new ArrayList<Media>();
    }

    public String getTitrePlaylist() {
        return titrePlaylist;
    }

    public void setTitrePlaylist(String titrePlaylist) {
        this.titrePlaylist = titrePlaylist;
    }

    public void ajouterMediaListe(Media media) {
        listeMedia.add(media);
    }

    public void retirerMediaListe(Media media) {
        listeMedia.remove(media);
    }

    public int recupererLongueurListe() {
        return listeMedia.size();
    }

    public Media getMediaByName(String name) {
        Media mediafound = null;
        for (Media media : listeMedia) {
            if (media.getTitre().equals(name)) {
                mediafound = media;
            }
        }
        return mediafound;
    }

    public ArrayList<Media> getListeMedia() {
        return listeMedia;
    }


}
