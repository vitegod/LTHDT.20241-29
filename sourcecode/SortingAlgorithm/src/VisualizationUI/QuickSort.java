package VisualizationUI;

import CreateArrayUIStage2.ArrayModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.Stack;

public class QuickSort extends SortingAlgorithm {

    private Stack<int[]> rangesToSort = new Stack<>();

    public QuickSort(ArrayModel array) {
        super(array);
    }

    @Override
    public void perform() {
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        rangesToSort.push(new int[]{0, myArray.getNbElements() - 1});

        KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), event -> {
            if (!rangesToSort.isEmpty()) {
                int[] range = rangesToSort.pop();
                int low = range[0];
                int high = range[1];

                int pivotIndex = partition(low, high);

                if (low < pivotIndex - 1) {
                    rangesToSort.push(new int[]{low, pivotIndex - 1});
                }
                if (pivotIndex + 1 < high) {
                    rangesToSort.push(new int[]{pivotIndex + 1, high});
                }
            } else {
                timeline.stop();
                for (SortElement element : elements) {
                    element.setColor(Color.LIGHTGREEN);
                }
                System.out.println("Quá trình QuickSort hoàn tất!");
            }
        });

        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    private int partition(int low, int high) {
        int i = low - 1;
        int j = low;
        int pivotPosition = high;
        double pivot = elements[pivotPosition].getValue();
        elements[pivotPosition].setColor(Color.ORANGE);

        while (j <= high - 1) {
            elements[j].setColor(Color.YELLOW);

            String[] info = {"The range under consideration: from " + "array[" + low + "]" + " to " + "array[" + high + "]. "
                    + "Pivot element: array[" + pivotPosition + "]." + " i = " + i + ", j = " + j + "."};
            this.instructionList.getItems().addAll(info);
            
            if (elements[j].getValue() <= pivot) {
                i++;
                if (i >= 0) {
                    String[] info1 = {"Cause array[j] <= array[pivot], increase i, now i = " + i + " and j = " + j + ". Swap array[" + i + "] and array[" + j + "]."};
                    this.instructionList.getItems().addAll(info1);
                    swap(i, j);
                    elements[i].setColor(Color.LIGHTBLUE);
                    elements[j].setColor(Color.LIGHTBLUE);
                }
            }
            
            if (i >= 0) {
                elements[i].setColor(Color.LIGHTGRAY);
            }
            
            elements[j].setColor(Color.LIGHTGRAY);

            j++;
            
            String[] info2 = {"i = " + i + "; increase j, now j = " + j + "."};
            this.instructionList.getItems().addAll(info2);
        }

        swap(i + 1, high);
        elements[high].setColor(Color.LIGHTGRAY);
        elements[i + 1].setColor(Color.ORANGE);

        return i + 1;
    }

    private void swap(int index1, int index2) {
        double tempValue = elements[index1].getValue();
        elements[index1].setValueText(elements[index2].getValue());
        elements[index2].setValueText(tempValue);

        double tempHeight = elements[index1].getHeight();
        elements[index1].setHeight(elements[index2].getHeight());
        elements[index2].setHeight(tempHeight);

        elements[index1].getRectangle().setHeight(elements[index1].getHeight());
        elements[index2].getRectangle().setHeight(elements[index2].getHeight());
    }
}