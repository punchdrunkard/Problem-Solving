import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main {
	static int n, m;
	static int x1, y1, x2, y2;

	// 벽일 경우 = 거리가 0, 인간일 경우 = 거리가 1
	static char[][] classroom;

	static class Point {
		int x;
		int y;

		public Point(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(bfs());
	}

	// (x1, y1) ~ (x2, y2) 까지 가는 최단거리
	public static int bfs() {
		int[] DX = {-1, 1, 0, 0};
		int[] DY = {0, 0, -1, 1};

		int[][] dist = new int[n][m];

		for (int i = 0; i < n; i++){
			// 아직 방문하지 않았다면 -1
			Arrays.fill(dist[i], -1);
		}

		Deque<Point> q = new ArrayDeque<>();
		q.offer(new Point(x1, y1));
		dist[x1][y1] = 0;

		while (!q.isEmpty()){
			Point current = q.poll();

			// 도착한 경우
			if (current.x == x2 && current.y == y2) {
				return dist[x2][y2];
			}

			for (int dir = 0; dir < 4; dir++){
				int nx = current.x + DX[dir];
				int ny = current.y + DY[dir];

				if (!isValidRange(nx, ny)) continue;
				if (dist[nx][ny] != -1) continue; // 이미 방문한 경우

				int nextDist = dist[current.x][current.y];

				if (classroom[nx][ny] != '0'){ // 가중치가 1
					nextDist++;
					q.offerLast(new Point(nx, ny));
				} else {
					// 가중치가 0인 경우, 먼저 탐색될 수 있도록 한다.
					q.offerFirst(new Point(nx, ny));
				}

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

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
