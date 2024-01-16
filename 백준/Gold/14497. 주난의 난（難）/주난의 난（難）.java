import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main {
	static int n, m;
	static int x1, y1, x2, y2;

	// 벽일 경우 = 거리가 0, 인간일 경우 = 거리가 1
	static char[][] classroom;

	static class State implements Comparable<State> {
		int x;
		int y;
		int jumpCount; // 현재 점프 횟수

		public State(int x, int y, int jumpCount) {
			this.x = x;
			this.y = y;
			this.jumpCount = jumpCount;
		}

		@Override
		public int compareTo(State o) { // 최소 힙
			return this.jumpCount - o.jumpCount;
		}

		@Override
		public String toString() {
			return String.format("(x : %d, y : %d, jumpCount: %d}", x, y, jumpCount);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(dijkstra());
	}

	// (x1, y1) ~ (x2, y2) 까지 가는 최단거리
	public static int dijkstra() {
		int[] DX = {-1, 1, 0, 0};
		int[] DY = {0, 0, -1, 1};

		Queue<State> pq = new PriorityQueue<>();
		int[][] dist = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}


		pq.offer(new State(x1, y1, 0));
		dist[x1][y1] = 0;

		while (!pq.isEmpty()) {
			var current = pq.poll();

			if (dist[current.x][current.y] < current.jumpCount) {
				continue;
			}

			// 답을 찾은 경우
			if (current.x == x2 && current.y == y2) {
				return current.jumpCount;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nx = current.x + DX[dir];
				int ny = current.y + DY[dir];

				if (!isValidRange(nx, ny))
					continue;

				int nextDist = current.jumpCount;

				if (classroom[nx][ny] != '0') {
					nextDist += 1;
				}

				if (dist[nx][ny] <= nextDist)
					continue;

				pq.offer(new State(nx, ny, nextDist));
				dist[nx][ny] = nextDist;
			}
		}

		return dist[x2][y2];
	}

	public static boolean isValidRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser lineParser = new OneLineParser(br.readLine());
		n = lineParser.nextInt();
		m = lineParser.nextInt();

		classroom = new char[n][m];
		lineParser.setNewLine(br.readLine());

		// 주난의 위치
		x1 = lineParser.nextInt() - 1;
		y1 = lineParser.nextInt() - 1;
		// 범인의 위치
		x2 = lineParser.nextInt() - 1;
		y2 = lineParser.nextInt() - 1;

		for (int i = 0; i < n; i++) {
			classroom[i] = br.readLine().toCharArray();
		}
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}

		public long nextLong(StringTokenizer st) {
			return Long.parseLong(st.nextToken());
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
