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
    protected int currentStep = 0; 
    protected List<double[]> steps; 
    
    @FXML
    private HBox hBox;

    @FXML
    private Button previousStepButton;

    @FXML
    private Button nextStepButton;

    @FXML
    private Button finalStepButton;

    @FXML
    private Button returnButton;

    @FXML
    private Label stepLabel;

    public SortingAlgorithm(String option, ArrayModel array) {
        this.selectedAlgorithm = option;
        this.myArray = array;
        this.steps = new ArrayList<>();
    }

    public SortingAlgorithm(ArrayModel array) {
        this.myArray = array;
        this.steps = new ArrayList<>();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(20));
        elements = new SortElement[myArray.getNbElements()];

        int i = 0;
        for (double value : myArray.getArray()) {
            elements[i] = new SortElement(value, 40);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(elements[i].getRectangle(), elements[i].getValueText());
            stackPane.setAlignment(Pos.CENTER);

            hBox.getChildren().add(stackPane);
            i++;
        }

        updateStepLabel();
    }

    public abstract void perform(); 
    public void displayStep(int stepIndex) {
        if (stepIndex < 0 || stepIndex >= steps.size()) {
            return;
        }

        double[] stepState = steps.get(stepIndex);
        for (int i = 0; i < elements.length; i++) {
            elements[i].updateValue(stepState[i]);
        }

        currentStep = stepIndex;
        updateStepLabel();
    }

    public void goToPreviousStep() {
        if (currentStep > 0) {
            displayStep(currentStep - 1);
        }
    }


    public void goToNextStep() {
        if (currentStep < steps.size() - 1) {
            displayStep(currentStep + 1);
        }
    }

    public void goToFinalStep() {
        displayStep(steps.size() - 1);
    }

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


    private void updateStepLabel() {
        stepLabel.setText("Step: " + (currentStep + 1) + " / " + steps.size());
    }

    protected void addStep(double[] arrayState) {
        steps.add(arrayState.clone());
    }

}
