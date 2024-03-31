import java.io.*;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();

	// 돌아가는 방향: -> <- 위 아래

	static final int RIGHT = 1;
	static final int LEFT = 2;
	static final int UP = 3;
	static final int DOWN = 4;

	static final int[] DX = {0, 0, 0, -1, 1};
	static final int[] DY = {0, 1, -1, 0, 0};

	static int r, c, k, w;
	static List<Pair> heaters, targets;
	static int[][] board, wall, temperature;

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int chocoCount = 0;
		int[][] windAmount = wind();

		while (!isEnd() && chocoCount <= 100) { // 모든 칸의 온도가 1 이상이거나, 초콜릿 갯수가 101개 이상일 때
			for (int i = 1; i <= r; i++) {
				for (int j = 1; j <= c; j++) {
					temperature[i][j] += windAmount[i][j];
				}
			}

			control();
			decreaseOutside();
			chocoCount++;
			// printTemp(temperature);
		}

		return chocoCount;
	}

	static void decreaseOutside() {
		for (int i = 1; i <= c; i++) {
			temperature[1][i] = Math.max(temperature[1][i] - 1, 0);
			temperature[r][i] = Math.max(temperature[r][i] - 1, 0);
		}

		for (int i = 2; i < r; i++) {
			temperature[i][1] = Math.max(temperature[i][1] - 1, 0);
			temperature[i][c] = Math.max(temperature[i][c] - 1, 0);
		}
	}

	static void control() {
		int[][] delta = new int[r + 1][c + 1]; // 온도 증감 기록

		// 모든 인접한 칸에 대해서
		for (int x = 1; x <= r; x++) {
			for (int y = 1; y <= c; y++) {
				for (int dir = 1; dir <= 4; dir++) {
					int nx = x + DX[dir];
					int ny = y + DY[dir];

					if (!isValidRange(nx, ny) || (wall[x][y] & (1 << dir)) != 0) {
						continue;
					}

					// 온도가 높은 칸에서 낮은 칸으로 조절됨
					if (temperature[x][y] <= temperature[nx][ny]) {
						continue;
					}

					int diff = Math.abs(temperature[x][y] - temperature[nx][ny]) / 4;
					delta[x][y] -= diff;
					delta[nx][ny] += diff;
				}
			}
		}

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				temperature[i][j] += delta[i][j];
			}
		}
	}

	static boolean isValidRange(int x, int y) {
		return 1 <= x && x <= r && 1 <= y && y <= c;
	}

	static int[][] wind() {
		int[][] delta = new int[r + 1][c + 1];

		for (Pair heater : heaters) {
			int hx = heater.x;
			int hy = heater.y;
			int dir = board[hx][hy];

			int sx = heater.x + DX[dir];
			int sy = heater.y + DY[dir];

			Queue<Pair> q = new LinkedList<>();
			boolean[][] visited = new boolean[r + 1][c + 1];
			int[][] amount = new int[r + 1][c + 1];

			q.offer(new Pair(sx, sy));
			visited[sx][sy] = true;
			amount[sx][sy] = 5;

			while (!q.isEmpty()) {
				Pair current = q.poll();
				int cx = current.x;
				int cy = current.y;

				if (amount[cx][cy] == 1) {
					continue;
				}

				int nx = cx + DX[dir];
				int ny = cy + DY[dir];

				if (isValidRange(nx, ny) && !visited[nx][ny] && (wall[cx][cy] & (1 << dir)) == 0) {
					amount[nx][ny] = amount[cx][cy] - 1;
					q.offer(new Pair(nx, ny));
					visited[nx][ny] = true;
				}

				if (dir == RIGHT || dir == LEFT) {
					// (cx - 1, ny) 로 이동할 수 있는가?
					if (isValidRange(cx - 1, ny) && !visited[cx - 1][ny]) {
						if ((wall[cx][cy] & (1 << UP)) == 0 &&
							(wall[cx - 1][cy] & (1 << dir)) == 0) {
							amount[cx - 1][ny] = amount[cx][cy] - 1;
							visited[cx - 1][ny] = true;
							q.offer(new Pair(cx - 1, ny));
						}
					}

					// (cx + 1, ny) 로 이동할 수 있는가?
					if (isValidRange(cx + 1, ny) && !visited[cx + 1][ny]) {
						if ((wall[cx][cy] & (1 << DOWN)) == 0 &&
							(wall[cx + 1][cy] & (1 << dir)) == 0) {
							amount[cx + 1][ny] = amount[cx][cy] - 1;
							visited[cx + 1][ny] = true;
							q.offer(new Pair(cx + 1, ny));
						}
					}

				} else {
					// (cx + DX[dir], cy - 1) 로 이동할 수 있는가?
					if (isValidRange(nx, cy - 1) && !visited[nx][cy - 1]) {
						if ((wall[cx][cy] & (1 << LEFT)) == 0 &&
							(wall[cx][cy - 1] & (1 << dir)) == 0) {
							amount[nx][cy - 1] = amount[cx][cy] - 1;
							visited[nx][cy - 1] = true;
							q.offer(new Pair(nx, cy - 1));
						}
					}

					// (nx, cy + 1) 로 이동할 수 있는가?
					if (isValidRange(nx, cy + 1) && !visited[nx][cy + 1]) {
						if ((wall[cx][cy] & (1 << RIGHT)) == 0 &&
							(wall[cx][cy + 1] & (1 << dir)) == 0) {
							amount[nx][cy + 1] = amount[cx][cy] - 1;
							visited[nx][cy + 1] = true;
							q.offer(new Pair(nx, cy + 1));
						}
					}
				}
			}

			for (int i = 1; i <= r; i++) {
				for (int j = 1; j <= c; j++) {
					delta[i][j] += amount[i][j];
				}
			}
		}

		return delta;
	}

	static boolean isEnd() {
		for (Pair t : targets) {
			if (temperature[t.x][t.y] < k)
				return false;
		}

		return true;
	}

	static void printTemp(int[][] arr) {
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= arr.length - 1; i++) {
			for (int j = 1; j <= arr[i].length - 1; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void init() {
		r = scan.nextInt();
		c = scan.nextInt();
		k = scan.nextInt();

		board = new int[r + 1][c + 1];
		wall = new int[r + 1][c + 1];
		temperature = new int[r + 1][c + 1];
		heaters = new ArrayList<>();
		targets = new ArrayList<>();

		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				board[i][j] = scan.nextInt();

				if (1 <= board[i][j] && board[i][j] <= 4) {
					heaters.add(new Pair(i, j));
				} else if (board[i][j] == 5) {
					targets.add(new Pair(i, j));
				}
			}
		}

		w = scan.nextInt();

		for (int i = 0; i < w; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			int t = scan.nextInt();

			if (t == 0) { // (x, y)와 (x - 1, y) 사이에 벽이 있다.
				wall[x][y] |= (1 << UP);
				wall[x - 1][y] |= (1 << DOWN);
			} else { // (x, y)와 (x, y + 1) 사이에 벽이 있다.
				wall[x][y] |= (1 << RIGHT);
				wall[x][y + 1] |= (1 << LEFT);
			}
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
