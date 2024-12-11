package CreateArrayUIStage1;

import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import CreateArrayUIStage2.*;

public class ChooseOptionController implements Initializable {
	@FXML
	private ListView<String> instructionList;
	
	@FXML
	private Button optionEnter;
	
	@FXML
	private Button optionCreate;
	
	private String selectedAlgorithm;
	
	public ChooseOptionController(String option) {
		this.selectedAlgorithm = option;
	}
	
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {	
    	// Initialize for the instruction list
    	String[] instruction = {"1. You must choose to enter each element or create the array automatically",
    			                "2. If you want to enter each element, first, choose the number of elements",
    			                "3. Then, enter each element, add space between them (like: 2 3 4 5 6)",
    			                "4. Then, choose the position of pivot element (for quick sort only)",
    			                "5. Click 'Finish' to move on, or 'Return' to back to main menu",
    			                "6. If you choose create the array automatically, ignore step 2, 3, 4",
    			                "7. The system will show the array created automatically, go to step 5"};
    	instructionList.getItems().addAll(instruction);
    }
    
    @FXML
    public void handleEnterEachElement() {
    	try {
            // Download the EnterEachElement view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateArrayUIStage2/EnterEachElementView.fxml"));
            loader.setControllerFactory(param -> new EnterEachElementController(this.selectedAlgorithm));
            
            Parent root = loader.load();
            
            // Build a new scene
            Scene createArrayScene = new Scene(root);
            
            // Get the current stage
            Stage stage = (Stage) optionEnter.getScene().getWindow(); // Use chooseBox to get the stage
            
            // Move to the new scene
            stage.setScene(createArrayScene);
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void handleCreateArrayAutomatically() {
    	try {
            // Download the CreateAutomatically view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CreateArrayUIStage2/CreateAutomaticallyView.fxml"));
            loader.setControllerFactory(param -> new CreateAutomaticallyController(this.selectedAlgorithm));
            
            Parent root = loader.load();
            
            // Build a new scene
            Scene createArrayScene = new Scene(root);
            
            // Get the current stage
            Stage stage = (Stage) optionCreate.getScene().getWindow(); // Use chooseBox to get the stage
            
            // Move to the new scene
            stage.setScene(createArrayScene);
            
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
