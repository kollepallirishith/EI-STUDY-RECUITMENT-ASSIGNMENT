import java.util.Arrays;


interface SortingStrategy {
    void sort(int[] dataset);
}


class InsertionSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] dataset) {
        for (int i = 1; i < dataset.length; i++) {
            int key = dataset[i];
            int j = i - 1;
            while (j >= 0 && dataset[j] > key) {
                dataset[j + 1] = dataset[j];
                j = j - 1;
            }
            dataset[j + 1] = key;
        }
        System.out.println("InsertionSort applied: " + Arrays.toString(dataset));
    }
}

// 3. Concrete Strategy: MergeSort
class MergeSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] dataset) {
        mergeSort(dataset, 0, dataset.length - 1);
        System.out.println("MergeSort applied: " + Arrays.toString(dataset));
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }
}


class BubbleSortStrategy implements SortingStrategy {
    @Override
    public void sort(int[] dataset) {
        int n = dataset.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (dataset[j] > dataset[j + 1]) {
                    int temp = dataset[j];
                    dataset[j] = dataset[j + 1];
                    dataset[j + 1] = temp;
                }
            }
        }
        System.out.println("BubbleSort applied: " + Arrays.toString(dataset));
    }
}


class SorterContext {
    private SortingStrategy strategy;

    public void setSortingStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sortData(int[] dataset) {
        if (strategy != null) {
            strategy.sort(dataset);
        } else {
            System.out.println("No sorting strategy set.");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        SorterContext context = new SorterContext();
        int[] dataset = {38, 27, 43, 3, 9, 82, 10};

        // Using MergeSort
        context.setSortingStrategy(new MergeSortStrategy());
        context.sortData(dataset.clone());  // Pass a copy of the dataset to maintain original order

        // Using InsertionSort
        context.setSortingStrategy(new InsertionSortStrategy());
        context.sortData(dataset.clone());

        // Using BubbleSort
        context.setSortingStrategy(new BubbleSortStrategy());
        context.sortData(dataset.clone());
    }
}
