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
	static int n, m, s, t;
	static List<Edge>[] adj;

	static class Edge implements Comparable<Edge> {
		int en;
		int cost;

		public Edge(int en, int cost) {
			this.en = en;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) { // cost 기준 오름차순
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(dijkstra());
	}

	public static int dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[n + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);

		pq.offer(new Edge(s, 0));
		dist[s] = 0;

		while (!pq.isEmpty()) {
			Edge current = pq.poll();

			if (dist[current.en] < current.cost) {
				continue;
			}

			for (Edge edge : adj[current.en]) {
				int nextCost = current.cost + edge.cost;

				if (dist[edge.en] > nextCost) {
					dist[edge.en] = nextCost;
					pq.offer(new Edge(edge.en, nextCost));
				}
			}
		}

		return dist[t];
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		n = line.nextInt();
		m = line.nextInt();

		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		// undirected!
		for (int i = 0; i < m; i++) {
			line.setNewLine(br.readLine());
			int a = line.nextInt();
			int b = line.nextInt();
			int c = line.nextInt();
			adj[a].add(new Edge(b, c));
			adj[b].add(new Edge(a, c));
		}

		line.setNewLine(br.readLine());
		s = line.nextInt();
		t = line.nextInt();
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
