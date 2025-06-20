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
		dfs(0, new int[m]);
		System.out.println(answer);
	}

	static void dfs(int depth, int[] current) {
		if (depth == m) {
			appendAnswer(current);
			return;
		}

		for (int i = 1; i <= n; i++) {
			current[depth] = i;
			dfs(depth + 1, current);
			current[depth] = 0;
		}
	}

	private static void appendAnswer(int[] current) {
		Arrays.stream(current).forEach(num -> answer.append(num).append(' '));
		answer.append('\n');
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
