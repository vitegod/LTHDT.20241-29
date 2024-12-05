package MainMenu;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.fxml.Initializable;
import javafx.collections.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {

    @FXML
    private ComboBox<String> chooseBox;

    @FXML
    private Button helpButton;

    @FXML
    private Label mainTitle;

    @FXML
    private Button quitButton;

    @Override 
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	ObservableList<String> list = FXCollections.observableArrayList("Quick sort", "Insertion Sort", "Bubble sort");
    	chooseBox.setItems(list);
    }
}
