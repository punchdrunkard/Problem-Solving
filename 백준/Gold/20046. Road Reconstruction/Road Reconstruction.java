import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int m, n;
	static int[][] board;

	static class State implements Comparable<State> {
		int x;
		int y;
		int cost;

		public State(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "State{" +
				"x=" + x +
				", y=" + y +
				", cost=" + cost +
				'}';
		}

		@Override
		public int compareTo(State o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(dijkstra());
	}

	public static int dijkstra() {
		if (board[1][1] == -1 || board[m][n] == -1) {
			return -1;
		}

		int[] DX = {-1, 1, 0, 0};
		int[] DY = {0, 0, -1, 1};

		Queue<State> pq = new PriorityQueue<>();
		int[][] dist = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++){
			for (int j = 1; j <= n; j++){
				dist[i][j] = Integer.MAX_VALUE;
			}
		}

		pq.offer(new State(1, 1, board[1][1]));
		dist[1][1] = board[1][1];

		while (!pq.isEmpty()) {
			State current = pq.poll();
			int cx = current.x;
			int cy = current.y;
			int cost = current.cost;

			if (dist[cx][cy] < cost) {
				continue;
			}

			for (int dir = 0; dir < 4; dir++) {
				int nx = cx + DX[dir];
				int ny = cy + DY[dir];

				if (!isValidRange(nx, ny)) {
					continue;
				}

				if (board[nx][ny] == -1) {
					continue;
				}

				int nextCost = board[nx][ny] + cost;

				if (dist[nx][ny] > nextCost) {
					dist[nx][ny] = nextCost;
					pq.offer(new State(nx, ny, nextCost));
				}
			}
		}

		return dist[m][n] != Integer.MAX_VALUE ? dist[m][n] : -1;
	}

	public static boolean isValidRange(int x, int y) {
		return 1 <= x && x <= m && 1 <= y && y <= n;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		m = line.nextInt();
		n = line.nextInt();

		board = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			line.setNewLine(br.readLine());

			for (int j = 1; j <= n; j++) {
				board[i][j] = line.nextInt();
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
