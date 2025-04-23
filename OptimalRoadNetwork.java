
public class OptimalRoadNetwork {
    // Edge class to represent a road between two towns
    static class Edge {
        int source, destination, weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    // Union-Find data structure to detect cycles
    static class UnionFind {
        int[] parent, rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) return;

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // Find the minimum weight edge that doesn't create a cycle
    private static Edge findMinEdge(int[][] matrix, boolean[][] used, UnionFind uf, int n) {
        int minWeight = Integer.MAX_VALUE;
        Edge minEdge = null;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!used[i][j] && matrix[i][j] != 0 && matrix[i][j] < minWeight) {
                    int x = uf.find(i);
                    int y = uf.find(j);
                    if (x != y) {  // Check if edge creates a cycle
                        minWeight = matrix[i][j];
                        minEdge = new Edge(i, j, minWeight);
                    }
                }
            }
        }
        return minEdge;
    }

    // Kruskal's algorithm using adjacency matrix directly
    public static Edge[] kruskalMST(int[][] adjacencyMatrix, int numTowns) {
        Edge[] result = new Edge[numTowns - 1];
        UnionFind uf = new UnionFind(numTowns);
        boolean[][] used = new boolean[numTowns][numTowns];
        int edgesAdded = 0;

        while (edgesAdded < numTowns - 1) {
            Edge minEdge = findMinEdge(adjacencyMatrix, used, uf, numTowns);
            if (minEdge != null) {
                result[edgesAdded] = minEdge;
                used[minEdge.source][minEdge.destination] = true;
                used[minEdge.destination][minEdge.source] = true;
                uf.union(minEdge.source, minEdge.destination);
                edgesAdded++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Define the 5 towns
        String[] towns = {
            "Town A",
            "Town B",
            "Town C",
            "Town D",
            "Town E"
        };
        
        int numTowns = towns.length;
        
        // Create and initialize adjacency matrix
        // 0 means no direct connection between towns
        int[][] adjacencyMatrix = new int[numTowns][numTowns];
        
        // Adding roads between towns with their distances
        adjacencyMatrix[0][1] = adjacencyMatrix[1][0] = 4;  // A-B: 4km
        adjacencyMatrix[0][2] = adjacencyMatrix[2][0] = 8;  // A-C: 8km
        adjacencyMatrix[1][2] = adjacencyMatrix[2][1] = 2;  // B-C: 2km
        adjacencyMatrix[1][3] = adjacencyMatrix[3][1] = 6;  // B-D: 6km
        adjacencyMatrix[2][3] = adjacencyMatrix[3][2] = 3;  // C-D: 3km
        adjacencyMatrix[2][4] = adjacencyMatrix[4][2] = 9;  // C-E: 9km
        adjacencyMatrix[3][4] = adjacencyMatrix[4][3] = 5;  // D-E: 5km

        // Display the original road network
        System.out.println("Original Road Network (Adjacency Matrix):");
        System.out.println("--------------------------------------");
        System.out.print("     ");
        for (String town : towns) {
            System.out.printf("%-6s", town);
        }
        System.out.println("\n");
        
        for (int i = 0; i < numTowns; i++) {
            System.out.printf("%-5s", towns[i]);
            for (int j = 0; j < numTowns; j++) {
                System.out.printf("%-6d", adjacencyMatrix[i][j]);
            }
            System.out.println();
        }

        // Find and display the optimal road network (MST)
        System.out.println("\nOptimal Road Network (Minimum Spanning Tree):");
        System.out.println("-------------------------------------------");
        Edge[] optimalNetwork = kruskalMST(adjacencyMatrix, numTowns);
        int totalLength = 0;
        
        for (Edge edge : optimalNetwork) {
            System.out.println(towns[edge.source] + " - " + towns[edge.destination] + 
                             ": " + edge.weight + " km");
            totalLength += edge.weight;
        }
        
        System.out.println("\nTotal road network length: " + totalLength + " km");
    }
} 