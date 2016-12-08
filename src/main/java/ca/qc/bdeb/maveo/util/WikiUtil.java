package ca.qc.bdeb.maveo.util;

import javafx.application.HostServices;

/**
 * Created by nicholas on 06/12/16.
 */
public class WikiUtil {

    private static final String WIKI_SEARCH_LINK = "https://fr.wikipedia.org/w/index.php?search=";

    private HostServices hostServices;
    StringBuilder searchContent;

    /**
     * Constructeur pour l'utilitaire du wiki.
     */
    public WikiUtil (HostServices hostServices) {
        this.hostServices = hostServices;
        searchContent = new StringBuilder();
    }

    /**
     * Méthode qui permet d'ajouter un élément de recherche à la requête.
     * @param content - l'élément de recherche.
     */
    public void addSearchContent(String content) {
        searchContent.append(content);
    }

    /**
     * Méthode qui ouvre la page de wiki à partir des éléments de recherche.
     * @return retourne true, s'il a des éléments de recherche.
     */
    public boolean openWikiPage() {
        boolean hasContent = hasContent();
        if(hasContent) {
            hostServices.showDocument(WIKI_SEARCH_LINK + searchContent.toString());
        }
        return hasContent;
    }

    /**
     * Méthode qui permet de vérifier s'il a des éléments de recherche.
     * @return true, s'il contient des éléments.
     */
    boolean hasContent() {
        return searchContent.length() > 0;
    }
}
