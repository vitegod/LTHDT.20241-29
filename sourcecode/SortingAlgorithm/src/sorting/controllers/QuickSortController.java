package sorting.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sorting.algorithms.QuickSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QuickSortController {
    @FXML
    private Label lblAlgorithmName;

    @FXML
    private TextArea txtArrayState;

    @FXML
    private Button btnNextStep;

    @FXML
    private Button btnPreviousStep;

    @FXML
    private Button btnFinalStep;
    
    @FXML
    private Button btnReturnMenu;

    private List<int[]> steps;  
    private int currentStep;

    @FXML
    public void initialize() {
        QuickSort quickSort = new QuickSort();

        int[] initialArray = {5, 3, 8, 6, 2};
        steps = captureSteps(quickSort, initialArray);


        currentStep = 0;
        lblAlgorithmName.setText("QuickSort Algorithm");
        updateUI();
    }

    private List<int[]> captureSteps(QuickSort quickSort, int[] array) {
        List<int[]> stepList = new ArrayList<>();
        quickSort.sort(array, stepList);
        return stepList;
    }

    private void updateUI() {
        int[] currentArray = steps.get(currentStep);
        txtArrayState.setText(arrayToString(currentArray));
    }

    @FXML
    public void handleNextStep() {
        if (currentStep < steps.size() - 1) {
            currentStep++;
            updateUI();
        }
    }

    @FXML
    public void handlePreviousStep() {
        if (currentStep > 0) {
            currentStep--;
            updateUI();
        }
    }

    @FXML
    public void handleFinalStep() {
        currentStep = steps.size() - 1;
        updateUI();
    }
    
    @FXML
    public void handleReturnMenu() {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    alert.setTitle("Confirm");
	    alert.setHeaderText("Return to main menu");
	    alert.setContentText("Are you sure to return? All changes will be lost!");

	    Optional<ButtonType> result = alert.showAndWait();

	    if (result.isPresent() && result.get() == ButtonType.OK) {
	        try {
	            Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("/MainMenu/MainMenuView.fxml"));
	            Scene mainMenuScene = new Scene(mainMenuRoot);
	            Stage stage = (Stage) btnReturnMenu.getScene().getWindow();
	            stage.setScene(mainMenuScene);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
    }
  


    private String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

	public void initializeWithArray(int[] arrayToSort) {
		QuickSort quickSort = new QuickSort();
		steps = captureSteps(quickSort, arrayToSort);
		
		currentStep = 0;
        lblAlgorithmName.setText("QuickSort Algorithm");
        updateUI();
	}
}
