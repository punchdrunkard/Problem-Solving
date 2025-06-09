import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	// 오른쪽, 아래쪽, 왼쪽 대각선, 아래쪽 대각선
	static int[] DX = {0, 1, 1, 1};
	static int[] DY = {-1, 0, -1, 1};

	static int[][] board;

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		StringBuilder answerString = new StringBuilder();

		for (int y = 0; y < 19; y++) {
			for (int x = 0; x < 19; x++) {
				if (board[x][y] == 0) {
					continue;
				}

				if (isWin(x, y)) {
					answerString.append(board[x][y]).append('\n');
					answerString.append(x + 1).append(' ').append(y + 1);
					System.out.println(answerString);
					return;
				}
			}
		}

		answerString.append(0);
		System.out.println(answerString);
	}

	static boolean isWin(int x, int y) {
		int stone = board[x][y];

		for (int dir = 0; dir < 4; dir++) {
			if (countInDirection(dir, stone, x, y) == 5) {
				return true;
			}
		}

		return false;
	}

	// 주어진 방향으로 바둑알을 센다. (단 양방향으로 셈)
	static int countInDirection(int dir, int stone, int x, int y) {
		int count = 1;

		// 정방향 세기
		int nx = x + DX[dir];
		int ny = y + DY[dir];

		while (isValidRange(nx, ny)) {
			if (stone != board[nx][ny]) {
				break;
			}

			count++;
			nx += DX[dir];
			ny += DY[dir];
		}

		// 역방향 세기
		nx = x - DX[dir];
		ny = y - DY[dir];

		while (isValidRange(nx, ny)) {
			if (stone != board[nx][ny]) {
				break;
			}

			count++;
			nx -= DX[dir];
			ny -= DY[dir];
		}

		return count;
	}

	static boolean isValidRange(int x, int y) {
		return 0 <= x && x < 19 && 0 <= y && y < 19;
	}

	static void init() {
		board = new int[19][19];
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				board[i][j] = scan.nextInt();
			}
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
