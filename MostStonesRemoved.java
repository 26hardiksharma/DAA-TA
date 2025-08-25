import java.util.*;

public class MostStonesRemoved {
	static class UnionFind {
		private Map<Integer, Integer> parent = new HashMap<>();
		public int find(int x) {
			if (!parent.containsKey(x)) parent.put(x, x);
			if (parent.get(x) != x) parent.put(x, find(parent.get(x)));
			return parent.get(x);
		}
		public void union(int x, int y) {
			parent.put(find(x), find(y));
		}
		public int count() {
			Set<Integer> roots = new HashSet<>();
			for (int x : parent.keySet()) roots.add(find(x));
			return roots.size();
		}
	}

	public static int removeStones(int[][] stones) {
		UnionFind uf = new UnionFind();
		for (int[] stone : stones) {
			uf.union(stone[0], stone[1] + 10001);
		}
		return stones.length - uf.count();
	}

	public static void main(String[] args) {
		int[][] stones = {
			{0, 0}, {0, 2}, {1, 3}, {3, 1}, {3, 2}, {4, 3}
		};
		int result = removeStones(stones);
		System.out.println("Maximum stones that can be removed: " + result);

	}
}

