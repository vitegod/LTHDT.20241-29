package VisualizationUI;

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class SortElement {
    private Rectangle rectangle;
    private Text valueText;      

    public SortElement(double value, double size) {
        rectangle = new Rectangle(size, size);
        rectangle.setFill(Color.LIGHTGRAY);
        rectangle.setStroke(Color.GRAY);

        valueText = new Text(formatValue(value));
        valueText.setFill(Color.BLACK);
        valueText.setFont(Font.font("System", 15));
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

    public void updateValue(double value) {
        this.valueText.setText(formatValue(value));
    }


    public void setColor(Color color) {
        rectangle.setFill(color);
    }

    private String formatValue(double value) {
        return String.format("%.2f", value);
    }
}
