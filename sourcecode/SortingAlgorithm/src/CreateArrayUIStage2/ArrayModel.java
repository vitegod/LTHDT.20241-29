package CreateArrayUIStage2;

import java.util.ArrayList;

public class ArrayModel {
	private int nbElements;
	private int pivotPosition;
	private ArrayList<Double> array = new ArrayList<Double>();
	
	public int getNbElements() {
		return nbElements;
	}
	
	public void setNbElements(int nbElements) {
		this.nbElements = nbElements;
	}
	
	public int getPivotPosition() {
		return pivotPosition;
	}
	
	public void setPivotPosition(int pivotPosition) {
		this.pivotPosition = pivotPosition;
	}
	
	public ArrayList<Double> getArray() {
		return array;
	}
	
	public void setArray(ArrayList<Double> array) {
		this.array = array;
	}
	
}
