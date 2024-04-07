import java.io.*;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();
	static int n, m;
	static char[][] arr;

	public static void main(String[] args) {
		init();

		char[][] ans = new char[n][m];

		for (int c = 0; c < m; c++) {
			int cursor = n - 1;

			for (int r = n - 1; r >= 0; r--) {
				if (arr[r][c] == '#') {
					ans[r][c] = '#';
					cursor = r - 1;
				}

				if (arr[r][c] == 'o') {
					ans[cursor--][c] = 'o';
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++){
			for (int j = 0; j < m; j++){
				sb.append((ans[i][j] != 'o' && ans[i][j] != '#') ? '.' : ans[i][j]);
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();

		arr = new char[n][m];

		for (int i = 0; i < n; i++) {
			arr[i] = scan.next().toCharArray();
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
