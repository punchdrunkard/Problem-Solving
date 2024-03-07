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
	static int n, m;
	static int[][] board;

	static int[] DX = {1, 0};
	static int[] DY = {0, 1};
	static class State {
		int x;
		int y;
		int dist;

		State(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}

	public static void main(String[] args) {
		input();
		solve();
	}

	static void solve() {
		Queue<State> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];

		q.offer(new State(0, 0, 0));
		visited[0][0] = true;

		while (!q.isEmpty()) {
			State current = q.poll();
			int x = current.x;
			int y = current.y;

			if (x == n - 1 && y == m - 1){
				System.out.println(current.dist);
				return;
			}

			for (int dir = 0; dir < 2; dir++){
				for (int dist = 1; dist <= board[x][y]; dist++) {
					int nx = x + dist * DX[dir];
					int ny = y + dist * DY[dir];

					if (!isValidRange(nx, ny) || visited[nx][ny]) {
						continue;
					}

					q.offer(new State(nx, ny, current.dist + 1));
					visited[nx][ny] = true;
				}
			}
		}
	}

	static boolean isValidRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

	static void input() {
		n = scan.nextInt();
		m = scan.nextInt();

		board = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] = scan.nextInt();
			}
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

