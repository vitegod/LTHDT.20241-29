package CreateArrayUIStage2;

import javafx.fxml.*;
import javafx.scene.control.*;
import java.net.URL;
import javafx.collections.*;
import java.util.*;

public class EnterEachElementController extends CreateArrayController implements Initializable {
	@FXML
	private TextField array;

	@FXML
	private ComboBox<Integer> nbElements;

	@FXML
	private ComboBox<String> pivotPosition;
	
	public EnterEachElementController(String option) {
		this.selectedAlgorithm = option;
	}
	
	public void setupNbElementsListener() {
	    nbElements.valueProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            if(selectedAlgorithm.equals("Quick sort")) {
	            	updatePivotPosition();
	            }
	            else {
	            	pivotPosition.setPromptText("No need");
	            }
	        }
	    });
	}
	
	public void updatePivotPosition() {
		ObservableList<String> pivotOption = FXCollections.observableArrayList("Right most");
		pivotPosition.setItems(pivotOption);
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		// Initialize for "number of elements" combo box
		ObservableList<Integer> listNumber = FXCollections.observableArrayList(5, 6, 7, 8, 9, 10);
		nbElements.setItems(listNumber);
		nbElements.setPromptText("Click here");
		setupNbElementsListener();

		// Initialize for "position of pivot" combo box
		pivotPosition.setPromptText("Click here");
	}
	
	@Override
	public void setupMyArray() {
		String input = array.getText().trim();
	    String[] elements = input.split("\\s+");
	    double[] list = new double[nbElements.getValue()];
	    int count = 0, i = 0;

	    for (String element : elements) {
	        try {
	        	list[i] = Double.parseDouble(element);
	            count++;
	            i++;
	        } catch (NumberFormatException e) {
	            // Show error if the number format is not valid
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Not a number error");
	            alert.setHeaderText("Invalid input");
	            alert.setContentText("Element \"" + element + "\" is not a number. Please check!");
	            alert.showAndWait();
	        }
	    }

	    if (count == nbElements.getValue()) {
	        myArray.setNbElements(nbElements.getValue());

	        if (selectedAlgorithm.equals("Quick sort")) {
	            myArray.setPivotPosition(pivotPosition.getValue());
	        }

	        myArray.setArray(list);
	        
	        open();
	    } 
	    else {
	        // Show error if the number of elements doesn't match
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Number of elements error");
	        alert.setHeaderText("The number of elements does not match");
	        alert.setContentText("You've chosen " + nbElements.getValue() + " elements, but you've entered " + count + ". Please check!");
	        alert.showAndWait();
	    }
	}
}
