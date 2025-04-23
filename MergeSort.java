import java.util.Random;

public class MergeSort {
    // Merge function to combine two subarrays
    public static void merge(int[] array, int left, int middle, int right) {
        int n1 = middle - left + 1; // Size of the left subarray
        int n2 = right - middle;    // Size of the right subarray

        // Temporary arrays to hold the data of the left and right subarrays
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        // Copying the data to the temporary arrays
        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[middle + 1 + j];
        }

        int i = 0, j = 0, k = left; // Initial indices of left, right, and merged subarrays

        // Merging the temporary arrays back into the original array
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

        // Copying any remaining elements of leftArray
        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        // Copying any remaining elements of rightArray
        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    // MergeSort function to sort the array
    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2; // Finding the middle point

            // Sorting the first and second halves
            mergeSort(array, left, middle);
            mergeSort(array, middle + 1, right);

            // Merging the sorted halves
            merge(array, left, middle, right);
        }
    }

    public static void main(String[] args) {
        int[] sizes = {5000, 6000, 7000, 8000, 9000, 10000}; // Array sizes to test

        System.out.println("----------------------------------------------------------------------");
        System.out.println("n       | Best Case Time (ns) | Avg Case Time (ns) | Worst Case Time (ns)");
        System.out.println("----------------------------------------------------------------------");

        for (int n : sizes) {
            int[] a = new int[n];
            Random r = new Random();

            // Best Case: Sorted array (Best Case: O(n log n))
            for (int i = 0; i < n; i++) {
                a[i] = i;
            }
            long startTime = System.nanoTime();
            mergeSort(a, 0, n - 1);
            long endTime = System.nanoTime();
            long bestCaseTime = endTime - startTime;

            // Average Case: Shuffled array (Average Case: O(n log n))
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt(10000);
            }
            startTime = System.nanoTime();
            mergeSort(a, 0, n - 1);
            endTime = System.nanoTime();
            long avgCaseTime = endTime - startTime;

            // Worst Case: Reverse sorted array (Worst Case: O(n log n))
            for (int i = 0; i < n; i++) {
                a[i] = n - i;
            }
            startTime = System.nanoTime();
            mergeSort(a, 0, n - 1);
            endTime = System.nanoTime();
            long worstCaseTime = endTime - startTime;

            System.out.printf("%-7d | %-18d | %-18d | %-18d\n", n, bestCaseTime, avgCaseTime, worstCaseTime);
        }

        System.out.println("----------------------------------------------------------------------");
    }
}
