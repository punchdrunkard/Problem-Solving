import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m, src, dest;
	static List<State>[] adj;
	static int[] dist, prev;

	static class State implements Comparable<State> {
		int to;
		int cost;

		State(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(this.cost, o.cost);
		}

	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

	public static void solve() {
		dijkstra();
		List<Integer> path = getMinimumPath();

		StringBuilder sb = new StringBuilder();
		sb.append(dist[dest]).append('\n');
		sb.append(path.size()).append('\n');

		for (int i = path.size() - 1; i >= 0; i--) {
			sb.append(path.get(i)).append(' ');
		}

		System.out.println(sb);
	}

	public static List<Integer> getMinimumPath() {
		List<Integer> path = new ArrayList<>();

		int current = dest;

		while (current != src) {
			path.add(current);
			current = prev[current];
		}

		path.add(current);

		return path;
	}

	public static void dijkstra() {
		dist = new int[n + 1];
		prev = new int[n + 1];

		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[src] = 0;
		prev[src] = src;

		Queue<State> pq = new PriorityQueue<>();
		pq.offer(new State(src, 0));

		while (!pq.isEmpty()) {
			State current = pq.poll();

			if (current.cost != dist[current.to]) {
				continue;
			}

			for (var edge : adj[current.to]) {
				int nextCost = edge.cost + current.cost;

				if (nextCost >= dist[edge.to]) {
					continue;
				}

				dist[edge.to] = nextCost;
				prev[edge.to] = current.to;
				pq.offer(new State(edge.to, nextCost));
			}
		}
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());
		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			OneLineParser line = new OneLineParser(br.readLine());

			int st = line.nextInt();
			int en = line.nextInt();
			int cost = line.nextInt();

			adj[st].add(new State(en, cost));
		}

		OneLineParser line = new OneLineParser(br.readLine());
		src = line.nextInt();
		dest = line.nextInt();
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}

		public Long nextLong() {
			return Long.parseLong(st.nextToken());
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
