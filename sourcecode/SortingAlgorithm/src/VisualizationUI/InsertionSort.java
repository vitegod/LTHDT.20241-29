package VisualizationUI;

import CreateArrayUIStage2.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class InsertionSort extends SortingAlgorithm{
	public InsertionSort(ArrayModel array) {
		super(array);
	}
	
	@Override
	public void perform() {
		Timeline timeline = new Timeline();
	    timeline.setCycleCount(Timeline.INDEFINITE); // Lặp cho đến khi hoàn thành

	    // Biến giữ trạng thái của thuật toán
	    int[] i = {1}; // Vị trí hiện tại trong vòng lặp ngoài
	    int[] j = {0}; // Vị trí hiện tại trong vòng lặp while
	    double[] key = {0}; // Giá trị của phần tử cần chèn

	    // Thêm các bước vào Timeline
	    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1500), event -> {
	        if (j[0] < 0 || elements[j[0]].getValue() <= key[0]) {
	            // Chèn phần tử vào vị trí thích hợp
	            elements[j[0] + 1].setValueText(key[0]);
	            String[] info1 = {"Key element: " + " array[" + i[0] + "] = " + key[0] + "."};
                this.instructionList.getItems().addAll(info1);
	            String[] info = {"Insert key element into position: index = " + (j[0] + 1) + "."};
	            this.instructionList.getItems().addAll(info);

	            // Đặt lại màu sắc sau mỗi vòng lặp ngoài
	            for (SortElement element : elements) {
	                element.getRectangle().setFill(Color.LIGHTGRAY);
	            }

	            // Tiến tới bước tiếp theo của vòng lặp ngoài
	            i[0]++;
	            if (i[0] >= myArray.getNbElements()) {
	                timeline.stop(); // Dừng khi hoàn thành
	                // Sau khi hoàn thành, chuyển tất cả sang màu xanh
	                for(SortElement element : elements) {
	                	 element.getRectangle().setFill(Color.LIGHTGREEN);
	                }
	            } else {
	                key[0] = elements[i[0]].getValue();
	                j[0] = i[0] - 1;
	                elements[i[0]].getRectangle().setFill(Color.YELLOW); // Đánh dấu phần tử đang xử lý
	                
	            }
	        } else {
	            // Di chuyển phần tử lớn hơn sang phải
	            elements[j[0] + 1].setValueText(elements[j[0]].getValue());
	            elements[j[0]].getRectangle().setFill(Color.YELLOW);
	            j[0]--;
	        }
	    }));

	    // Bắt đầu thực thi Timeline
	    key[0] = elements[i[0]].getValue();
	    j[0] = i[0] - 1;
	    elements[i[0]].getRectangle().setFill(Color.YELLOW); // Đánh dấu phần tử đầu tiên
	    timeline.play();
	}
}
