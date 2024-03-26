import java.util.*;
import java.io.*;

public class Main {
	static FastReader scan = new FastReader();
	static final int[] DX = {0, 0, -1, 1};
	static final int[] DY = {1, -1, 0, 0};

	static final int WHITE = 0;
	static final int RED = 1;
	static final int BLUE = 2;

	static int n, k;
	static List<Integer>[][] board;
	static int[][] color;
	static int[] kx, ky, dirs;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		for (int turn = 1; turn <= 1000; turn++) {
			for (int i = 1; i <= k; i++) {
				// 해당 말이 이동하려는 칸
				int nx = kx[i] + DX[dirs[i]];
				int ny = ky[i] + DY[dirs[i]];

				// 이동하려는 칸의 색 마다 이동이 달라짐
				if (!isValidRange(nx, ny) || color[nx][ny] == BLUE) {
					// 이동 방향을 반대로 하고 한 칸 이동
					dirs[i] = changeDirection(dirs[i]);
					nx = kx[i] + DX[dirs[i]];
					ny = ky[i] + DY[dirs[i]];

					if (!isValidRange(nx, ny) || color[nx][ny] == BLUE) { // 움직이지 않는다.
						continue;
					} else {
						move(kx[i], ky[i], nx, ny, i);
					}
				} else {
					move(kx[i], ky[i], nx, ny, i);
				}

				// 이동한 좌표에 말이 4개 이상이면 return
				if (board[nx][ny].size() >= 4) {
					return turn;
				}
			}
		}

		return -1;
	}

	// board[x][y]에 있던 i 번 말이 board[nx][ny]로 이동
	static void move(int x, int y, int nx, int ny, int idx) {
		int base = -1; // 이 인덱스 다음에 있는 말들이 이동하게 될 것이다.

		List<Integer> remain = new ArrayList<>(); // 원래 칸에 남아있을 말들
		List<Integer> moved = new ArrayList<>(); // 다음 칸으로 이동할 말들

		for (int i = 0; i < board[x][y].size(); i++) {
			if (board[x][y].get(i) == idx) {
				base = i;
				break;
			}

			remain.add(board[x][y].get(i));
		}

		for (int i = base; i < board[x][y].size(); i++) {
			moved.add(board[x][y].get(i));
		}

		if (color[nx][ny] == RED) {
			Collections.reverse(moved);
		}

		for (int i : moved) {
			board[nx][ny].add(i);
			kx[i] = nx;
			ky[i] = ny;
		}

		board[x][y] = remain;
	}

	static int changeDirection(int d) {
		if (d == 0)
			return 1;
		if (d == 1)
			return 0;
		if (d == 2)
			return 3;
		if (d == 3)
			return 2;
		return -1;
	}

	static boolean isValidRange(int x, int y) {
		return 1 <= x && x <= n && 1 <= y && y <= n;
	}

	static void init() {
		n = scan.nextInt();
		k = scan.nextInt();

		color = new int[n + 1][n + 1];
		board = new ArrayList[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				board[i][j] = new ArrayList<>();
				color[i][j] = scan.nextInt();
			}
		}

		kx = new int[k + 1];
		ky = new int[k + 1];
		dirs = new int[k + 1];

		for (int i = 1; i <= k; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			int dir = scan.nextInt();

			kx[i] = x;
			ky[i] = y;
			dirs[i] = dir - 1;
			board[x][y].add(i);
		}

	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public String next() {
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
