package VisualizationUI;

import CreateArrayUIStage2.*;
import javafx.scene.paint.Color;
import java.util.Arrays;

public class QuickSort extends SortingAlgorithm {

    public QuickSort(ArrayModel array) {
        super(array);
    }

    @Override
    public void perform() {
        double[] array = Arrays.stream(elements).mapToDouble(SortElement::getValue).toArray();
        quickSort(array, 0, array.length - 1);
        addStep(Arrays.copyOf(array, array.length));
    }

    private void quickSort(double[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            addStep(Arrays.copyOf(array, array.length));
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(double[] array, int low, int high) {
        double pivot = array[high];
        int i = low - 1; 

        for (int j = low; j < high; j++) {
            addStep(Arrays.copyOf(array, array.length));

            if (array[j] <= pivot) {
                i++;
                double temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                addStep(Arrays.copyOf(array, array.length));
            }
        }

        double temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1; 
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
