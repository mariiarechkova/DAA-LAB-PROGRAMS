
import java.util.Random;

public class QuickSort {

    static int partition(int a[], int l, int r) {
        int pivot = a[l];
        int i = l, j = r;

        while (i < j) {
            // Find element greater than pivot from left
            while (i <= r && a[i] <= pivot) {
                i++;
            }

            // Find element less than pivot from right
            while (j >= l && a[j] > pivot) {
                j--;
            }

            // Swap if positions haven't crossed
            if (i < j) {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        // Place pivot in its correct position
        int temp = a[l];
        a[l] = a[j];
        a[j] = temp;

        return j;
    }

    static void quicksort(int a[], int l, int r) {
        if (l < r) {
            int s = partition(a, l, r);
            quicksort(a, l, s - 1);
            quicksort(a, s + 1, r);
        }
    }

    public static void main(String args[]) {
        int[] sizes = {5000, 6000, 7000, 8000, 9000, 10000};

        System.out.println("----------------------------------------------------------------------");
        System.out.println("n       | Best Case Time (ns) | Avg Case Time (ns) | Worst Case Time (ns)");
        System.out.println("----------------------------------------------------------------------");

        for (int n : sizes) {
            int[] a = new int[n];
            Random r = new Random();

            // Best Case: Median pivot (approximate with middle element)
            // Best Case: Balanced partitioning
            for (int i = 0; i < n; i++) {
                // Pattern that creates more balanced partitions
                if (i % 2 == 0) {
                    a[i] = i / 2;
                } else {
                    a[i] = n - i / 2;
                }
            }
            long startTime = System.nanoTime();
            quicksort(a, 0, n - 1);
            long endTime = System.nanoTime();
            long bestCaseTime = endTime - startTime;

            // Average Case: Random array
            for (int i = 0; i < n; i++) {
                a[i] = r.nextInt(100000);
            }
            startTime = System.nanoTime();
            quicksort(a, 0, n - 1);
            endTime = System.nanoTime();
            long avgCaseTime = endTime - startTime;

            // Worst Case: Already sorted array (when picking first element as pivot)
            for (int i = 0; i < n; i++) {
                a[i] = i;  // For our partition method, already sorted is worst case
            }
            startTime = System.nanoTime();
            quicksort(a, 0, n - 1);
            endTime = System.nanoTime();
            long worstCaseTime = endTime - startTime;

            System.out.printf("%-7d | %-18d | %-18d | %-18d\n", n, bestCaseTime, avgCaseTime, worstCaseTime);
        }

        System.out.println("----------------------------------------------------------------------");
    }
}
