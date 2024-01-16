import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static final int TARGET = 500;

	static final int SAFE = 0;
	static final int DEATH = -1;
	static final int DANGER = 1;

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int[][] board = new int[TARGET + 1][TARGET + 1];

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	public static int solve(){
		// (0, 0) ~ (500, 500)
		int[] DX = {-1, 1, 0, 0};
		int[] DY = {0, 0, -1, 1};

		Deque<Point> deque = new ArrayDeque<>();
		int[][] dist = new int[TARGET + 1][TARGET + 1];

		for (int i = 0; i <= 500; i++){
			for (int j = 0; j <= 500; j++){
				dist[i][j] = -1; // 아직 방문하지 않음
			}
		}

		deque.offer(new Point(0, 0));
		dist[0][0] = 0;

		while (!deque.isEmpty()){
			Point current = deque.poll();

			if (current.x == TARGET && current.y == TARGET) {
				return dist[TARGET][TARGET];
			}

			for (int dir = 0; dir < 4; dir++){
				int nx = current.x + DX[dir];
				int ny = current.y + DY[dir];

				if (!isValidRange(nx, ny)) continue;
				if (dist[nx][ny] != -1) continue;
				if (board[nx][ny] == DEATH) continue;

				if (board[nx][ny] == SAFE) { // 가중치 0
					dist[nx][ny] = dist[current.x][current.y];
					deque.offerFirst(new Point(nx, ny));
				} else if (board[nx][ny] == DANGER){ // 가중치 1
					dist[nx][ny] = dist[current.x][current.y] + 1;
					deque.offerLast(new Point(nx, ny));
				}
			}
		}

		return dist[TARGET][TARGET];
	}


	public static boolean isValidRange(int x, int y) {
		return 0 <= x && x <= TARGET && 0 <= y && y <= TARGET;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine()); // 위험한 공간의 수

		for (int i = 0; i < n; i++){
			OneLineParser lineParser = new OneLineParser(br.readLine());

			// 위험한 공간의 한 모서리
			int x1 = lineParser.nextInt();
			int y1 = lineParser.nextInt();

			// 위험한 공간의 반대 모서리
			int x2 = lineParser.nextInt();
			int y2 = lineParser.nextInt();

			fill2DArray(board, DANGER, new Point(x1, y1), new Point(x2, y2));
		}

		m = Integer.parseInt(br.readLine()); // 죽음의 공간의 수;

		for (int i = 0; i < m; i++){
			OneLineParser lineParser = new OneLineParser(br.readLine());

			// 위험한 공간의 한 모서리
			int x1 = lineParser.nextInt();
			int y1 = lineParser.nextInt();

			// 위험한 공간의 반대 모서리
			int x2 = lineParser.nextInt();
			int y2 = lineParser.nextInt();

			fill2DArray(board, DEATH, new Point(x1, y1), new Point(x2, y2));
		}
	}

	public static void fill2DArray(int[][] arr, int val, Point p1, Point p2){
		int xFrom = Math.min(p1.x, p2.x);
		int xTo = Math.max(p1.x, p2.x);
		int yFrom = Math.min(p1.y, p2.y);
		int yTo = Math.max(p1.y, p2.y);

		for (int x = xFrom; x <= xTo; x++){
			for (int y = yFrom; y <= yTo; y++){
					arr[x][y] = val;
			}
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
