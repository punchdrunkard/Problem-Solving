import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static int n, m;
	static List<Integer>[] adj;
	static boolean[] visited; // visited[i] := i번째 노드를 방문하였는가?

	static int maxLength = 0;

	public static void main(String[] args) {
		init();
		System.out.println(solve() ? 1 : 0);
	}

	static boolean solve() {
		for (int st = 0; st < n; st++) {
			// st 에서 출발해서 끝까지 갈 수 있는 최대 길이
			dfs(st, 1);

			if (maxLength >= 5) {
				return true;
			}
		}

		return false;
	}

	static void dfs(int current, int len) { // current 에서 최대로 탐색할 수 있는 길이
		if (len >= 5) {
			maxLength = 5;
			return;
		}

		visited[current] = true;
		maxLength = Math.max(maxLength, len);

		for (int node : adj[current]) {
			if (!visited[node]) {
				dfs(node, len + 1);
			}
		}

		visited[current] = false;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		visited = new boolean[n];

		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();

			adj[a].add(b);
			adj[b].add(a);
		}
	}

	static class FastReader {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			try {
				while (st == null || !st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return st.nextToken();
		}

	}
}
