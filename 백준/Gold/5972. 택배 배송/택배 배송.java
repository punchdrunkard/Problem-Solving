import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static int n, m;
	static List<Node>[] adj;

	public static void main(String[] args) {
		init();
		System.out.println(dijkstra());
	}

	static int dijkstra() {
		// dist[i] := 0부터 i까지의 거리
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[0] = 0;

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0));

		while (!pq.isEmpty()) {
			Node current = pq.poll();

			int to = current.to;
			int cost = current.cost;

			if (cost != dist[to]) {
				continue;
			}

			if (to == n - 1) {
				return cost;
			}

			for (Node neighbor : adj[to]) {
				int next = neighbor.to;
				int nextCost = dist[to] + neighbor.cost;

				if (nextCost < dist[next]) {
					dist[next] = nextCost;
					pq.offer(new Node(next, nextCost));
				}
			}
		}

		return dist[n - 1];
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		adj = new List[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList();
		}

		for (int i = 0; i < m; i++) {
			int a = scan.nextInt() - 1;
			int b = scan.nextInt() - 1;
			int c = scan.nextInt();

			// 양방향
			adj[a].add(new Node(b, c));
			adj[b].add(new Node(a, c));
		}
	}

	static class Node implements Comparable<Node> {
		int to;
		int cost;

		Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(cost, o.cost);
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) {
			try {
				br = new BufferedReader(new FileReader(new File(s)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
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

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
