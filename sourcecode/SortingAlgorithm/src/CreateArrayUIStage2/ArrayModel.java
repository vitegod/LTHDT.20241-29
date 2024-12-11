package CreateArrayUIStage2;

public class ArrayModel {
	private int nbElements;
	private String pivotPosition;
	private double[] array;
	
	public int getNbElements() {
		return nbElements;
	}
	
	public void setNbElements(int nbElements) {
		this.nbElements = nbElements;
	}
	
	public String getPivotPosition() {
		return pivotPosition;
	}
	
	public void setPivotPosition(String pivotPosition) {
		this.pivotPosition = pivotPosition;
	}
	
	public double[] getArray() {
		return array;
	}
	
	public void setArray(double[] array) {
		this.array = new double[this.getNbElements()];
		this.array = array;
	}
}
