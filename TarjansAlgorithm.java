import java.util.*;

public class TarjansAlgorithm {
    private int time = 0;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (List<Integer> conn : connections) {
            int u = conn.get(0), v = conn.get(1);
            graph[u].add(v);
            graph[v].add(u);
        }

        int[] disc = new int[n];
        int[] low = new int[n];
        Arrays.fill(disc, -1);

        for (int i = 0; i < n; i++) {
            if (disc[i] == -1) {
                dfs(i, -1, graph, disc, low, result);
            }
        }
        return result;
    }

    private void dfs(int u, int parent, List<Integer>[] graph, int[] disc, int[] low, List<List<Integer>> result) {
        disc[u] = low[u] = time++;
        for (int v : graph[u]) {
            if (v == parent) continue;
            if (disc[v] == -1) {
                dfs(v, u, graph, disc, low, result);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    result.add(Arrays.asList(u, v));
                }
            } else {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }

    public static void main(String[] args) {
        int n = 13;
        List<List<Integer>> connections = new ArrayList<>();
        connections.add(Arrays.asList(1, 2));
        connections.add(Arrays.asList(1, 4));
        connections.add(Arrays.asList(2,3));
        connections.add(Arrays.asList(4, 3));
        connections.add(Arrays.asList(1, 3));
        connections.add(Arrays.asList(4, 5));
        connections.add(Arrays.asList(1, 3));
        connections.add(Arrays.asList(5,6));
        connections.add(Arrays.asList(6,7));
        connections.add(Arrays.asList(6,9));
        connections.add(Arrays.asList(7,8));
        connections.add(Arrays.asList(9,8));
        connections.add(Arrays.asList(8,10));
        connections.add(Arrays.asList(10,11));
        connections.add(Arrays.asList(10,12));
        TarjansAlgorithm ta = new TarjansAlgorithm();
        List<List<Integer>> bridges = ta.criticalConnections(n, connections);
        System.out.println("Critical Connections: " + bridges);
    }
}