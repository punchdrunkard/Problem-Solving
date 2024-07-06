import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static final int[] DX = {-1, 0, 1, 0};
	static final int[] DY = {0, 1, 0, -1};
	static final int INF = Integer.MAX_VALUE;

	static int w, h;
	static char[][] board;

	static List<Integer> targetX, targetY;
	static PriorityQueue<State> pq = new PriorityQueue<>();

	static class State implements Comparable<State> {
		int x, y, d, cost;

		State(int x, int y, int d, int cost) {
			this.x = x;
			this.y = y;
			this.d = d;
			this.cost = cost;
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) {
		init();
		solve();
	}

	// 거울 갯수의 최솟값
	static void solve() {
		int[][][] dist = initDist();

		int sx = targetX.get(0);
		int sy = targetY.get(0);

		// 출발점에서 빛의 4방향으로 초기 상태를 설정
		for (int dir = 0; dir < 4; dir++) {
			pq.add(new State(sx, sy, dir, 0));
			dist[sx][sy][dir] = 0;
		}

		// dijkstra
		while (!pq.isEmpty()) {
			State current = pq.poll();
			int cx = current.x;
			int cy = current.y;
			int cd = current.d;
			int currentCost = current.cost;

			int nx = cx + DX[cd];
			int ny = cy + DY[cd];

			// 진행할 수 없는 경우
			if (isOutOfRange(nx, ny) || board[nx][ny] == '*') {
				continue;
			}

			// 잘못된 점
			if (currentCost > dist[nx][ny][cd]) {
				continue;
			}

			// 도착한 경우
			if (nx == targetX.get(1) && ny == targetY.get(1)) {
				dist[nx][ny][cd] = Math.min(dist[nx][ny][cd], currentCost);
				continue;
			}

			if (board[nx][ny] == '.') {
				// 현재 방향으로 진행하는 경우
				if (dist[nx][ny][cd] > currentCost) {
					dist[nx][ny][cd] = currentCost;
					pq.offer(new State(nx, ny, cd, currentCost));
				}

				// 거울을 설치해서 진행하는 경우
				int[] nextDirections = getNextDirection(cd);
				int nextCost = currentCost + 1; // 거울 설치

				for (int newDirection : nextDirections) {
					if (dist[nx][ny][newDirection] > nextCost) {
						dist[nx][ny][newDirection] = nextCost;
						pq.offer(new State(nx, ny, newDirection, nextCost));
					}
				}
			}
		}

		int answer = INF;
		for (int dir = 0; dir < 4; dir++) {
			answer = Math.min(dist[targetX.get(1)][targetY.get(1)][dir], answer);
		}
		System.out.println(answer);
	}

	static int[] getNextDirection(int dir) {
		return new int[] {(dir + 1) % 4, (dir - 1 + 4) % 4};
	}

	static int[][][] initDist() {
		// dist[x][y][dir] := dir 방향의 빛이 (x, y) 로 가기 위한 최소 거울 갯수
		int[][][] dist = new int[h][w][4];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				for (int dir = 0; dir < 4; dir++) {
					dist[i][j][dir] = INF;
				}
			}
		}

		return dist;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= h || y < 0 || y >= w;
	}

	static void init() {
		w = scan.nextInt();
		h = scan.nextInt();
		board = new char[h][w];
		targetX = new ArrayList<>();
		targetY = new ArrayList<>();

		for (int i = 0; i < h; i++) {
			board[i] = scan.next().toCharArray();

			for (int j = 0; j < w; j++) {
				if (board[i][j] == 'C') {
					targetX.add(i);
					targetY.add(j);
				}
			}
		}
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
