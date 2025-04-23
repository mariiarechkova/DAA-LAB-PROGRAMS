import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShelfOptimizer {
    
    static class Product {
        String name;
        int weight;
        int profit;
        
        Product(String name, int weight, int profit) {
            this.name = name;
            this.weight = weight;
            this.profit = profit;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get shelf capacity
        System.out.print("Enter shelf capacity: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        // Get number of products
        System.out.print("Enter number of products: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Clear buffer
        
        // Create product list
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.println("\nProduct " + (i+1) + ":");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Weight: ");
            int weight = scanner.nextInt();
            System.out.print("Profit: ");
            int profit = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
            
            products.add(new Product(name, weight, profit));
        }
        
        // Solve knapsack problem
        List<Product> selected = knapsackSolver(products, capacity);
        
        // Display results
        System.out.println("\n--- Optimal Product Selection ---");
        int totalWeight = 0;
        int totalProfit = 0;
        
        System.out.println("Selected products:");
        for (Product p : selected) {
            System.out.printf("- %s (Weight: %d, Profit: %d)\n", p.name, p.weight, p.profit);
            totalWeight += p.weight;
            totalProfit += p.profit;
        }
        
        System.out.printf("\nTotal profit: %d\n", totalProfit);
        System.out.printf("Total weight: %d/%d\n", totalWeight, capacity);
        
        scanner.close();
    }
    
    // Knapsack solver using dynamic programming with graph representation
    public static List<Product> knapsackSolver(List<Product> products, int capacity) {
        int n = products.size();
        
        // Create weights and profits arrays
        int[] weights = new int[n];
        int[] profits = new int[n];
        for (int i = 0; i < n; i++) {
            weights[i] = products.get(i).weight;
            profits[i] = products.get(i).profit;
        }
        
        // Create DP table
        int[][] dp = new int[n+1][capacity+1];
        
        // Fill the DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i-1] <= w) {
                    // Max of: (1) take current item, (2) skip current item
                    dp[i][w] = Math.max(
                        profits[i-1] + dp[i-1][w - weights[i-1]], 
                        dp[i-1][w]
                    );
                } else {
                    // Can't take current item
                    dp[i][w] = dp[i-1][w];
                }
            }
        }
        
        // Backtrack to find selected items
        List<Product> selectedProducts = new ArrayList<>();
        int w = capacity;
        for (int i = n; i > 0; i--) {
            // If this product was selected (current cell differs from the cell above)
            if (dp[i][w] != dp[i-1][w]) {
                selectedProducts.add(products.get(i-1));
                w -= weights[i-1];
            }
        }
        
        return selectedProducts;
    }
}