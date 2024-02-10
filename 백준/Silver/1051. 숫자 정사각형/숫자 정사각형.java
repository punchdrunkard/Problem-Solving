import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();
	static int n, m;
	static char[][] board;

	static int[] DX = {0, 1, 1};
	static int[] DY = {1, 0, 1};

	public static void main(String[] args) {
		input();
		System.out.println(solve());
	}

	public static int solve() {
		int maxLength = -1;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				// (i, j)를 가장 왼쪽 꼭짓점으로 하고 한 변의 길이가 len인 정사각형
				for (int len = 0; len <= n; len++) {
					boolean flag = true;

					for (int dir = 0; dir < 3; dir++) {
						int nx = i + (len - 1) * DX[dir];
						int ny = j + (len - 1) * DY[dir];

						if (!isValidRange(nx, ny)) {
							flag = false;
							break;
						}

						if (board[nx][ny] != board[i][j]) {
							flag = false;
							break;
						}
					}

					if (flag) {
						maxLength  = Math.max(maxLength, len);
					}
				}
			}
		}

		return maxLength * maxLength;
	}

	public static boolean isValidRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

	public static void input() {
		n = scan.nextInt();
		m = scan.nextInt();
		board = new char[n][m];

		for (int i = 0; i < n; i++) {
			board[i] = scan.nextLine().toCharArray();
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
