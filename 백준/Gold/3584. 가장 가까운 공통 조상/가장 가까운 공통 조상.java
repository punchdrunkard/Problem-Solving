import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n;
	static List<Integer>[] adj;
	static int[] parent, depth;
	static int node1, node2;
	static int root; // 현재 트리의 루트

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			init();
			dfs(root, parent[root], 0);
			sb.append(solve()).append("\n");
		}

		System.out.print(sb);
	}

	public static int solve() {
		int current1 = node1;
		int current2 = node2;

		while (current1 != current2) { // 조상이 같아질 때 까지
			if (depth[current1] > depth[current2]) {
				current1 = parent[current1];
			} else if (depth[current1] < depth[current2]) {
				current2 = parent[current2];
			} else {
				if (current1 == current2) {
					break;
				} else {
					current1 = parent[current1];
					current2 = parent[current2];
				}
			}
		}

		return current1;
	}

	// 트리의 depth 구하기
	public static void dfs(int x, int par, int currentDepth) {
		depth[x] = currentDepth;

		for (int child : adj[x]) {
			if (child == par) {
				continue;
			}

			dfs(child, x, currentDepth + 1);
		}
	}

	public static void init() throws IOException {
		n = Integer.parseInt(br.readLine());
		adj = new ArrayList[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		parent = new int[n + 1];
		depth = new int[n + 1];

		boolean[] isChild = new boolean[n + 1];

		for (int i = 0; i < n - 1; i++) {
			OneLineParser line = new OneLineParser(br.readLine());

			int a = line.nextInt();
			int b = line.nextInt();

			adj[a].add(b);
			adj[b].add(a);

			parent[b] = a;
			isChild[b] = true;
		}

		for (int node = 1; node <= n; node++) {
			if (!isChild[node]) {
				root = node;
				break;
			}
		}

		parent[root] = -1;

		OneLineParser line = new OneLineParser(br.readLine());
		node1 = line.nextInt();
		node2 = line.nextInt();
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}
	}
}
