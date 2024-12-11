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

        // Gọi hàm đệ quy QuickSort
        quickSort(array, 0, array.length - 1);

        // Lưu trạng thái cuối cùng (hoàn tất)
        addStep(Arrays.copyOf(array, array.length));
    }

    private void quickSort(double[] array, int low, int high) {
        if (low < high) {
            // Phân đoạn mảng và tìm chỉ số pivot
            int pivotIndex = partition(array, low, high);

            // Lưu trạng thái sau khi phân đoạn
            addStep(Arrays.copyOf(array, array.length));

            // Đệ quy cho mảng bên trái
            quickSort(array, low, pivotIndex - 1);

            // Đệ quy cho mảng bên phải
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(double[] array, int low, int high) {
        double pivot = array[high];
        int i = low - 1; // Chỉ số phần tử nhỏ hơn pivot

        for (int j = low; j < high; j++) {
            // Lưu trạng thái trước khi so sánh
            addStep(Arrays.copyOf(array, array.length));

            if (array[j] <= pivot) {
                i++;

                // Hoán đổi array[i] và array[j]
                double temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                // Lưu trạng thái sau khi hoán đổi
                addStep(Arrays.copyOf(array, array.length));
            }
        }

        // Hoán đổi pivot vào vị trí chính xác
        double temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1; // Trả về vị trí của pivot
    }

    @Override
    public void displayStep(int stepIndex) {
        super.displayStep(stepIndex);

        double[] stepState = steps.get(stepIndex);

        for (int i = 0; i < elements.length; i++) {
            elements[i].updateValue(stepState[i]);

            if (stepIndex > 0 && stepState[i] != steps.get(stepIndex - 1)[i]) {
                elements[i].setColor(Color.YELLOW); // Phần tử thay đổi
            } else {
                elements[i].setColor(Color.LIGHTGRAY); // Phần tử không thay đổi
            }
        }

        if (stepIndex == steps.size() - 1) {
            for (SortElement element : elements) {
                element.setColor(Color.LIGHTGREEN); // Màu hoàn tất
            }
        }
    }
}
