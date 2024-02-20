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
	static final char WHITE = '1';
	static final char BLACK = '0';

	static FastReader scan = new FastReader();

	static int n, m;
	static char[][] bitmap;

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		input();
		print2DArray(bfs());
	}

	static void print2DArray(int[][] arr) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}

		System.out.print(sb);
	}

	public static int[][] bfs() {
		int[] DX = {-1, 1, 0, 0};
		int[] DY = {0, 0, -1, 1};

		Queue<Point> q = new LinkedList<>();
		int[][] dist = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (bitmap[i][j] == WHITE) {
					dist[i][j] = 0;
					q.offer(new Point(i, j));
				} else {
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
		}

		while (!q.isEmpty()) {
			Point current = q.poll();

			int x = current.x;
			int y = current.y;

			for (int dir = 0; dir < 4; dir++) {
				int nx = x + DX[dir];
				int ny = y + DY[dir];

				if (!isValidRange(nx, ny)) {
					continue;
				}

				if (dist[nx][ny] != Integer.MAX_VALUE) {
					continue;
				}

				if (bitmap[nx][ny] == WHITE) {
					continue;
				}

				dist[nx][ny] = dist[x][y] + 1;
				q.offer(new Point(nx, ny));
			}
		}

		return dist;
	}

	static boolean isValidRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

	static void input() {
		n = scan.nextInt();
		m = scan.nextInt();
		bitmap = new char[n][m];

		for (int i = 0; i < n; i++) {
			bitmap[i] = scan.nextLine().toCharArray();
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
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
