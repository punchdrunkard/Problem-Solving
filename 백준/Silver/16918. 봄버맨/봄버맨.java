import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();

	static final int[] DX = {1, -1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int r, c, n;
	static int[][] bomb;

	public static void main(String[] args) {
		input();
		solve();
	}

	static void solve() {
		int time = 2;

		while (time <= n) {
			if (time % 2 == 0) { // 짝수초 -> 폭탄을 심음
				install(time);
			} else { // 홀수 초 -> 폭탄이 터짐
				explode(time);
			}

			time++;
		}

		printBoard();
	}

	static void install(int time) { // time 초에 폭탄을 심음
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (bomb[i][j] != -1)
					continue;
				bomb[i][j] = time + 3;
			}
		}
	}

	static void explode(int time) {// time 초에 폭탄이 터짐
		int[][] temp = new int[r][c];
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				temp[i][j] = bomb[i][j];
			}
		}

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (bomb[i][j] == time) {
					temp[i][j] = -1;

					for (int dir = 0; dir < 4; dir++) {
						int nx = i + DX[dir];
						int ny = j + DY[dir];

						if (!isValidRange(nx, ny))
							continue;

						temp[nx][ny] = -1;
					}
				}
			}
		}

		bomb = temp;
	}

	static void printBoard() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				sb.append(bomb[i][j] == -1 ? '.' : 'O');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static boolean isValidRange(int x, int y) {
		return 0 <= x && x < r && 0 <= y && y < c;
	}

	static void input() {
		r = scan.nextInt();
		c = scan.nextInt();
		n = scan.nextInt();

		bomb = new int[r][c];

		for (int i = 0; i < r; i++) {
			char[] row = scan.nextLine().toCharArray();

			for (int j = 0; j < c; j++) {
				if (row[j] == 'O') {
					bomb[i][j] = 3; // 폭발까지 남은 시간
				} else if (row[j] == '.') {
					bomb[i][j] = -1;
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

