import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static int r, c, t, cx, cy;
	static char[][] board;

	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) {
		init();
		solve(cx, cy, 0, 0);
		System.out.println(answer);
	}
	
	static void solve(int x, int y, int time, int count) {
		if (time >= t) {
			answer = Math.max(answer, count);
			return;
		}

		// - 이동하는 경우
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + DX[dir];
			int ny = y + DY[dir];

			if (isOutOfRange(nx, ny) || board[nx][ny] == '#') {
				continue;
			}

			if (board[nx][ny] == 'S') {
				board[nx][ny] = '.';
				solve(nx, ny, time + 1, count + 1);
				board[nx][ny] = 'S';
			} else {
				solve(nx, ny, time + 1, count);
			}
		}

		// - 이동하지 않고 그 자리에 머무는 경우
		solve(x, y, time + 1, count);
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= r || y < 0 || y >= c;
	}

	static void init() {
		r = scan.nextInt();
		c = scan.nextInt();
		t = scan.nextInt();
		board = new char[r][c];
		for (int i = 0; i < r; i++) {
			board[i] = scan.nextLine().toCharArray();
			for (int j = 0; j < c; j++) {
				if (board[i][j] == 'G') {
					cx = i;
					cy = j;
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

		public FastReader(String s) {
			try {
				br = new BufferedReader(new FileReader(new File(s)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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
