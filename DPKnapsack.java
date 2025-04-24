import java.util.Scanner;  // Import Scanner class for user input

public class DPKnapsack {
    // Static variables for number of items (n), capacity (m), profits array (p), and weights array (w)
    static int n, m, p[], w[];

    static void knapsackDP() {
        // Create a 2D array to store the maximum values for different subproblems
        int[][] v = new int[n + 1][m + 1];

        // Iterate through each item
        for (int i = 0; i <= n; i++) {
            // For each capacity from 0 to m
            for (int j = 0; j <= m; j++) {
                // Initialize base cases where either no items or no capacity
                if (i == 0 || j == 0)
                    v[i][j] = 0;
                // If current item's weight is more than current capacity
                else if (j < w[i])
                    v[i][j] = v[i - 1][j];  // Take the value without including current item
                else
                    // Take maximum of (excluding current item) or (including current item + remaining capacity value)
                    v[i][j] = max(v[i - 1][j], p[i] + v[i - 1][j - w[i]]);
            }
        }

        // Print the dynamic programming table
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++)
                System.out.print(v[i][j] + " ");
            System.out.println();
        }

        // Print the optimal solution and track back selected items
        System.out.println("OPTIMAL PROFIT IS:" + v[n][m]);
        System.out.print("Products selected for shelf that yields maximum profit are:");
        
        // Backtrack to find selected items
        while (n != 0) {
            // If current item was included in optimal solution
            if (v[n][m] != v[n - 1][m]) {
                System.out.print(n + " ");  // Print the item number
                m = m - w[n];  // Reduce remaining capacity by item's weight
            }
            n--;  // Move to previous item
        }
    }

    // Helper function to return maximum of two numbers
    static int max(int a, int b) {
        return (a > b ? a : b);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);  // Create Scanner object for input
        
        System.out.println("Enter the no. of products");
        n = s.nextInt();  // Read number of products
        
        // Initialize arrays for profits and weights with size n+1 (1-based indexing)
        p = new int[n + 1];
        w = new int[n + 1];
        
        System.out.println("Enter the weights of n products");
        // Read weights for each product
        for (int i = 1; i <= n; i++)
            w[i] = s.nextInt();

        System.out.println("Enter the profits of n products");
        // Read profits for each product
        for (int i = 1; i <= n; i++)
            p[i] = s.nextInt();

        System.out.println("Enter the capacity of shelf(Knapsack)");
        m = s.nextInt();  // Read shelf capacity
        
        knapsackDP();  // Solve the knapsack problem using dynamic programming
    }
}
