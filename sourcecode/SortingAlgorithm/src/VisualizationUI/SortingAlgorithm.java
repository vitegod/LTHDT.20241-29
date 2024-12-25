package VisualizationUI;

import CreateArrayUIStage2.*;
import java.net.URL;
import java.util.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.*;
import java.io.IOException;
import javafx.stage.Stage;

public abstract class SortingAlgorithm implements Initializable {
	protected String selectedAlgorithm;
	protected ArrayModel myArray;
	protected SortElement[] elements;
	protected Timeline timeline;
	protected boolean isPaused = false;
	protected Stack<Runnable> undoStack = new Stack<>();
	protected Stack<Runnable> redoStack = new Stack<>();
	protected boolean isManualStepping = false;
	
	@FXML
	protected Button pausePlayButton;
	
	@FXML
	protected Button nextButton;

	@FXML
	protected Button prevButton;
	
	@FXML
	protected ListView<String> instructionList;

	@FXML
	private HBox hBox;

	@FXML
	protected Button startButton;

	@FXML
	private Button returnButton;

	public SortingAlgorithm(String option, ArrayModel array) {
		this.selectedAlgorithm = option;
		this.myArray = array;
	}

	public SortingAlgorithm(ArrayModel array) {
		this.myArray = array;
	}
	
	@FXML
	protected void handlePausePlay() {
	    if (isPaused) {
	        timeline.play();
	        pausePlayButton.setText("Pause");
	        isPaused = false;
	        nextButton.setDisable(true);
	        prevButton.setDisable(true);
	        isManualStepping = false;
	    } else {
	        timeline.pause();
	        pausePlayButton.setText("Play");
	        isPaused = true;
	        nextButton.setDisable(false);
	        isManualStepping = true;

	        if(undoStack.isEmpty()) {
	        	prevButton.setDisable(true);
	        }
	        else {
	        	prevButton.setDisable(false);
	        }
	    }
	}
	
	@FXML
	protected void handleNextStep() {
	    isManualStepping = true;
	    if (!redoStack.isEmpty()) {
	        Runnable redoAction = redoStack.pop();
	        redoAction.run();

	        undoStack.push(redoAction);

	        if (undoStack.isEmpty()) {
	            prevButton.setDisable(true);
	        } else {
	            prevButton.setDisable(false);
	        }

	        if (redoStack.isEmpty()) {
	            nextButton.setDisable(true);
	        } else {
	            nextButton.setDisable(false);
	        }
	    } else {
	        nextButton.setDisable(true);
	    }
	}
	
	@FXML
	protected void handlePrevStep() {
	    isManualStepping = true;
	    if (!undoStack.isEmpty()) {
	        Runnable undoAction = undoStack.pop();
	        KeyFrame keyFrameToRemove = null;
	        for (KeyFrame kf : timeline.getKeyFrames()) {
	            Object eventHandler = kf.getOnFinished();
	            if (eventHandler instanceof EventHandler && ((EventHandler<?>) eventHandler).equals(undoAction)) {
	                keyFrameToRemove = kf;
	                break;
	            }
	        }

	        if (keyFrameToRemove != null) {
	            timeline.getKeyFrames().remove(keyFrameToRemove);
	        }

	        redoStack.push(undoAction);

	        if (redoStack.isEmpty()) {
	            nextButton.setDisable(true);
	        } else {
	            nextButton.setDisable(false);
	        }

	        if (undoStack.isEmpty()) {
	            prevButton.setDisable(true);
	        } else {
	            prevButton.setDisable(false);
	        }
	    } else {
	        prevButton.setDisable(true);
	    }
	}

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double[] sortedArray = Arrays.stream(myArray.getArray()).sorted().toArray();

        hBox.setSpacing(10);
        hBox.setAlignment(Pos.BOTTOM_CENTER);
        hBox.setPadding(new Insets(20, 20, 50, 20));

        Scene scene = hBox.getScene();
        if (scene != null) {
            Stage stage = (Stage) scene.getWindow();
            if (stage != null) {
                stage.setHeight(600);
            }
        }

        elements = new SortElement[myArray.getNbElements()];

        int i = 0;
        for (double value : myArray.getArray()) {
            elements[i] = new SortElement(value, sortedArray);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(elements[i].getRectangle(), elements[i].getValueText());
            stackPane.setAlignment(Pos.BOTTOM_CENTER);

            hBox.getChildren().add(stackPane);
            i++;
        }

        // Initialize for instruction
        String[] instruction = {"1. For quick sort algorithm, click 'Start' to visualize each step of the process.",
                "2. For other algorithms, click 'Start' ONE TIME only. The process will run automatically."};
        instructionList.getItems().addAll(instruction);
//        perform();

        startButton.setOnAction(event -> {
            startButton.setDisable(true);
            pausePlayButton.setDisable(false);
            nextButton.setDisable(true);
            prevButton.setDisable(true);

            perform();
            isManualStepping = false;
        });

	    pausePlayButton.setOnAction(event -> handlePausePlay());

	    pausePlayButton.setDisable(true);
	    nextButton.setDisable(true);
	    prevButton.setDisable(true);
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