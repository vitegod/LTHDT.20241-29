package VisualizationUI;

import CreateArrayUIStage2.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class BubbleSort extends SortingAlgorithm {
	public BubbleSort(ArrayModel array) {
		super(array);
	}
	
	@Override
	public void perform() {
		Timeline timeline = new Timeline();
	    timeline.setCycleCount(Timeline.INDEFINITE); // Lặp cho đến khi hoàn thành

	    // Biến trạng thái
	    int[] i = {0}; // Vòng lặp ngoài
	    int[] j = {0}; // Vòng lặp trong
	    boolean[] comparing = {false}; // Trạng thái có đang so sánh hay không

	    KeyFrame keyFrame = new KeyFrame(Duration.millis(300), event -> {
	        if (i[0] < myArray.getNbElements() - 1) {
	            if (!comparing[0]) {
	                // Đánh dấu hai phần tử được so sánh
	                elements[j[0]].getRectangle().setFill(Color.YELLOW);
	                elements[j[0] + 1].getRectangle().setFill(Color.YELLOW);
	                comparing[0] = true; // Đặt trạng thái đang so sánh
	            } else {
	                // Thực hiện so sánh và hoán đổi nếu cần
	                if (elements[j[0]].getValue() > elements[j[0] + 1].getValue()) {
	                	swap(j[0], j[0] + 1);
	                    
	                    String[] info = {"Cause array[" + j[0] + "] > array[" + (j[0] + 1) + "], swap them."};
	                    this.instructionList.getItems().addAll(info);
	                }

	                // Khôi phục màu sắc sau khi so sánh
	                elements[j[0]].getRectangle().setFill(Color.LIGHTGRAY);
	                elements[j[0] + 1].getRectangle().setFill(Color.LIGHTGRAY);
	                comparing[0] = false; // Đặt trạng thái kết thúc so sánh

	                // Chuyển sang cặp phần tử tiếp theo
	                j[0]++;
	                if (j[0] >= myArray.getNbElements() - 1 - i[0]) {
	                    // Khi hoàn thành một vòng lặp trong
	                    elements[myArray.getNbElements() - 1 - i[0]].getRectangle().setFill(Color.LIGHTGREEN);
	                    j[0] = 0;
	                    i[0]++;
	                }
	            }
	        } else {
	            // Toàn bộ mảng đã được sắp xếp
	            for (SortElement element : elements) {
	                element.getRectangle().setFill(Color.LIGHTGREEN);
	            }
	            timeline.stop();
	        }
	    });

	    // Thêm KeyFrame vào Timeline và bắt đầu thực thi
	    timeline.getKeyFrames().add(keyFrame);
	    timeline.play();
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
