/*write a java program that uses simple technique to search a key,to sort n elements,
 run the program for varied values of n>5000 and record the time taken to search /sort 
 plot a graph
of the time taken v/s n the elements can be read from a file or can be generated using the random number generator
 method: brute force-non recursive algo implementation, sequential search and selection sort */

 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.Random;
import java.util.Scanner;

public class SearchAndSort {

    // Sequential search
    public static int sequentialSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    // Selection sort
    public static void selectionSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    // Main method
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);

       
        int key =-1; // Key is always negative, ensuring it's never found;

        int[] sizes = { 55000 ,100000, 150000, 200000, 250000, 300000, 350000, 400000, 430000, 450000 }; // Different sizes of arrays
        Random rand = new Random();

        long[] searchTimes = new long[sizes.length];
        long[] sortTimes = new long[sizes.length];

        System.out.println("Size\tSearch Time(ns)\tSort Time (ns)");

        for (int i = 0; i < sizes.length; i++) {
            int size = sizes[i];
            int[] arr = new int[size];

            // Generate random array
            for (int j = 0; j < size; j++) {
                arr[j] = rand.nextInt(size);
            }

            // Measure search time
            long startTime = System.nanoTime();
            int ind=sequentialSearch(arr, key);
            long searchTime = System.nanoTime() - startTime;
            searchTimes[i] = searchTime;


            // Measure sort time
            startTime = System.nanoTime();
            selectionSort(arr);
            long sortTime = System.nanoTime() - startTime;
            sortTimes[i] = sortTime;

            // Print the results in table format
            System.out.println(size + "\t" + searchTime + "\t\t\t" + sortTime);
        }

        scanner.close();
         
         // Export data to CSV file
         try (FileWriter writer = new FileWriter("timing_data.csv")) {
             writer.write("Size,Search Time (ns),Sort Time (ns)\n");
             for (int i = 0; i < sizes.length; i++) {
                 writer.write(sizes[i] + "," + searchTimes[i] + "," + sortTimes[i] + "\n");
             }
             System.out.println("Data exported to timing_data.csv");
         } catch (IOException e) {
             e.printStackTrace();
         }
     }
 }
 