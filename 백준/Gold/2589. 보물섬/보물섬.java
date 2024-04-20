import java.util.*;
import java.io.*;

public class Main {
	static FastReader scan = new FastReader();
	static final char W = 'W';
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int r, c;
	static char[][] map;
	static int[][] dist;

	static Queue<Pair> q = new LinkedList<>();

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		int result = -1;

		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				if (isOutOfRange(x, y) || map[x][y] == W) {
					continue;
				}

				result = Math.max(result, bfs(x, y));
				initDist();

			}
		}

		System.out.println(result);
	}

	static void initDist() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				dist[i][j] = -1;
			}
		}
	}

	// 가장 먼 거리의 좌표를 반환
	static int bfs(int x, int y) {
		// 현재 연결되어있는 좌표들 모음
		List<Pair> points = new ArrayList<>();
		Pair st = new Pair(x, y);

		points.add(st);
		dist[x][y] = 0;
		q.offer(st);

		while (!q.isEmpty()) {
			Pair current = q.poll();
			int cx = current.x;
			int cy = current.y;

			for (int dir = 0; dir < 4; dir++) {
				int nx = cx + DX[dir];
				int ny = cy + DY[dir];

				if (isOutOfRange(nx, ny) || map[nx][ny] == W || dist[nx][ny] != -1) {
					continue;
				}

				Pair next = new Pair(nx, ny);
				dist[nx][ny] = dist[cx][cy] + 1;
				q.offer(next);
				points.add(next);
			}
		}

		int maxDist = -1;

		for (Pair p : points) {
			maxDist = Math.max(maxDist, dist[p.x][p.y]);

		}

		return maxDist;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= r || y < 0 || y >= c;
	}

	static void init() {
		r = scan.nextInt();
		c = scan.nextInt();

		map = new char[r][c];
		dist = new int[r][c];

		initDist();

		for (int i = 0; i < r; i++) {
			map[i] = scan.next().toCharArray();
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
