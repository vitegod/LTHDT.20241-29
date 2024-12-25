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
		timeline = new Timeline();
		timeline.setCycleCount(Timeline.INDEFINITE);

	    // Biến giữ trạng thái của thuật toán
	    int[] i = {1}; // Vị trí hiện tại trong vòng lặp ngoài
	    int[] j = {0}; // Vị trí hiện tại trong vòng lặp while
	    double[] key = {0}; // Giá trị của phần tử cần chèn
	    double[] keyHeight = {0};

	    // Thêm các bước vào Timeline
	    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(300), event -> {
//	        if (j[0] < 0 || elements[j[0]].getValue() <= key[0]) {
//	            // Chèn phần tử vào vị trí thích hợp
//	        	elements[j[0] + 1].setValueText(key[0]);
//                elements[j[0] + 1].setHeight(keyHeight[0]);
//                
//	            String[] info1 = {"Key element: " + " array[" + i[0] + "] = " + key[0] + "."};
//                this.instructionList.getItems().addAll(info1);
//	            String[] info = {"Insert key element into position: index = " + (j[0] + 1) + "."};
//	            this.instructionList.getItems().addAll(info);
//
//	            // Đặt lại màu sắc sau mỗi vòng lặp ngoài
//	            for (SortElement element : elements) {
//	                element.getRectangle().setFill(Color.LIGHTGRAY);
//	            }
//
//	            // Tiến tới bước tiếp theo của vòng lặp ngoài
//	            i[0]++;
//	            if (i[0] >= myArray.getNbElements()) {
//	                timeline.stop(); // Dừng khi hoàn thành
//	                // Sau khi hoàn thành, chuyển tất cả sang màu xanh
//	                for(SortElement element : elements) {
//	                	 element.getRectangle().setFill(Color.LIGHTGREEN);
//	                }
//	            } else {
//	                key[0] = elements[i[0]].getValue();
//	                keyHeight[0] = elements[i[0]].getHeight();
//	                j[0] = i[0] - 1;
//	                elements[i[0]].getRectangle().setFill(Color.YELLOW); // Đánh dấu phần tử đang xử lý
//	                
//	            }
//	        } else {
//	            // Di chuyển phần tử lớn hơn sang phải
//	            elements[j[0] + 1].setValueText(elements[j[0]].getValue());
//	            elements[j[0] + 1].setHeight(elements[j[0]].getHeight());
//	            elements[j[0]].getRectangle().setFill(Color.YELLOW);
//	            j[0]--;
//	        }
	    	if (j[0] < 0 || elements[j[0]].getValue() <= key[0]) {
	    	    elements[j[0] + 1].setValueText(key[0]);
	    	    elements[j[0] + 1].setHeight(keyHeight[0]);

	    	    String[] info1 = {"Key element: " + " array[" + i[0] + "] = " + key[0] + "."};
	    	    this.instructionList.getItems().addAll(info1);
	    	    String[] info = {"Insert key element into position: index = " + (j[0] + 1) + "."};
	    	    this.instructionList.getItems().addAll(info);

	    	    for (SortElement element : elements) {
	    	        element.getRectangle().setFill(Color.LIGHTGRAY);
	    	    }

	    	    i[0]++;
	    	    if (i[0] >= myArray.getNbElements()) {
	    	        timeline.stop();
	    	        for (SortElement element : elements) {
	    	            element.getRectangle().setFill(Color.LIGHTGREEN);
	    	        }
	    	        startButton.setDisable(false);
	    	        pausePlayButton.setDisable(true);
	    	        nextButton.setDisable(true);
	    	        prevButton.setDisable(true);
	    	    } else {
	    	        key[0] = elements[i[0]].getValue();
	    	        keyHeight[0] = elements[i[0]].getHeight();
	    	        j[0] = i[0] - 1;
	    	        elements[i[0]].getRectangle().setFill(Color.YELLOW);
	    	    }
	    	} else {
	    	    elements[j[0] + 1].setValueText(elements[j[0]].getValue());
	    	    elements[j[0] + 1].setHeight(elements[j[0]].getHeight());
	    	    elements[j[0]].getRectangle().setFill(Color.YELLOW);
	    	    j[0]--;
	    	}

	    	int currentJ = j[0];
	    	int currentI = i[0];
	    	double currentKey = key[0];
	    	double currentKeyHeight = keyHeight[0];

	    	Runnable action = () -> {
	    		if (currentJ >= 0 && elements[currentJ].getValue() > currentKey) {
	    	        elements[currentJ + 1].setValueText(elements[currentJ].getValue());
	    	        elements[currentJ + 1].setHeight(elements[currentJ].getHeight());
	    	        elements[currentJ].getRectangle().setFill(Color.YELLOW);
	    	    } else {
	    	        elements[currentJ + 1].setValueText(currentKey);
	    	        elements[currentJ + 1].setHeight(currentKeyHeight);
	    	        for (SortElement element : elements) {
	    	            element.getRectangle().setFill(Color.LIGHTGRAY);
	    	        }
	    	        elements[currentI].getRectangle().setFill(Color.YELLOW);
	    	    }

	    		if(isManualStepping) {
	    			if (!redoStack.isEmpty()) {
	    	        	nextButton.setDisable(false);
	    	        }
	    			else {
	    				nextButton.setDisable(true);
	    			}
	    		}
	    	};

	    	if(isManualStepping) {
	    		undoStack.push(action);

	    		if(undoStack.isEmpty()) {
	    	    	prevButton.setDisable(true);
	    	    }
	    	    else {
	    	    	prevButton.setDisable(false);
	    	    }
	    	}

	    	if (!isPaused && !isManualStepping) {
	    	    action.run();
	    	}
	    }));

	    key[0] = elements[i[0]].getValue();
	    keyHeight[0] = elements[i[0]].getHeight();
	    j[0] = i[0] - 1;
	    elements[i[0]].getRectangle().setFill(Color.YELLOW);
//	    timeline.play();
	    startButton.setOnAction(event -> {
	        if (timeline != null) {
	            timeline.play();
	        }
	    });
	}
}
