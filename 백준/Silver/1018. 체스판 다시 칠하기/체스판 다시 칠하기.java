import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static int n, m;
	static String[] board;
	static String[][] CHESS_SHAPE = {
		{"BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB"},
		{"WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW"},
	};

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int answer = Integer.MAX_VALUE;

		for (int x1 = 0; x1 < n; x1++) {
			for (int y1 = 0; y1 < m; y1++) {
				int x2 = x1 + 7;
				int y2 = y1 + 7;

				if (isOutOfRange(x2, y2)) {
					continue;
				}

				// (x, y)로 부터 8 * 8을 골랐을 때, 두 가지 모양 중 하나가 되야 함
				// 1. B로 시작하는 경우
				// 2. W로 시작하는 경우
				char[][] newBoard = new char[8][8];

				for (int dx = 0; dx < 8; dx++) {
					for (int dy = 0; dy < 8; dy++) {
						newBoard[dx][dy] = board[x1 + dx].charAt(y1 + dy);
					}
				}

				for (String[] validChess : CHESS_SHAPE) {
					int count = 0;

					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							if (validChess[i].charAt(j) != newBoard[i][j]) {
								count++;
							}
						}
					}

					answer = Math.min(count, answer);
				}
			}
		}

		return answer;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || y < 0 || x >= n || y >= m;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		board = new String[n];

		for (int i = 0; i < n; i++) {
			board[i] = scan.next();
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
