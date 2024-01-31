import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static Map<String, List<String>> adj = new HashMap<>();
	public static Map<String, Integer> indegree = new HashMap<>();

	static class Item implements Comparable<Item> {
		String name;
		int level;

		Item(String name, int level) {
			this.name = name;
			this.level = level;
		}

		@Override
		public int compareTo(Item o) {
			if (this.level == o.level) {
				return this.name.compareTo(o.name);
			}
			return Integer.compare(this.level, o.level);

		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

	public static void solve() {
		List<Item> result = new ArrayList<>();
		Queue<Item> q = new PriorityQueue<>();

		for (Map.Entry<String, Integer> entry : indegree.entrySet()) {
			if (entry.getValue() == 0) {
				q.offer(new Item(entry.getKey(), 0));
			}
		}

		while (!q.isEmpty()) {
			Item item = q.poll();
			result.add(item);

			for (String next : adj.get(item.name)) {
				indegree.put(next, indegree.get(next) - 1);

				if (indegree.get(next) == 0) {
					q.offer(new Item(next, item.level + 1));
				}
			}
		}

		if (result.size() != indegree.size()) {
			System.out.println(-1);
		} else {
			StringBuilder sb = new StringBuilder();

			for (Item item: result){
				sb.append(item.name).append('\n');
			}

			System.out.print(sb);
		}
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();

			if (!adj.containsKey(a)) {
				adj.put(a, new ArrayList<>());
			}

			if (!adj.containsKey(b)) {
				adj.put(b, new ArrayList<>());
			}

			if (!indegree.containsKey(a)) {
				indegree.put(a, 0);
			}

			if (!indegree.containsKey(b)) {
				indegree.put(b, 0);
			}

			adj.get(a).add(b);
			indegree.put(b, indegree.get(b) + 1);
		}
	}
}
