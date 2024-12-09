package VisualizationUI;

import CreateArrayUIStage2.*;
import java.net.URL;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.fxml.*;
import java.io.IOException;
import javafx.stage.Stage;

public abstract class SortingAlgorithm implements Initializable {
	protected String selectedAlgorithm;
	protected ArrayModel myArray;
	protected SortElement[] elements;
	
	@FXML
	private HBox hBox;
	
	@FXML
	private Button returnButton;
	
	public SortingAlgorithm(String option, ArrayModel array) {
		this.selectedAlgorithm = option;
		this.myArray = array;
	}
	
	public SortingAlgorithm(ArrayModel array) {
		this.myArray = array;
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resouceBundle) {
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);
		hBox.setPadding(new Insets(20));
		elements = new SortElement[myArray.getNbElements()];
		
		int i = 0;
		for(double value : myArray.getArray()) {
			elements[i] = new SortElement(value, 40);
			
			StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(elements[i].getRectangle(), elements[i].getValueText());
            stackPane.setAlignment(Pos.CENTER); 
			
            hBox.getChildren().add(stackPane);
            i++;
		}
	}
	
	public abstract void perform();

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
}
