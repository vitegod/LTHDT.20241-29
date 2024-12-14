package CreateArrayUIStage2;

import java.io.IOException;
import java.util.Optional;
import VisualizationUI.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public abstract class CreateArrayController {
	@FXML
	protected Button finishButton;
	
	@FXML
	protected Button returnButton;
	
    protected String selectedAlgorithm;
	
	protected ArrayModel myArray = new ArrayModel();
	
	public abstract void setupMyArray();
	
	public void returnToMainMenu() {
	    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Confirm");
	    alert.setHeaderText("Return to main menu");
	    alert.setContentText("Are you sure to return? All changes will be lost!");

	    Optional<ButtonType> result = alert.showAndWait();

	    if (result.isPresent() && result.get() == ButtonType.OK) {
	        try {
	            Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuView.fxml"));
	            Scene mainMenuScene = new Scene(mainMenuRoot);
	            Stage stage = (Stage) returnButton.getScene().getWindow();
	            stage.setScene(mainMenuScene);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public SortingAlgorithm createAlgorithm(String selectedAlgorithm, ArrayModel array) {
        switch(selectedAlgorithm) {
            case "Insertion sort":
                return new InsertionSort(array);
            case "Bubble sort":
            	return new BubbleSort(array);
            case "Quick sort":
            	return new QuickSort(array);
            default:
                throw new IllegalArgumentException("Unknown algorithm: " + selectedAlgorithm);
        }
    }
	
	public void open() {
		try {
            // Download the view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VisualizationUI/VisualizationView.fxml"));
            
            // 
            SortingAlgorithm sortingAlgorithm = this.createAlgorithm(this.selectedAlgorithm, this.myArray);
            loader.setControllerFactory(param -> sortingAlgorithm);
            Parent root = loader.load();
            
            // Build a new scene
            Scene newScene = new Scene(root);
            
            // Get the current stage
            Stage stage = (Stage) finishButton.getScene().getWindow(); 
            
            // Move to the new scene
            stage.setScene(newScene);
            
        } catch(IOException e) {
            e.printStackTrace();
        }
	}
}
