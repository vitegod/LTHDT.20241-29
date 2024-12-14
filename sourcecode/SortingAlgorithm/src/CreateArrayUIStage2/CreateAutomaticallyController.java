package CreateArrayUIStage2;

import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class CreateAutomaticallyController extends CreateArrayController {
	private int nbElements;
	private String pivotPosition;
	private double[] randomList;
	
	@FXML
	private ListView<String> resultList;
	
	@FXML
	private Button showResultButton;
	
	public CreateAutomaticallyController(String option) {
		this.selectedAlgorithm = option;
	}
	
	@Override
	public void setupMyArray() {
		myArray.setNbElements(nbElements);
		myArray.setArray(randomList);
		myArray.setPivotPosition(pivotPosition);
		open();
	}
	
	public void generateRandomly() {
        Random r = new Random();
		
		// Randomly generate the number of elements
		nbElements = 5 + r.nextInt(6);
		
		// Randomly generate all the elements
		double min = -10.0;
		double max = 10.0;
		randomList = new double[nbElements];
		for(int i = 0; i < nbElements; i++) {
			double randomValue = min + (max - min) * r.nextDouble();
			randomValue = Math.round(randomValue * 10) / 10.0;
			randomList[i] = randomValue;
		}
		
		// Randomly generate pivot position for quick sort
		if(selectedAlgorithm.equals("Quick sort")) {
			pivotPosition = "Right most";
		}
	}
	
	public void showResult() {
		this.generateRandomly();
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < nbElements; i++) {
			sb.append(String.valueOf(randomList[i]));
			if(i < nbElements - 1) {
				sb.append(" ");
			}
		}
		
	    String pv = "";
	    if(selectedAlgorithm.equals("Quick sort")) {
	    	pv = String.valueOf(pivotPosition);
	    }
	    else {
	    	pv = "No need";
	    }
		
		String[] result = {"You chose algorithm: " + this.selectedAlgorithm,
                "The number of elements: " + this.nbElements,
                "Your array: " + sb,
                "Pivot position: " + pv
		};
        resultList.getItems().addAll(result);
	}
}
