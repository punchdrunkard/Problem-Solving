import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n, m;

	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) {
		init();
		dfs(1, 0, new int[m], new boolean[n + 1]);
		System.out.println(answer);
	}

	static void dfs(int i, int depth, int[] current, boolean[] visited) {
		// base case
		if (depth == m) {
			Arrays.stream(current).forEach(num -> answer.append(num).append(' '));
			answer.append('\n');
			return;
		}

		// recursive case
		for (int j = i; j <= n; j++) {
			if (visited[j]) {
				continue;
			}

			current[depth] = j;
			visited[j] = true;
			dfs(j + 1, depth + 1, current, visited);
			current[depth] = 0;
			visited[j] = false;
		}
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
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
