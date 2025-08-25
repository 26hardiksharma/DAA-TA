import java.util.*;

public class MergeAccounts {
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, String> emailToName = new HashMap<>();
		Map<String, String> parent = new HashMap<>();
		for (List<String> account : accounts) {
			String name = account.get(0);
			for (int i = 1; i < account.size(); i++) {
				String email = account.get(i);
				parent.putIfAbsent(email, email);
				emailToName.put(email, name);
			}
		}
		for (List<String> account : accounts) {
			String firstEmail = account.get(1);
			for (int i = 2; i < account.size(); i++) {
				union(parent, firstEmail, account.get(i));
			}
		}
		Map<String, TreeSet<String>> unions = new HashMap<>();
		for (String email : parent.keySet()) {
			String root = find(parent, email);
			unions.computeIfAbsent(root, x -> new TreeSet<>()).add(email);
		}
		List<List<String>> result = new ArrayList<>();
		for (String root : unions.keySet()) {
			List<String> merged = new ArrayList<>();
			merged.add(emailToName.get(root));
			merged.addAll(unions.get(root));
			result.add(merged);
		}
		return result;
	}

	private String find(Map<String, String> parent, String email) {
		if (!parent.get(email).equals(email)) {
			parent.put(email, find(parent, parent.get(email)));
		}
		return parent.get(email);
	}

	private void union(Map<String, String> parent, String a, String b) {
		parent.put(find(parent, a), find(parent, b));
	}

	public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList<>();
		accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
		accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
		accounts.add(Arrays.asList("Mary","mary@mail.com"));
		accounts.add(Arrays.asList("John","johnnybravo@mail.com"));
		MergeAccounts ma = new MergeAccounts();
		List<List<String>> result = ma.accountsMerge(accounts);
		for (List<String> acc : result) {
			System.out.println(acc);
		}
	}
}
 
