class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        if (rank[px] < rank[py]) {
            parent[px] = py;
        } else if (rank[px] > rank[py]) {
            parent[py] = px;
        } else {
            parent[py] = px;
            rank[px]++;
        }
    }
    public static void main(String[] args) {
        UnionFind uf = new UnionFind(5);
        for (int i = 0; i < 5; i++) {
            System.out.println("Parent of " + i + ": " + uf.find(i));
        }

        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(3, 4);

        System.out.println("Parent of 0: " + uf.find(0));
        System.out.println("Parent of 1: " + uf.find(1));
        System.out.println("Parent of 2: " + uf.find(2));
        System.out.println("Parent of 3: " + uf.find(3));
        System.out.println("Parent of 4: " + uf.find(4));

        uf.union(2, 4);
        System.out.println("After union(2, 4):");
        for (int i = 0; i < 5; i++) {
            System.out.println("Parent of " + i + ": " + uf.find(i));
        }

        System.out.println("After unions:");
        for (int i = 0; i < 5; i++) {
            System.out.println("Parent of " + i + ": " + uf.find(i));
        }
    }
}