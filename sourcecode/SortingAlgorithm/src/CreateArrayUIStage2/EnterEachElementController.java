package CreateArrayUIStage2;

import javafx.fxml.*;
import javafx.scene.control.*;
import java.net.URL;
import javafx.collections.*;
import java.util.*;
import javafx.stage.Stage;
import sorting.controllers.BubbleSortController;
import sorting.controllers.QuickSortController;
import javafx.scene.*;
import java.io.IOException;

public class EnterEachElementController implements Initializable, Setupable {
	@FXML
	private TextField array;

	@FXML
	private Button finishButton;

	@FXML
	private ComboBox<Integer> nbElements;

	@FXML
	private ComboBox<Integer> pivotPosition;

	@FXML
	private Button returnButton;
	
	private String selectedAlgorithm;
	
	private ArrayModel myArray = new ArrayModel();
	
	public EnterEachElementController(String option) {
		this.selectedAlgorithm = option;
	}
	
	public void setupNbElementsListener() {
	    nbElements.valueProperty().addListener((observable, oldValue, newValue) -> {
	        if (newValue != null) {
	            if(selectedAlgorithm.equals("Quick sort")) {
	            	updatePivotPosition(newValue);
	            }
	            else {
	            	pivotPosition.setPromptText("No need");
	            }
	        }
	    });
	}

	public void updatePivotPosition(int nbValue) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < nbValue; i++) {
			list.add(i); //
		}
		ObservableList<Integer> listPosition = FXCollections.observableArrayList(list);
		pivotPosition.setItems(listPosition);
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
	    ArrayList<Double> list = new ArrayList<>();
	    int count = 0;

	    for (String element : elements) {
	        try {
	            list.add(Double.parseDouble(element));
	            count++;
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
	        
	    } else {
	        // Show error if the number of elements doesn't match
	        Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Number of elements error");
	        alert.setHeaderText("The number of elements does not match");
	        alert.setContentText("You've chosen " + nbElements.getValue() + " elements, but you've entered " + count + ". Please check!");
	        alert.showAndWait();
	    }
	}
	
	@FXML
	public void handleFinishButton() {
	    setupMyArray();

	    if (myArray.getArray().isEmpty()) {
	        Alert alert = new Alert(Alert.AlertType.WARNING);
	        alert.setTitle("Empty Array");
	        alert.setHeaderText("No elements in the array");
	        alert.setContentText("Please enter the elements of the array before proceeding.");
	        alert.showAndWait();
	        return;
	    }

	    try {
	        FXMLLoader loader = getAlgorithmLoader(selectedAlgorithm);
	        if (loader == null) {
	            throw new IllegalArgumentException("Unsupported algorithm: " + selectedAlgorithm);
	        }

	        Parent sortRoot = loader.load();
	        int[] arrayToSort = myArray.getArray().stream().mapToInt(Double::intValue).toArray();
	        Object controller = loader.getController();

	        if (controller instanceof QuickSortController) {
	            ((QuickSortController) controller).initializeWithArray(arrayToSort);
	        } else if (controller instanceof BubbleSortController) {
	            ((BubbleSortController) controller).initializeWithArray(arrayToSort);
	        }

	        Scene sortScene = new Scene(sortRoot);
	        Stage stage = (Stage) finishButton.getScene().getWindow();
	        stage.setScene(sortScene);

	    } catch (IOException | IllegalArgumentException e) {
	        e.printStackTrace();
	    }
	}

	
	private FXMLLoader getAlgorithmLoader(String algorithm) {
	    switch (algorithm) {
	        case "Quick sort":
	            return new FXMLLoader(getClass().getResource("/sorting/views/QuickSortView.fxml"));
	        case "Bubble sort":
	            return new FXMLLoader(getClass().getResource("/sorting/views/BubbleSortView.fxml"));
	        default:
	            return null;
	    }
	}

	
	@FXML
	public void handleReturnButton() {
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
