package ca.qc.bdeb.maveo.modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by WuTchanKi on 2016-09-29.
 */
public class Playlist implements Serializable {
    private String titrePlaylist;
    private int idPlaylist;
    private static ArrayList<Media> listeMedia;

    public String getTitrePlaylist() {
        return titrePlaylist;
    }

    public void setTitrePlaylist(String titrePlaylist) {
        this.titrePlaylist = titrePlaylist;
    }

    public int getIdPlaylist() {
        return idPlaylist;
    }

    public void setIdPlaylist(int idPlaylist) {
        this.idPlaylist = idPlaylist;
    }

    public static ArrayList<Media> getListeMedia() {
        return listeMedia;
    }

    public static void setListeMedia(ArrayList<Media> listeMedia) {
        Playlist.listeMedia = listeMedia;
    }

    public void ajouterChansonAListe(Media media){
        listeMedia.add(media);
    }

    public void retirerChansonAListe(Media media){
        listeMedia.remove(media);
    }

    public int recupererLongueurListe(){
        return listeMedia.size();
    }

    public Playlist(String nom, int id){
        this.titrePlaylist = nom;
        this.idPlaylist = id;
        this.listeMedia = new ArrayList<Media>();



    }
}
