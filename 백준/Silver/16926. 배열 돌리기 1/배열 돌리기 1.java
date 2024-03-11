import java.io.*;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();
	static int n, m, r;
	static int[][] v;

	public static void main(String[] args) {
		input();
		solve();
		printAnswer();
	}

	static void printAnswer() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(v[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void solve() {
		int rotateCount = Math.min(n, m) / 2;

		int nextN = n;
		int nextM = m;

		int x1 = 0;
		int y1 = 0;

		for (int cnt = 0; cnt < rotateCount; cnt++) {
			int x2 = x1 + nextN - 1;
			int y2 = y1 + nextM - 1;

			for (int i = 0; i < r; i++) {
				rotate(x1, x2, y1, y2);
			}

			nextN -= 2;
			nextM -= 2;
			x1 += 1;
			y1 += 1;
		}
	}

	static void rotate(int x1, int x2, int y1, int y2) {
		int temp = v[x1][y1];

		for (int j = y1; j < y2; j++) {
			v[x1][j] = v[x1][j + 1];
		}

		for (int i = x1; i < x2; i++) {
			v[i][y2] = v[i + 1][y2];
		}

		for (int j = y2; j > y1; j--) {
			v[x2][j] = v[x2][j - 1];
		}

		for (int i = x2; i > x1; i--) {
			v[i][y1] = v[i - 1][y1];
		}

		v[x1 + 1][y1] = temp;
	}

	static void input() {
		n = scan.nextInt();
		m = scan.nextInt();
		r = scan.nextInt();

		v = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				v[i][j] = scan.nextInt();
			}
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
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

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
