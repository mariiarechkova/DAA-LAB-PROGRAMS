import java.util.*;

class Graph {
    private int V; // Number of vertices
    private List<List<int[]>> adj; // Adjacency list

    public Graph(int v) {
        V = v;
        adj = new ArrayList<>(v);
        for (int i = 0; i < v; i++) {
            adj.add(new ArrayList<>());
        }
    }

    // Method to add an edge to the graph
    public void addEdge(int u, int v, int weight) {
        adj.get(u).add(new int[]{v, weight});
        adj.get(v).add(new int[]{u, weight}); // for undirected graph
    }

    // Method to implement Prim's Algorithm
    public void primMST() {
        // To track the vertices included in MST
        boolean[] inMST = new boolean[V];
        // Min-heap to select the edge with the smallest weight
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Start from the first vertex
        pq.add(new int[]{0, 0}); // {vertex, weight}
        int totalCost = 0;

        while (!pq.isEmpty()) {
            // Choose the minimum weight edge
            int[] edge = pq.poll();
            int u = edge[0];
            int weight = edge[1];

            // If this vertex is already in MST, continue
            if (inMST[u]) continue;

            // Include the vertex in MST
            inMST[u] = true;
            totalCost += weight;

            // Explore the edges of the selected vertex
            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int w = neighbor[1];

                // Only consider edges to vertices not in MST
                if (!inMST[v]) {
                    pq.add(new int[]{v, w});
                }
            }
        }

        System.out.println("Total cost of minimum spanning tree: " + totalCost);
    }
}

public class WaterSupplyNetwork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices");
        int n = sc.nextInt();
        Graph graph = new Graph(n); // Example with 5 vertices (areas)
        System.out.println("Enter number of edges");
        int e = sc.nextInt();
        System.out.println("Enter edges");// u, v are vertices, weight is the cost of the edge
        for (int i = 0; i < e; i++) {
            System.out.print("Enter initial vertex 'u' (0 to " + (n - 1) + "): ");
            int u = sc.nextInt();
            System.out.print("Enter final vertex 'v' (0 to " + (n - 1) + "): ");
            int v = sc.nextInt();
            System.out.print("Enter weight of the edge: ");
            int weight = sc.nextInt();
            graph.addEdge(u, v, weight);
        }
        graph.primMST(); // Execute Prim's algorithm
    }
}
