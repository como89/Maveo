package ca.qc.bdeb.maveo.vue;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

/**
 * Created by Cedric Wu Tchan Ki on 2016-10-06.
 */
public class GuiPlaylist {
    @FXML
    ListView listViewPlaylist;

    @FXML
    Pane panelPlaylist;

    public GuiPlaylist() {

    }

    public Window getFenetre() {
        return panelPlaylist.getScene().getWindow();
    }


}
