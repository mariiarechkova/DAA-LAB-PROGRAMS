import java.util.*; // Import all utility classes (Scanner, List, etc.)

public class FloydWarshall { // Main class definition for Floyd Warshall algorithm implementation
    private static final int INF = 99999; // Constant to represent infinity/no direct path
    private int V; // Number of vertices/stations in the graph
    private int[][] dist; // 2D array to store shortest distances between vertices
    private int[][] next; // 2D array to store next vertex in shortest path for path reconstruction

    public FloydWarshall(int vertices) { // Constructor taking number of vertices as parameter
        this.V = vertices; // Initialize number of vertices
        this.dist = new int[V][V]; // Create distance matrix
        this.next = new int[V][V]; // Create next vertex matrix
        
        // Initialize the next matrix for path reconstruction
        for (int i = 0; i < V; i++) { // Iterate through rows
            for (int j = 0; j < V; j++) { // Iterate through columns
                if (i != j) { // If not the same vertex
                    next[i][j] = j; // Initialize next vertex as destination
                }
            }
        }
    }

    public void findShortestPaths(int[][] graph) { // Method to find all pairs shortest paths
        // Initialize distance matrix and next matrix
        for (int i = 0; i < V; i++) { // Iterate through rows
            for (int j = 0; j < V; j++) { // Iterate through columns
                dist[i][j] = graph[i][j]; // Copy initial distances from input graph
            }
        }

        // Core Floyd-Warshall algorithm
        for (int k = 0; k < V; k++) { // Iterate through intermediate vertices
            for (int i = 0; i < V; i++) { // Iterate through source vertices
                for (int j = 0; j < V; j++) { // Iterate through destination vertices
                    if (dist[i][k] != INF && dist[k][j] != INF  // If path through k exists
                        && dist[i][k] + dist[k][j] < dist[i][j]) { // If new path is shorter
                        dist[i][j] = dist[i][k] + dist[k][j]; // Update distance
                        next[i][j] = next[i][k]; // Update next vertex in path
                    }
                }
            }
        }

        // Check for negative cycles
        for (int i = 0; i < V; i++) { // Iterate through vertices
            if (dist[i][i] < 0) { // If distance to self is negative
                System.out.println("Warning: Graph contains negative weight cycle!"); // Print warning
                return; // Exit method
            }
        }
    }

    // Method to reconstruct path between two vertices
    public List<Integer> getPath(int source, int destination) { // Method to get path between two vertices
        if (dist[source][destination] == INF) { // If no path exists
            return new ArrayList<>(); // Return empty path
        }

        List<Integer> path = new ArrayList<>(); // Create list to store path
        path.add(source); // Add source vertex to path
        
        while (source != destination) { // While not reached destination
            source = next[source][destination]; // Move to next vertex in path
            path.add(source); // Add vertex to path
        }
        
        return path; // Return completed path
    }

    public void displayResults() { // Method to display shortest path matrix
        System.out.println("\nShortest Travel Times Matrix:"); // Print header
        for (int i = 0; i < V; i++) { // Iterate through rows
            for (int j = 0; j < V; j++) { // Iterate through columns
                if (dist[i][j] == INF) { // If no path exists
                    System.out.printf("%7s", "INF"); // Print INF
                } else { // If path exists
                    System.out.printf("%7d", dist[i][j]); // Print distance
                }
            }
            System.out.println(); // New line after each row
        }
    }

    public static void main(String[] args) { // Main method - entry point of program
        try (Scanner scanner = new Scanner(System.in)) { // Create scanner for input with try-with-resources
            System.out.print("Enter number of vertices/stations: "); // Prompt for number of vertices
            int V = scanner.nextInt(); // Read number of vertices
            
            if (V <= 0) { // Validate input
                throw new IllegalArgumentException("Number of vertices must be positive"); // Throw error if invalid
            }

            int[][] graph = new int[V][V]; // Create adjacency matrix
            
            System.out.println("\nEnter the adjacency matrix (Enter " + INF + " if no direct connection):"); // Print input instructions
            System.out.println("For each vertex, enter " + V + " values representing distances to other vertices"); // More instructions
            
            for (int i = 0; i < V; i++) { // Iterate through rows
                for (int j = 0; j < V; j++) { // Iterate through columns
                    if (i == j) { // If same vertex
                        graph[i][j] = 0; // Set distance to self as 0
                        continue; // Skip to next iteration
                    }
                    System.out.printf("Enter distance from vertex %d to %d: ", i, j); // Prompt for distance
                    graph[i][j] = scanner.nextInt(); // Read distance
                }
            }

            FloydWarshall fw = new FloydWarshall(V); // Create FloydWarshall instance
            fw.findShortestPaths(graph); // Find all shortest paths
            fw.displayResults(); // Display results

            // Demonstrate path finding
            System.out.println("\nWould you like to find a specific path? (y/n)"); // Ask user if they want to find specific paths
            scanner.nextLine(); // Consume newline
            String response = scanner.nextLine(); // Read response
            
            while (response.toLowerCase().startsWith("y")) { // While user wants to continue
                System.out.print("Enter source vertex: "); // Prompt for source
                int source = scanner.nextInt(); // Read source
                System.out.print("Enter destination vertex: "); // Prompt for destination
                int dest = scanner.nextInt(); // Read destination
                
                if (source >= 0 && source < V && dest >= 0 && dest < V) { // Validate vertices
                    List<Integer> path = fw.getPath(source, dest); // Get shortest path
                    if (!path.isEmpty()) { // If path exists
                        System.out.println("Shortest path: " + path); // Print path
                        System.out.println("Total distance: " + fw.dist[source][dest]); // Print total distance
                    } else { // If no path exists
                        System.out.println("No path exists between these vertices"); // Print error message
                    }
                } else { // If vertices are invalid
                    System.out.println("Invalid vertices!"); // Print error message
                }
                
                System.out.println("\nFind another path? (y/n)"); // Ask if user wants to continue
                scanner.nextLine(); // Consume newline
                response = scanner.nextLine(); // Read response
            }
            
        } catch (Exception e) { // Catch any exceptions
            System.out.println("Error: " + e.getMessage()); // Print error message
        }
    }
}