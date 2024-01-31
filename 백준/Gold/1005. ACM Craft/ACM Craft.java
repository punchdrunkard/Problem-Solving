import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, k, w;
	static int[] cost, indegree;
	static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			init();
			sb.append(solve()).append('\n');
		}

		System.out.print(sb);
	}

	public static long solve() {
		long[] dp = new long[n + 1]; // dp[i] := i 건물을 짓기 위해드는 최소 시간
		Queue<Integer> q = new LinkedList<>();

		// 1. indegree가 0인 점을 큐에 넣는다.
		for (int i = 1; i <= n; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
				dp[i] += cost[i];
			}
		}

		while (!q.isEmpty()) {
			// 큐에서 꺼내고
			int current = q.poll();

			// 해당 정점과 연결된 indegree를 감소시키고, dp 갱신 후, 0인걸 큐에 넣기
			for (int next : adj[current]) {
				indegree[next]--;
				dp[next] = Math.max(dp[next], dp[current]);

				if (indegree[next] == 0) {
					dp[next] += cost[next];

					if (next == w)
						break;

					q.offer(next);
				}
			}
		}

		return dp[w];
	}

	public static void init() throws IOException {
		OneLineParser line = new OneLineParser(br.readLine());
		n = line.nextInt(); // 건물의 갯수
		k = line.nextInt(); // 건물간의 건설순서 규칙의 갯수

		cost = new int[n + 1];
		line.setNewLine(br.readLine());

		for (int i = 1; i <= n; i++) {
			cost[i] = line.nextInt();
		}

		adj = new ArrayList[n + 1];
		indegree = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < k; i++) {
			line.setNewLine(br.readLine());
			int x = line.nextInt();
			int y = line.nextInt();

			adj[x].add(y);
			indegree[y]++;
		}

		w = Integer.parseInt(br.readLine());
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
