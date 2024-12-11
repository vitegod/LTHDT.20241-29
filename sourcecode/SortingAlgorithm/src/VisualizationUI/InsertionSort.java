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
        // Chuyển đổi các phần tử của mảng sang double[] để thực hiện thuật toán
        double[] array = Arrays.stream(elements).mapToDouble(SortElement::getValue).toArray();

        // Thuật toán Insertion Sort
        for (int i = 1; i < array.length; i++) {
            double key = array[i];
            int j = i - 1;

            // Lưu trạng thái trước khi bắt đầu vòng lặp
            addStep(Arrays.copyOf(array, array.length));

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;

                // Lưu trạng thái sau mỗi lần di chuyển phần tử
                addStep(Arrays.copyOf(array, array.length));
            }

            // Chèn giá trị key vào đúng vị trí
            array[j + 1] = key;

            // Lưu trạng thái sau khi chèn phần tử
            addStep(Arrays.copyOf(array, array.length));
        }

        // Lưu trạng thái cuối cùng (hoàn tất)
        addStep(Arrays.copyOf(array, array.length));
    }

    @Override
    public void displayStep(int stepIndex) {
        // Gọi phương thức từ lớp cha để cập nhật giá trị
        super.displayStep(stepIndex);

        // Lấy trạng thái hiện tại của bước
        double[] stepState = steps.get(stepIndex);

        // Duyệt qua các phần tử để cập nhật giá trị và màu sắc
        for (int i = 0; i < elements.length; i++) {
            // Cập nhật giá trị hiển thị
            elements[i].updateValue(stepState[i]);

            // Đánh dấu màu sắc các phần tử
            if (stepIndex > 0 && stepState[i] != steps.get(stepIndex - 1)[i]) {
                elements[i].setColor(Color.YELLOW); // Phần tử thay đổi
            } else {
                elements[i].setColor(Color.LIGHTGRAY); // Phần tử không thay đổi
            }
        }

        // Nếu đây là bước cuối cùng, tô màu hoàn thành
        if (stepIndex == steps.size() - 1) {
            for (SortElement element : elements) {
                element.setColor(Color.LIGHTGREEN);
            }
        }
    }
}
