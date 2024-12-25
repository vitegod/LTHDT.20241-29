package VisualizationUI;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortElement {
    private Rectangle rectangle;
    private Text valueText;
    private double width = 40;
    private double height;

    public SortElement(double value, double[] sortedArray) {
        calculateHeight(value, sortedArray);

        rectangle = new Rectangle(width, height);
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setStroke(Color.GRAY);

        valueText = new Text(String.valueOf(value));
        valueText.setFill(Color.BLACK);
        valueText.setFont(Font.font("System", 18));
    }

    private void calculateHeight(double value, double[] sortedArray) {
        List<Double> sortedList = Arrays.stream(sortedArray).boxed().collect(Collectors.toList());
        int index = sortedList.indexOf(value);
        
        if (index != -1) {
            this.height = 40 + index * 10;
        } else {
            this.height = 40; 
        }
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public Text getValueText() {
        return valueText;
    }

    public double getValue() {
        return Double.parseDouble(valueText.getText());
    }

    public void setValueText(Double value) {
        this.valueText.setText(String.valueOf(value));
    }

    public void setColor(Color color) {
        rectangle.setFill(color);
    }

    public void setHeight(double height) {
        this.height = height;
        rectangle.setHeight(height);
    }

    public double getHeight() {
        return this.height;
    }
}