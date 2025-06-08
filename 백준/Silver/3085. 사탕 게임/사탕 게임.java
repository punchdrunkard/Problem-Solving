import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int n;
	static char[][] board;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int max = -1;

		for (int x = 0; x < n; x++) { // 가능한 모든 칸에 대하여
			for (int y = 0; y < n; y++) {
				char current = board[x][y]; // 현재 칸

				for (int d = 0; d < 4; d++) {
					int nx = x + DX[d];
					int ny = y + DY[d];

					if (isOutOfRange(nx, ny)) {
						continue;
					}
					char adj = board[nx][ny];

					// 교환
					board[x][y] = adj;
					board[nx][ny] = current;

					// 갯수 세기
					max = Math.max(max, countLongest());

					// 원상태 복구
					board[x][y] = current;
					board[nx][ny] = adj;
				}
			}
		}

		return max;
	}

	static int countLongest() {
		int max = -1;
		// 현재 행에서 가장 긴 연속 부분 찾기
		for (int row = 0; row < n; row++) {
			int seq = 1; // 현재 연속 갯수
			char curr = board[row][0];

			for (int col = 1; col < n; col++) {
				if (curr != board[row][col]) {
					max = Math.max(seq, max);
					seq = 1;
					curr = board[row][col];
				} else {
					seq++;
				}
			}

			max = Math.max(seq, max);
		}

		// 현재 열에서 가장 긴 연속 부분 찾기
		for (int col = 0; col < n; col++) {
			int seq = 1; // 현재 연속 갯수
			char curr = board[0][col];

			for (int row = 1; row < n; row++) {
				if (curr != board[row][col]) {
					max = Math.max(seq, max);
					seq = 1;
					curr = board[row][col];
				} else {
					seq++;
				}
			}

			max = Math.max(seq, max);
		}

		return max;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}

	static void init() {
		n = scan.nextInt();
		board = new char[n][n];
		for (int i = 0; i < n; i++) {
			board[i] = scan.next().toCharArray();
		}
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
