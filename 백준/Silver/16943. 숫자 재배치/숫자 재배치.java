import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int a, b;
	static String aStr;

	static List<int[]> sequences = new ArrayList<>();

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		int answer = -1;
		aStr = String.valueOf(a);
		int n = aStr.length();
		int[] current = new int[n];
		boolean[] visited = new boolean[n];

		dfs(0, n, current, visited);

		for (int[] seq : sequences) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < seq.length; i++) {
				sb.append(aStr.charAt(seq[i]));
			}

			int c = Integer.parseInt(sb.toString());
			if (c < b) {
				answer = Math.max(answer, c);
			}
		}

		System.out.println(answer);
	}

	static void dfs(int depth, int n, int[] current, boolean[] visited) {
		if (depth == n) {
			if (aStr.charAt(current[0]) != '0') {
				sequences.add(Arrays.copyOf(current, current.length));
			}

			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				continue;
			}

			// pruning
			visited[i] = true;
			current[depth] = i;
			dfs(depth + 1, n, current, visited);
			visited[i] = false;
		}
	}

	static void init() {
		a = scan.nextInt();
		b = scan.nextInt();
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
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
	}
}
