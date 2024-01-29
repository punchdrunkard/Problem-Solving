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
	static int n, m, a, b;
	static Long c;
	static final Long INF = 1_000_000_001L;
	static List<State>[] adj;

	static class State implements Comparable<State> {
		int dest;
		Long cost;

		State(int dest, Long cost) {
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "State{" +
				"dest=" + dest +
				", cost=" + cost +
				'}';
		}

		@Override
		public int compareTo(State o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	public static Long solve() {
		// check(x) := 지나는 골목의 요금의 최댓값의 최솟값이 x 일 때, c 원 이하로 지나갈 수 있는가?
		Long lo = 0L;
		Long hi = INF;

		while (lo + 1 < hi) {
			Long mid = lo + (hi - lo) / 2;

			if (check(mid)) {
				hi = mid;
			} else {
				lo = mid;
			}
		}

		return check(hi) ? hi : -1;
	}

	public static boolean check(Long x) {
		// dijkstra
		Long[] dist = new Long[n + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[a] = 0L; // 시작점
		Queue<State> pq = new PriorityQueue<>();
		pq.offer(new State(a, 0L));

		while (!pq.isEmpty()) {
			State current = pq.poll();

			if (current.cost > dist[current.dest]) {
				continue;
			}

			for (var edge : adj[current.dest]) {
				if (edge.cost > x) {
					continue;
				}

				Long nextCost = dist[current.dest] + edge.cost;

				if (nextCost >= dist[edge.dest]) {
					continue;
				}

				dist[edge.dest] = nextCost;
				pq.offer(new State(edge.dest, nextCost));
			}
		}

		return dist[b] <= c;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		n = line.nextInt();
		m = line.nextInt();
		a = line.nextInt();
		b = line.nextInt();
		c = line.nextLong();

		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			line.setNewLine(br.readLine());
			int st = line.nextInt();
			int en = line.nextInt();
			Long cost = line.nextLong();

			adj[st].add(new State(en, cost));
			adj[en].add(new State(st, cost));
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

		public Long nextLong() {
			return Long.parseLong(st.nextToken());
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
