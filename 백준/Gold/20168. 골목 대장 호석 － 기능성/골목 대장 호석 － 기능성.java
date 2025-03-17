import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static StringBuilder sb = new StringBuilder();

	static int n, m, a, b, c;
	static ArrayList<Edge>[] adj;
	static boolean[] visited;

	static int ansMin = Integer.MAX_VALUE;

	static class Edge {
		int v;
		int cost;

		Edge(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		init();
		dfs(a, 0, 0);
		System.out.println(ansMin == Integer.MAX_VALUE ? -1 : ansMin);
	}

	// st에서 갈 수 있는 모든 경우를 탐색
	static void dfs(int st, int totalCost, int maxCost) {
		// 도착한 경우 return
		if (st == b) {
			if (totalCost <= c) {
				ansMin = Math.min(ansMin, maxCost);
			}

			return;
		}

		// cost가 넘어가버린경우 return
		if (totalCost >= c) {
			return;
		}

		visited[st] = true;

		for (Edge e : adj[st]) {
			if (!visited[e.v]) {
				int newMaxCost = Math.max(maxCost, e.cost);
				dfs(e.v, totalCost + e.cost, newMaxCost);
			}
		}

		visited[st] = false; // Backtrack
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

		visited = new boolean[n + 1];
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
