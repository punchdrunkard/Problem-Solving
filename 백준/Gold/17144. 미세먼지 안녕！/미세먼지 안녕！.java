import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();
	static int r, c, t;

	static int[][] board;

	static int purifier;

	static int[] DX = {-1, 1, 0, 0};
	static int[] DY = {0, 0, -1, 1};

	public static void main(String[] args) {
		input();

		for (int i = 0; i < t; i++) {
			spread();
			purify();
		}

		System.out.println(getAnswer());
	}

	static int getAnswer() {
		int answer = 0;

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (isPurifier(i, j)) continue;
				answer += board[i][j];
			}
		}
		return answer;
	}


	static void purify() {
		int[][] temp = copyBoard();

		int top = purifier - 1;
		int bottom = purifier;

		// 위
		for (int i = 0; i <= c - 2; i++) {
			temp[top][i + 1] = board[top][i];
		}

		for (int x = top; x > 0; x--) {
			temp[x - 1][c - 1] = board[x][c - 1];
		}

		for (int y = c - 1; y > 0; y--) {
			temp[0][y - 1] = board[0][y];
		}

		for (int x = 0; x <= top; x++) {
			temp[x + 1][0] = board[x][0];
		}

		// 아래
		for (int y = 0; y <= c - 2; y++) {
			temp[bottom][y + 1] = board[bottom][y];
		}

		for (int x = bottom; x <= r - 2; x++) {
			temp[x + 1][c - 1] = board[x][c - 1];
		}

		for (int y = c - 1; y >= 1; y--) {
			temp[r - 1][y - 1] = board[r - 1][y];
		}

		for (int x = r - 1; x >= bottom + 1; x--) {
			temp[x - 1][0] = board[x][0];
		}

		temp[top][0] = 0;
		temp[bottom][0] = 0;

		board = temp;
	}

	static void spread() {
		int[][] temp = copyBoard();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] < 5) {
					continue;

				}

				int count = 0;

				for (int dir = 0; dir < 4; dir++) {
					int nx = i + DX[dir];
					int ny = j + DY[dir];

					if (!isValidRange(nx, ny) || isPurifier(nx, ny)) {
						continue;
					}


					temp[nx][ny] += (board[i][j] / 5);
					count++;
				}

				temp[i][j] -= ((board[i][j] / 5) * count);
			}
		}

		board = temp;
	}

	static boolean isPurifier(int x, int y) {
		return (y == 0) && (x == purifier || x == purifier - 1);
	}

	static int[][] copyBoard() {
		int temp[][] = new int[r][c];

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				temp[i][j] = board[i][j];
			}
		}

		return temp;
	}


	static boolean isValidRange(int x, int y) {
		return 0 <= x && x < r && 0 <= y && y < c;
	}

	static void input() {
		r = scan.nextInt();
		c = scan.nextInt();
		t = scan.nextInt();

		board = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				board[i][j] = scan.nextInt();
				if (board[i][j] == -1) {
					board[i][j] = 0;
					purifier = i;
				}
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

