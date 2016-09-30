package ca.qc.bdeb.maveo.modele;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by WuTchanKi on 2016-09-29.
 */
public class Playlist implements Serializable {
    private String titrePlaylist;
    private int idPlaylist;
    private static ArrayList<Chanson> listeChanson;

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

    public static ArrayList<Chanson> getListeChanson() {
        return listeChanson;
    }

    public static void setListeChanson(ArrayList<Chanson> listeChanson) {
        Playlist.listeChanson = listeChanson;
    }

    public void ajouterChansonAListe(Chanson chanson){
        listeChanson.add(chanson);
    }

    public void retirerChansonAListe(Chanson chanson){
        listeChanson.remove(chanson);
    }

    public Playlist(String nom, int id){
        this.titrePlaylist = nom;
        this.idPlaylist = id;
        this.listeChanson = new ArrayList<Chanson>();



    }
}
