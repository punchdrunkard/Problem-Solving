import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n, m;
	static int[] arr;

	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) {
		init();
		dfs(0, new int[m], 0);
		System.out.println(answer);

	}

	static void dfs(int depth, int[] current, int start) {
		if (depth == m) {
			appendAnswer(current);
			return;
		}

		int prev = 0;

		for (int i = start; i < n; i++) { // 비내림차순
			if (prev == arr[i]) {
				continue;
			}

			prev = arr[i];
			current[depth] = arr[i];
			dfs(depth + 1, current, i + 1);
		}
	}

	static void appendAnswer(int[] current) {
		Arrays.stream(current).forEach(num -> answer.append(num).append(' '));
		answer.append('\n');
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scan.nextInt();
		}
		Arrays.sort(arr);
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
