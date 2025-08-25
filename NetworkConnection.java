import java.util.*;

public class NetworkConnection {
    public int makeConnected(int n, int[][] edges) {
        if (edges.length < n - 1) return -1; 

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int components = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                components++;
                dfs(i, graph, visited);
            }
        }
        return components - 1;
    }

    private void dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) dfs(neighbor, graph, visited);
        }
    }
        public static void main(String[] args) {
            int n = 9;
            int[][] edges = {
                {0,1},{0,2},{0,3},{1,2},{2,3},{4,5},{5,6},{7,8}
            };
            NetworkConnection nc = new NetworkConnection();
            int result = nc.makeConnected(n, edges);
            System.out.println("Minimum number of operations: " + result);
        }
}