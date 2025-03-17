import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static StringBuilder sb = new StringBuilder();

	static final int INF = Integer.MAX_VALUE;

	static int n, m, a, b, c;
	static ArrayList<Edge>[] adj;
	static int[] dist;
	static int ansMin = INF;

	static class Edge {
		int v;
		int cost;

		Edge(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static class State implements Comparable<State> {
		int x, dist;

		State(int x, int dist) {
			this.x = x;
			this.dist = dist;
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(dist, o.dist);
		}
	}

	public static void main(String[] args) {
		init();
		solve();
		System.out.println(ansMin);
	}

	static void solve() {

		for (int threshold = 1; threshold <= 20; threshold++) {
			if (dijkstra(threshold)) {
				ansMin = Math.min(threshold, ansMin);
			}
		}
	}

	// 주어진 크기의 간선을 제외했을 때 a -> b로 갈 수 있는 최단경로
	static boolean dijkstra(int threshold) {
		for (int i = 1; i <= n; i++) {
			dist[i] = INF;
		}

		PriorityQueue<State> pq = new PriorityQueue<>();
		pq.offer(new State(a, 0));
		dist[a] = 0;

		while (!pq.isEmpty()) {
			State current = pq.poll();

			if (dist[current.x] != current.dist) {
				continue;
			}

			for (Edge e : adj[current.x]) {
				if (e.cost > threshold) {
					continue;
				}

				if (dist[e.v] > dist[current.x] + e.cost) {
					dist[e.v] = current.dist + e.cost;
					pq.offer(new State(e.v, dist[e.v]));
				}
			}
		}

		return dist[b] <= c;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		a = scan.nextInt();
		b = scan.nextInt();
		c = scan.nextInt();

		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int v1 = scan.nextInt();
			int v2 = scan.nextInt();
			int cost = scan.nextInt();

			// 양방향
			adj[v1].add(new Edge(v2, cost));
			adj[v2].add(new Edge(v1, cost));
		}

		dist = new int[n + 1];
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}
	}
}
