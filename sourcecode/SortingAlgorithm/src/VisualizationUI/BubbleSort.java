package VisualizationUI;

import CreateArrayUIStage2.*;
import javafx.scene.paint.Color;
import java.util.Arrays;

public class BubbleSort extends SortingAlgorithm {

    public BubbleSort(ArrayModel array) {
    	super(array);
    }

    @Override
    public void perform() {
        double[] array = Arrays.stream(elements).mapToDouble(SortElement::getValue).toArray();
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                addStep(Arrays.copyOf(array, array.length));

                if (array[j] > array[j + 1]) {
                    double temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    addStep(Arrays.copyOf(array, array.length));
                }
            }
        }

        addStep(Arrays.copyOf(array, array.length));
    }

    @Override
    public void displayStep(int stepIndex) {
        super.displayStep(stepIndex);

        double[] stepState = steps.get(stepIndex);

        for (int i = 0; i < elements.length; i++) {
            elements[i].updateValue(stepState[i]);

            if (stepIndex > 0 && stepState[i] != steps.get(stepIndex - 1)[i]) {
                elements[i].setColor(Color.YELLOW); 
            } else {
                elements[i].setColor(Color.LIGHTGRAY); 
            }
        }

        if (stepIndex == steps.size() - 1) {
            for (SortElement element : elements) {
                element.setColor(Color.LIGHTGREEN); 
            }
        }
    }
}
