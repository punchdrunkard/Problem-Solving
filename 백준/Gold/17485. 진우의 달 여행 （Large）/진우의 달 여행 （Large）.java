import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static int[] DY = {-1, 0, 1};
	static int n, m;
	static int[][] board;
	static PriorityQueue<State> pq;

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		// dist[i][j][d] := (i, j) 좌표로 d 방향으로 갔을 때 최단 거리를 기록한다.
		int[][][] dist = initDist();

		// 시작점을 pq 에 넣는다.
		for (int y = 0; y < m; y++) { // x 좌표 -1? y좌표 y 에서 출발
			for (int dir = 0; dir < 3; dir++) {
				int nx = 0;
				int ny = y + DY[dir];

				if (isOutOfRange(nx, ny)) {
					continue;
				}

				pq.offer(new State(nx, ny, dir, board[nx][ny]));
				dist[nx][ny][dir] = board[nx][ny];
			}
		}

		while (!pq.isEmpty()) {
			State current = pq.poll();

			if (current.cost > dist[current.x][current.y][current.dir]) {
				continue;
			}

			for (int dir = 0; dir < 3; dir++) {
				int nx = current.x + 1;
				int ny = current.y + DY[dir];

				if (isOutOfRange(nx, ny) || current.dir == dir) {
					continue;
				}

				int nextDist = current.cost + board[nx][ny];

				if (dist[nx][ny][dir] > nextDist) {
					dist[nx][ny][dir] = nextDist;
					pq.offer(new State(nx, ny, dir, nextDist));
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int y = 0; y < m; y++) {
			for (int d = 0; d < 3; d++) {
				answer = Math.min(dist[n - 1][y][d], answer);
			}
		}

		System.out.println(answer);
	}

	private static int[][][] initDist() {
		int[][][] dist = new int[n][m][3];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < 3; k++) {
					dist[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		return dist;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		board = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] = scan.nextInt();
			}
		}

		pq = new PriorityQueue<>();
	}

	static class State implements Comparable<State> {
		int x, y, dir, cost;

		State(int x, int y, int dir, int cost) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cost = cost;
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(this.cost, o.cost);
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
