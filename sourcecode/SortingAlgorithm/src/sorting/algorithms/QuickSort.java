package sorting.algorithms;

import java.util.List;

public class QuickSort {
    public void sort(int[] a) {
        quickSort(a, 0, a.length - 1, null); 
    }

    public void sort(int[] a, List<int[]> steps) {
        quickSort(a, 0, a.length - 1, steps);
    }

    private void quickSort(int[] a, int left, int right, List<int[]> steps) {
        if (left < right) {
            int pivotIndex = partition(a, left, right);
            if (steps != null) {
                steps.add(a.clone()); 
            }
            quickSort(a, left, pivotIndex - 1, steps);
            quickSort(a, pivotIndex + 1, right, steps);
        }
    }

    private int partition(int[] a, int left, int right) {
        int pivotValue = a[left];
        int pivotIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (a[i] < pivotValue) {
                pivotIndex++;
                swap(a, i, pivotIndex);
            }
        }
        swap(a, left, pivotIndex);
        return pivotIndex;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
