import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int src, dest;
	static List<State>[] adj;

	static class State implements Comparable<State> {
		int prev; // 이전 점
		int dest;
		Long cost;

		State(int prev, int dest, Long cost) {
			this.prev = prev;
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public int compareTo(State o) {
			return Long.compare(this.cost, o.cost);
		}

		@Override
		public String toString() {
			return "State{" +
				"prev=" + prev +
				", dest=" + dest +
				", cost=" + cost +
				'}';
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

	public static void solve() {
		StringBuilder sb = new StringBuilder();

		State[] dist = dijkstra();
		List<Integer> path = new ArrayList<>();
		Deque<Integer> stk = new ArrayDeque<>();
		stk.offer(dest);

		while (!stk.isEmpty()) {
			int next = stk.poll();
			if (next == -1)
				break;

			path.add(next);
			stk.offer(dist[next].prev);
		}

		// 비용, 도시 몇 개?, 경로
		sb.append(dist[dest].cost).append('\n');
		sb.append(path.size()).append('\n');

		for (int i = path.size() - 1; i >= 0; i--) {
			sb.append(path.get(i)).append(' ');
		}

		System.out.println(sb);

	}

	public static State[] dijkstra() {
		State[] dist = new State[n + 1];
		for (int i = 1; i <= n; i++) {
			dist[i] = new State(i, i, Long.MAX_VALUE);
		}

		dist[src] = new State(-1, src, 0L);

		Queue<State> pq = new PriorityQueue<>();
		pq.offer(dist[src]);

		while (!pq.isEmpty()) {
			State current = pq.poll();

			if (current.cost != dist[current.dest].cost) {
				continue;
			}

			for (var edge : adj[current.dest]) {
				Long nextCost = edge.cost + current.cost;

				if (nextCost >= dist[edge.dest].cost) {
					continue;
				}

				State next = new State(current.dest, edge.dest, nextCost);
				dist[edge.dest] = next;
				pq.offer(next);
			}
		}

		return dist;
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
			Long cost = line.nextLong();

			adj[st].add(new State(st, en, cost));
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
