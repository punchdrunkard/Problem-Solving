import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static final int WALL = 1;

	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int n, m, k;
	static int[][] board;

	static boolean[][][] visited;
	static Queue<State> q = new LinkedList<>();

	static class State {
		int x, y, count;

		State(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}

	public static void main(String[] args) {
		init();
		System.out.println(bfs());
	}

	static int bfs() {
		State start = new State(0, 0, 0);
		q.offer(start);
		visited[0][0][0] = true;
		int dist = 1;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				State current = q.poll();
				int cx = current.x;
				int cy = current.y;
				int currentCount = current.count;

				if (arrived(cx, cy)) {
					return dist;
				}

				for (int dir = 0; dir < 4; dir++) {
					int nx = cx + DX[dir];
					int ny = cy + DY[dir];

					if (isOutOfRange(nx, ny)) {
						continue;
					}

					if (board[nx][ny] == WALL) {
						int nextCount = currentCount + 1;

						State next = new State(nx, ny, nextCount);

						if (nextCount <= k && !visited[nextCount][nx][ny]) {
							q.offer(next);
							visited[nextCount][nx][ny] = true;
						}
					} else { // 벽이 아니라면
						State next = new State(nx, ny, currentCount);

						if (!visited[currentCount][nx][ny]) {
							q.offer(next);
							visited[currentCount][nx][ny] = true;
						}

					}

				}
			}

			dist++;
		}

		return -1;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}

	private static boolean arrived(int cx, int cy) {
		return cx == n - 1 && cy == m - 1;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		k = scan.nextInt();

		board = new int[n][m];
		visited = new boolean[k + 1][n][m];

		for (int x = 0; x < n; x++) {
			board[x] = scan.next().chars()
				.map(c -> c - '0')
				.toArray();
		}

	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
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
