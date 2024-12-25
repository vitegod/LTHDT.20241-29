package VisualizationUI;

import CreateArrayUIStage2.ArrayModel;
import javafx.scene.paint.Color;
import java.util.Stack;

public class QuickSort extends SortingAlgorithm {

    private int step = 0;
    private int low = 0;
    private int high = myArray.getNbElements() - 1;
    private int i = low - 1;
    private int j = low;
    private boolean isPartitionComplete = false;

    private Stack<int[]> rangesToSort = new Stack<>();

    public QuickSort(ArrayModel array) {
        super(array);
    }
    
    @Override
    public void perform() {
        if (isPartitionComplete) {
            if (!rangesToSort.isEmpty()) {
                int[] range = rangesToSort.pop();
                low = range[0];
                high = range[1];
                i = low - 1;
                j = low;

                step = 0;
                isPartitionComplete = false;
                perform();
                return;
            } else {
                System.out.println("Quá trình QuickSort hoàn tất!");
                for(SortElement element : elements) {
                	element.setColor(Color.LIGHTGREEN);
                }
                return;
            }
        }

        int pivotPosition = high;
        double pivot = elements[pivotPosition].getValue();
        double pivotHeight = elements[pivotPosition].getHeight();
        elements[pivotPosition].setColor(Color.ORANGE);
        
        switch (step) {
            case 0:
                if (j < high) {
                    elements[j].setColor(Color.YELLOW);
                    step = 1; // Chuyển sang bước tiếp theo
                } else {
                    step = 3; // Nếu j >= high, chuẩn bị hoán đổi pivot
                }
                break;

            case 1:
                // **Bước 2**: Nếu a[j] <= pivot, hoán đổi a[i] và a[j]

                String[] info = {"The range under consideration: from " + "array[" + low + "]" + " to " + "array[" +  high + "]. "
                		+ "Pivot element: array[" + pivotPosition + "]." + " i = " + i + ", j = " + j + "."};
                this.instructionList.getItems().addAll(info);

                if (elements[j].getValue() <= pivot) {
                    i++;
                    if (i >= 0) { // Kiểm tra chỉ số i hợp lệ
                    	String[] info1 = {"Cause array[j] <= array[pivot], increase i, now i = " + i + " and j = " + j + ". Swap array[" + i + "] and array[" + j + "]."};
                    	this.instructionList.getItems().addAll(info1);

                    	swap(i, j);

                        // Đánh dấu màu
                        elements[i].setColor(Color.LIGHTBLUE);
                        elements[j].setColor(Color.LIGHTBLUE);
                    }
                }
                step = 2; // Chuyển sang bước tiếp theo
                break;

            case 2:
                // **Bước 3**: Đưa màu a[i] và a[j] về bình thường
                if (i >= 0) {
                    elements[i].setColor(Color.LIGHTGRAY);
                }
                elements[j].setColor(Color.LIGHTGRAY);

                // Tiến tới phần tử tiếp theo
                j++;
                String[] info2 = {"i = " + i + "; increase j, now j = " + j + "."};
                this.instructionList.getItems().addAll(info2);
                step = 0; // Quay lại bước 1 cho phần tử tiếp theo
                break;

            case 3:
                // **Bước 4**: Hoán đổi pivot vào vị trí cuối cùng
            	swap(i + 1, pivotPosition);

                // Đánh dấu màu
                elements[pivotPosition].setColor(Color.LIGHTGRAY);
                elements[i + 1].setColor(Color.ORANGE);

                isPartitionComplete = true; // Đánh dấu đã hoàn thành partition

                // Thêm các phạm vi cần sắp xếp vào stack
                if (i >= low) {
                    System.out.println("Thêm phạm vi con trái: " + low + " đến " + i);
                    rangesToSort.push(new int[]{low, i}); // Phạm vi bên trái pivot
                }
                if (i + 2 <= high) {
                    System.out.println("Thêm phạm vi con phải: " + (i + 2) + " đến " + high);
                    rangesToSort.push(new int[]{i + 2, high}); // Phạm vi bên phải pivot
                }
                break;
        }
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



