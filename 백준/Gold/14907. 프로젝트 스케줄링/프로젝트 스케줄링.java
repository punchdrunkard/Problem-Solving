import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static int[] indegree = new int[26];
	public static Map<Integer, Integer> cost = new HashMap<>();
	public static List<Integer>[] adj = new ArrayList[26];

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	public static int solve(){
		int[] dp = new int[26];

		Deque<Integer> q = new LinkedList<>();
		for (int work: cost.keySet()) {
			if (indegree[work] == 0){
				q.offer(work);
				dp[work] += cost.get(work);
			}
		}

		while (!q.isEmpty()) {
			int current = q.poll();

			for (int work : adj[current]) {
				indegree[work]--;
				dp[work] = Math.max(dp[work], dp[current]);

				if (indegree[work] == 0){
					dp[work] += cost.get(work);
					q.offer(work);
				}
			}
		}

		return Arrays.stream(dp).max().getAsInt();
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputLine;

		for (int i = 0; i < 26; i++) {
			adj[i] = new ArrayList<>();
		}

		while ((inputLine = br.readLine()) != null) {
			OneLineParser line = new OneLineParser(inputLine);

			int w = line.nextToken().charAt(0) - 'A';
			int c = line.nextInt();
			cost.put(w, c);

			if (line.hasMoreTokens()) {
				char[] works = line.nextToken().toCharArray();

				for (int i = 0; i < works.length; i++) {
					adj[works[i] - 'A'].add(w);
					indegree[w]++;
				}
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

		public String nextToken() {
			return st.nextToken();
		}

		public boolean hasMoreTokens() {
			return st.hasMoreTokens();
		}
	}
}
