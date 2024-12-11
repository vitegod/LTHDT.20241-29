package VisualizationUI;

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
import sorting.algorithms.BubbleSort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BubbleSortController {
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

    public void initializeWithArray(int[] arrayToSort) {
        BubbleSort bubbleSort = new BubbleSort();
        steps = captureSteps(bubbleSort, arrayToSort);
        currentStep = 0;
        lblAlgorithmName.setText("Bubble Sort Algorithm");
        updateUI();
    }

    private List<int[]> captureSteps(BubbleSort bubbleSort, int[] array) {
        List<int[]> stepList = new ArrayList<>();
        bubbleSort.sort(array, stepList);
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
}
