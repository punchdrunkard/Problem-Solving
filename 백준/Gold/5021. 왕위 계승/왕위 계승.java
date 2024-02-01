import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static String king;
	static Map<String, List<String>> adj = new HashMap<>();
	static Map<String, Integer> indegree = new HashMap<>();
	static String[] candidates;

	static class State {
		String name;
		double level;

		State(String name, double level) {
			this.name = name;
			this.level = level;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve().get());
	}

	public static Optional<String> solve() {
		Map<String, Double> bloodLevel = new HashMap<>();
		Deque<State> q = new LinkedList<>();
		for (Map.Entry<String, Integer> entry : indegree.entrySet()) {
			String name = entry.getKey();

			if (entry.getValue() == 0) {
				if (name.equals(king)) {
					q.offer(new State(name, 1));
					bloodLevel.put(name, 1d);
				} else {
					q.offer(new State(name, 0));
					bloodLevel.put(name, 0d);
				}
			}
		}

		while (!q.isEmpty()) {
			State current = q.poll();

			for (String child : adj.get(current.name)) {
				indegree.put(child, indegree.get(child) - 1);
				bloodLevel.put(child, bloodLevel.getOrDefault(child, 0d) + current.level);

				if (indegree.get(child) == 0) {
					bloodLevel.put(child, bloodLevel.get(child) / 2);
					q.offer(new State(child, bloodLevel.get(child)));
				}
			}
		}

		return Arrays.stream(candidates)
			.max(Comparator.comparingDouble(candidate -> bloodLevel.getOrDefault(candidate, 0d)));
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());
		n = line.nextInt();
		m = line.nextInt();
		king = br.readLine();
		indegree.put(king, 0);

		candidates = new String[m];

		for (int i = 0; i < n; i++) {
			line.setNewLine(br.readLine());
			String child = line.nextToken();
			String parent1 = line.nextToken();
			String parent2 = line.nextToken();

			adj.computeIfAbsent(parent1, (k) -> new ArrayList<>()).add(child);
			adj.computeIfAbsent(parent2, (k) -> new ArrayList<>()).add(child);
			adj.computeIfAbsent(child, (k) -> new ArrayList<>());
			indegree.put(child, indegree.getOrDefault(child, 0) + 2);

			indegree.putIfAbsent(parent1, 0);
			indegree.putIfAbsent(parent2, 0);
		}

		for (int i = 0; i < m; i++) {
			candidates[i] = br.readLine();
		}
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}

		public String nextToken() {
			return st.nextToken();
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
