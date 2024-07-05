import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int n;
	static char[][] board;

	static int sx, sy; // 시작점

	static List<Integer> targetX = new ArrayList<>();
	static List<Integer> targetY = new ArrayList<>();

	static class State implements Comparable<State> {
		int x, y, d, count;

		State(int x, int y, int d, int count) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.count = count;
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(this.count, o.count);
		}
	}

	static PriorityQueue<State> pq = new PriorityQueue<>();
	static int[][][] dist;

	public static void main(String[] args) {
		init();
		dijkstra();
	}

	static int[] changeDirection(int dir) {
		if (dir == 0 || dir == 1) {
			return new int[] {2, 3};
		}
		return new int[] {0, 1};
	}

	static void dijkstra() {
		dist = new int[n][n][4];
		for (int[][] row : dist) {
			for (int[] col : row) {
				Arrays.fill(col, Integer.MAX_VALUE);
			}
		}

		for (int dir = 0; dir < 4; dir++) {
			pq.add(new State(sx, sy, dir, 0));
			dist[sx][sy][dir] = 0;
		}

		while (!pq.isEmpty()) {
			State current = pq.poll();
			int cx = current.x;
			int cy = current.y;
			int cd = current.d;
			int currentCount = current.count;

			int nx = cx + DX[cd];
			int ny = cy + DY[cd];

			if (isOutOfRange(nx, ny) || board[nx][ny] == '*') {
				continue;
			}

			// 현재 위치에서 다음 위치로 이동
			if (dist[nx][ny][cd] > currentCount) {
				dist[nx][ny][cd] = currentCount;
				pq.add(new State(nx, ny, cd, currentCount));
			}

			if (board[nx][ny] == '!') {
				int[] nextDir = changeDirection(cd);
				for (int nd : nextDir) {
					if (dist[nx][ny][nd] > currentCount + 1) {
						dist[nx][ny][nd] = currentCount + 1;
						pq.add(new State(nx, ny, nd, currentCount + 1));
					}
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			answer = Math.min(answer, dist[targetX.get(1)][targetY.get(1)][i]);
		}
		System.out.println(answer);
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}

	static void init() {
		n = scan.nextInt();
		board = new char[n][n];

		for (int i = 0; i < n; i++) {
			board[i] = scan.next().toCharArray();
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '#') {
					targetX.add(i);
					targetY.add(j);
				}
			}
		}

		sx = targetX.get(0);
		sy = targetY.get(0);
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
	}
}
