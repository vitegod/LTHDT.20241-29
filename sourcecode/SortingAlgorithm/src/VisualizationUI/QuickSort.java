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

    // Stack hoặc danh sách theo dõi các phạm vi cần sắp xếp đệ quy
    private Stack<int[]> rangesToSort = new Stack<>();

    public QuickSort(ArrayModel array) {
        super(array);
    }
    
    @Override
    public void perform() {
        if (isPartitionComplete) {
            // Nếu partition đã hoàn thành, tiến hành đệ quy cho các phạm vi con
            if (!rangesToSort.isEmpty()) {
                int[] range = rangesToSort.pop();
                low = range[0];
                high = range[1];
                i = low - 1;
                j = low;

                step = 0;
                isPartitionComplete = false; // Đặt lại trạng thái partition
                perform(); // Tiến hành partition cho phạm vi con
                return;
            } else {
                System.out.println("Quá trình QuickSort hoàn tất!");
                for(SortElement element : elements) {
                	element.setColor(Color.LIGHTGREEN);
                }
                return;
            }
        }

        // Lấy pivot (sử dụng phần tử cuối cùng trong mảng)
        int pivotPosition = high;
        double pivot = elements[pivotPosition].getValue();
        elements[pivotPosition].setColor(Color.ORANGE);  // Đánh dấu pivot
        
        switch (step) {
            case 0:
                // **Bước 1**: Bôi vàng phần tử tại vị trí j
                if (j < high) {
                    elements[j].setColor(Color.YELLOW); // Đánh dấu phần tử đang được so sánh
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
                        double temp = elements[i].getValue();
                        elements[i].setValueText(elements[j].getValue());
                        elements[j].setValueText(temp);

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
                double temp = elements[i + 1].getValue();
                elements[i + 1].setValueText(pivot);
                elements[pivotPosition].setValueText(temp);

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

}



