package ca.qc.bdeb.maveo.util;

import javafx.application.HostServices;

/**
 * Created by nicholas on 06/12/16.
 */
public class WikiUtil {

    private static final String WIKI_SEARCH_LINK = "https://fr.wikipedia.org/w/index.php?search=";

    private HostServices hostServices;
    StringBuilder searchContent;

    public WikiUtil (HostServices hostServices) {
        this.hostServices = hostServices;
        searchContent = new StringBuilder();
    }

    public void addSearchContent(String content) {
        searchContent.append(content);
    }

    public boolean openWikiPage() {
        boolean hasContent = hasContent();
        System.out.println(searchContent.toString());
        if(hasContent) {
            hostServices.showDocument(WIKI_SEARCH_LINK + searchContent.toString());
        }
        return hasContent;
    }

    boolean hasContent() {
        return searchContent.length() > 0;
    }
}
