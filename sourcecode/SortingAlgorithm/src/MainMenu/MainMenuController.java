package MainMenu;

import javafx.fxml.*;
import CreateArrayUIStage1.*;
import javafx.scene.control.*;
import javafx.collections.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.*;
import javafx.stage.Stage;

public class MainMenuController implements Initializable {
    @FXML
    private ComboBox<String> chooseBox;
    
    @FXML
    private Button quitButton;
    
    @FXML
    private Button startButton;
    
    @FXML
    private Label showMessage;
    
    private String selectedAlgorithm;
    
    @FXML
    public void showUserOption() {
    	String s = chooseBox.getSelectionModel().getSelectedItem().toLowerCase();
    	showMessage.setText("Click \"Start\" to execute " + s + " algorithm!");
    }
    
    @FXML
    public void handleHelpMenu() {
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
    	alert.setTitle("Help");
    	alert.setHeaderText("Introduction and Instruction");
    	String content = """
    			1. About Sorting Visualizer: We provide the executing process for three sorting algorithms: quick sort, insertion sort and bubble sort.
    			
    			2. Instruction:
    			- Choose one sorting algorithm to execute
    			- Click 'Start' to start the visualization
    			- Click 'Quit' to quit the application
    			
    			3. During the execution:
    			- Please choose the number of elements (it should be between 5 and 10)
    			- Please choose to enter the array or create it automatically
    			- If you choose to enter the array, enter each element of it
    			- Click 'Start' to show the visualization 
    			
    			""";
    	TextArea textArea = new TextArea(content);
    	textArea.setEditable(false);
    	textArea.setWrapText(true);
    	textArea.setPrefSize(600, 200);
    	alert.getDialogPane().setContent(textArea);
    	alert.showAndWait();
    }
    
    @FXML
    public void handleQuit() {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle("Exit confirmation");
    	alert.setHeaderText("Are you sure to quit?");
    	alert.showAndWait().ifPresent(response -> {
    		if (response == ButtonType.OK) {
    	          Stage stage = (Stage) quitButton.getScene().getWindow();
    	          stage.close();
    	    }
        });
    }
    
    @FXML
    public void handleStartButton() {
    	this.selectedAlgorithm = chooseBox.getValue();
        if(selectedAlgorithm == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning!");
            alert.setHeaderText(null);
            alert.setContentText("You haven't chosen any algorithm. Please choose an algorithm to start");
            alert.showAndWait();
        } else {
            try {
                // Download the ChooseOption view
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateArrayUIStage1/ChooseOption.fxml"));
                loader.setControllerFactory(param -> new ChooseOptionController(selectedAlgorithm));
                
                Parent root = loader.load();
                
                // Build a new scene
                Scene newScene = new Scene(root);
                
                // Get the current stage
                Stage stage = (Stage) chooseBox.getScene().getWindow(); // Use chooseBox to get the stage
                
                // Move to the new scene
                stage.setScene(newScene);
                
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    
    @Override 
    public void initialize(URL url, ResourceBundle resourceBundle) {
    	ObservableList<String> list = FXCollections.observableArrayList("Quick sort", "Insertion sort", "Bubble sort");
    	chooseBox.setItems(list);
    }
}
