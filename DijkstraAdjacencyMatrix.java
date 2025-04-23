import java.util.Arrays;
import java.util.Scanner;

class DijkstraAdjacencyMatrix {

    public static void dijkstra(int[][] graph, int src, int numNodes) {
        int[] dist = new int[numNodes]; // Stores shortest distances from src
        boolean[] visited = new boolean[numNodes]; // Tracks visited nodes

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0; // Distance to itself is 0

        // Loop to find shortest path for all nodes
        for (int i = 0; i < numNodes - 1; i++) {
            int u = selectMinVertex(dist, visited, numNodes);
            visited[u] = true;

            // Update distances for adjacent nodes
            for (int v = 0; v < numNodes; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE 
                    && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print shortest distances
        System.out.println("Shortest distances from node " + src + ":");
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Distance to node " + i + ": " + (dist[i] == Integer.MAX_VALUE ? "No path" : dist[i]));
        }
    }

    // Helper method to select the vertex with minimum distance
    public static int selectMinVertex(int[] dist, boolean[] visited, int numNodes) {
        int minValue = Integer.MAX_VALUE, minIndex = -1;
        for (int i = 0; i < numNodes; i++) {
            if (!visited[i] && dist[i] < minValue) {
                minValue = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input number of nodes
        System.out.println("Enter the number of cities:");
        int numCities = sc.nextInt();
        
        // Initialize adjacency matrix
        int[][] graph = new int[numCities][numCities];

        // Input number of roads
        System.out.println("Enter the number of roads:");
        int numRoads = sc.nextInt();

        System.out.println("Enter the roads (format: City1 City2 Weight):");
        for (int i = 0; i < numRoads; i++) {
            int city1 = sc.nextInt();
            int city2 = sc.nextInt();
            int weight = sc.nextInt();
            graph[city1][city2] = weight;
            graph[city2][city1] = weight; // Bidirectional roads
        }

        // Input source city
        System.out.println("Enter the source city index:");
        int sourceCity = sc.nextInt();

        // Solve using Dijkstra’s Algorithm
        dijkstra(graph, sourceCity, numCities);

        sc.close();
    }
}