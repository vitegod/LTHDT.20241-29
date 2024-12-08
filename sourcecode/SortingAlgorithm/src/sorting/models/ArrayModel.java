package sorting.models;

import java.util.ArrayList;
import java.util.List;

public class ArrayModel {
    private int[] array;
    private List<int[]> steps;

    public ArrayModel(int[] array) {
        this.array = array;
        this.steps = new ArrayList<>();
    }

    public int[] getArray() {
        return array;
    }

    public List<int[]> getSteps() {
        return steps;
    }

    public void addStep(int[] step) {
        steps.add(step.clone());
    }
}

