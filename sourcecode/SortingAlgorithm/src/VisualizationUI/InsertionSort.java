package VisualizationUI;

import CreateArrayUIStage2.*;
import javafx.scene.paint.Color;
import java.util.Arrays;

public class InsertionSort extends SortingAlgorithm {

    public InsertionSort(ArrayModel array) {
        super(array);
    }

    @Override
    public void perform() {
        if (elements.length == 0) return; 

        double[] array = Arrays.stream(elements).mapToDouble(SortElement::getValue).toArray();

        for (int i = 1; i < array.length; i++) {
            double key = array[i];
            int j = i - 1;

            addStep(Arrays.copyOf(array, array.length)); 

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;

                addStep(Arrays.copyOf(array, array.length)); 
            }

            array[j + 1] = key;
            addStep(Arrays.copyOf(array, array.length)); 
        }

        addStep(Arrays.copyOf(array, array.length)); 
        System.out.println("Steps recorded: " + steps.size()); // Kiểm tra số bước đã ghi
        for (double[] step : steps) {
            System.out.println(Arrays.toString(step)); // In ra từng bước
        }
    }


    @Override
    public void displayStep(int stepIndex) {
        super.displayStep(stepIndex);

        double[] stepState = steps.get(stepIndex);
        System.out.println("Displaying step " + stepIndex + ": " + Arrays.toString(stepState));
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
