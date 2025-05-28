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
	static final StringBuilder answer = new StringBuilder();

	static int r, c;
	static int[][] board, area;

	public static void main(String[] args) {
		init();
		solve();
		System.out.println(answer);
	}

	static void solve() {
		divideArea();

		int time = 0;
		int count = countCheese();

		Queue<Pair> q = initQueue();

		while (countCheese() > 0) {
			time++;
			int sz = q.size();

			for (int i = 0; i < sz; i++) {
				Pair current = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = current.x + DX[d];
					int ny = current.y + DY[d];
					if (isOutOfRange(nx, ny) || board[nx][ny] != 1) {
						continue;
					}
					board[nx][ny] = 0;
					q.offer(new Pair(nx, ny));
				}
			}

			count = q.size();
			mergeAir(q);
		}

		answer.append(time).append('\n').append(count);
	}

	static void mergeAir(Queue<Pair> q) {
		Queue<Pair> temp = new LinkedList<>(q);
		while (!temp.isEmpty()) {
			int size = temp.size();
			for (int i = 0; i < size; i++) {
				Pair current = temp.poll();

				for (int d = 0; d < 4; d++) {
					int nx = current.x + DX[d];
					int ny = current.y + DY[d];

					if (isOutOfRange(nx, ny) || area[nx][ny] != 0) {
						continue;
					}

					area[nx][ny] = -1;
					q.offer(new Pair(nx, ny));
					temp.offer(new Pair(nx, ny));
				}
			}
		}
	}

	static int countCheese() {
		int count = 0;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	static void copyArr(int[][] from, int[][] to) {
		for (int i = 0; i < from.length; i++) {
			for (int j = 0; j < from[0].length; j++) {
				to[i][j] = from[i][j];
			}
		}
	}

	static Queue<Pair> initQueue() {
		Queue<Pair> q = new LinkedList<>();
		boolean[][] visited = new boolean[r + 2][c + 2];
		// 치즈와 인접한 바깥 공기 넣기!
		for (int x = 0; x < area.length; x++) {
			for (int y = 0; y < area[0].length; y++) {
				if (area[x][y] == 1) {
					for (int d = 0; d < 4; d++) {
						int nx = x + DX[d];
						int ny = y + DY[d];
						if (isOutOfRange(nx, ny) || visited[nx][ny] || area[nx][ny] != -1) {
							continue;
						}
						q.offer(new Pair(nx, ny));
						visited[nx][ny] = true;
					}
				}
			}
		}

		return q;
	}

	// 치즈 (1) / 외부 공기 (-1) / 내부 공기 (0) 의 구역을 나눈다
	static void divideArea() {
		for (int i = 0; i < r + 2; i++) {
			for (int j = 0; j < c + 2; j++) {
				if (board[i][j] == 1) {
					area[i][j] = 1;
				} else {
					area[i][j] = 0;
				}
			}
		}

		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0));

		while (!q.isEmpty()) {
			int sz = q.size();
			for (int i = 0; i < sz; i++) {
				Pair current = q.poll();
				for (int d = 0; d < 4; d++) {
					int nx = current.x + DX[d];
					int ny = current.y + DY[d];
					// 이미 도착했다면 1일수가 없음!
					if (isOutOfRange(nx, ny) || area[nx][ny] != 0) {
						continue;
					}

					area[nx][ny] = -1;
					q.offer(new Pair(nx, ny));
				}
			}
		}
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= r + 2 || y < 0 || y >= c + 2;
	}

	static void init() {
		r = scan.nextInt();
		c = scan.nextInt();
		// 맨 처음 공기를 처리하기 위함
		board = new int[r + 2][c + 2];
		area = new int[r + 2][c + 2];
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
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
				sb.append('[').append(arr[i][j] == -1 ? " " : arr[i][j]).append("]");
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
