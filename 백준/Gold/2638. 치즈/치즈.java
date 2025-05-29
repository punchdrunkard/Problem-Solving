import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static int n, m;
	static int[][] board;
	static boolean[][] isOutside;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int time = 0;
		findOutsideArea();

		// 치즈를 녹인다.
		while (countCheese() > 0) { // 모든 치즈가 녹을 떄 까지
			time++;
			meltCheese();
		}

		return time;
	}

	static void meltCheese() {
		// 1 치즈 중, 녹을 에정인 치즈를 큐에 넣자.
		boolean[][] visited = new boolean[n + 2][m + 2];
		Queue<Pair> q = findMeltingCandidate(visited); // 외부공기가 될 값들을 넣어두는 큐

		//2. 큐 처리
		while (!q.isEmpty()) {
			int sz = q.size();

			for (int i = 0; i < sz; i++) {
				// 녹이거나, 공기를 합치거나
				Pair c = q.poll();

				// 치즈를 녹이는 경우
				if (board[c.x][c.y] == 1) {
					board[c.x][c.y] = 0;
					isOutside[c.x][c.y] = true;
				} else if (board[c.x][c.y] == 0 && !isOutside[c.x][c.y]) { // 내부 공기와 외부 공기가 합쳐지는 경우
					isOutside[c.x][c.y] = true;
				}

				// 인접영역이 영향 받는 경우 -> 인접 영역이 공기라면? 처리해줘야 한다.
				for (int d = 0; d < 4; d++) {
					int nx = c.x + DX[d];
					int ny = c.y + DY[d];
					if (isOutOfRange(nx, ny) || visited[nx][ny] || board[nx][ny] != 0) {
						continue;
					}

					q.offer(new Pair(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}
	}

	static Queue<Pair> findMeltingCandidate(boolean[][] visited) {
		Queue<Pair> q = new LinkedList<>();
		for (int x = 0; x < n + 2; x++) {
			for (int y = 0; y < m + 2; y++) {
				if (board[x][y] != 1) {
					continue;
				}

				int count = 0; // 인접한 외부 공기 카운트
				for (int d = 0; d < 4; d++) {
					int nx = x + DX[d];
					int ny = y + DY[d];
					if (isOutOfRange(nx, ny) || !isOutside[nx][ny]) {
						continue;
					}

					count++;
				}
				if (count >= 2) {
					q.offer(new Pair(x, y));
					visited[x][y] = true;
				}
			}
		}
		return q;
	}

	static int countCheese() {
		int count = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 1) {
					count++;
				}
			}
		}

		return count;
	}

	static void findOutsideArea() {
		isOutside = new boolean[n + 2][m + 2];
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0));
		boolean[][] visited = new boolean[n + 2][m + 2];
		visited[0][0] = true;

		while (!q.isEmpty()) {
			int sz = q.size();
			for (int i = 0; i < sz; i++) {
				Pair c = q.poll();

				for (int d = 0; d < 4; d++) {
					int nx = c.x + DX[d];
					int ny = c.y + DY[d];

					if (isOutOfRange(nx, ny) || visited[nx][ny] || board[nx][ny] != 0) {
						continue;
					}

					isOutside[nx][ny] = true;
					visited[nx][ny] = true;
					q.offer(new Pair(nx, ny));
				}
			}
		}
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n + 2 || y < 0 || y >= m + 2;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		board = new int[n + 2][m + 2];
		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < m + 1; j++) {
				board[i][j] = scan.nextInt();
			}
		}
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair{" +
				"x=" + x +
				", y=" + y +
				'}';
		}
	}

	static void print2D(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j] == 0 ? "⬜️" : "🟨");
			}
			sb.append('\n');
		}
		System.out.println(sb);

	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) {
			try {
				br = new BufferedReader(new FileReader(new File(s)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
