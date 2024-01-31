import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[] cost, indegree;
	static List<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}
	public static void solve(){
		int[] dp = new int[n + 1];

		Deque<Integer> q = new LinkedList<>();

		for (int i = 1; i <= n; i++){
			if (indegree[i] == 0){
				q.offer(i);
				dp[i] += cost[i];
			}
		}

		while (!q.isEmpty()) {
			int current = q.poll();

			for (int next : adj[current]) {
				indegree[next]--;
				dp[next] = Math.max(dp[next], dp[current]);

				if (indegree[next] == 0) {
					dp[next] += cost[next];
					q.offer(next);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++){
			sb.append(dp[i]).append('\n');
		}
		System.out.println(sb);
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cost = new  int[n + 1];
		indegree = new int[n + 1];
		adj = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++){
			adj[i] = new ArrayList<>();
		}

		for (int i = 1; i <= n; i++){
			OneLineParser line = new OneLineParser(br.readLine());
			int c = line.nextInt();
			cost[i] = c;

			int prev;

			while ((prev = line.nextInt()) != -1){
				adj[prev].add(i);
				indegree[i]++;
			}
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
	}
}
