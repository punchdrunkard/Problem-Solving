import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static int r, c;
	static char[][] board;
	static boolean[] visited;

	static int answer = -1;

	public static void main(String[] args) {
		init();
		// solve();

		visited[board[0][0] - 'A'] = true;
		dfs(0, 0, 1);

		System.out.println(answer);
	}

	static void dfs(int x, int y, int count) {
		answer = Math.max(answer, count);

		// recursive case
		for (int d = 0; d < 4; d++) {
			int nx = x + DX[d];
			int ny = y + DY[d];

			// 더 이상 진행할 수 없는 경우
			if (isOutOfRange(nx, ny) || visited[board[nx][ny] - 'A']) {
				continue;
			}

			visited[board[nx][ny] - 'A'] = true;
			dfs(nx, ny, count + 1);
			visited[board[nx][ny] - 'A'] = false;
		}
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= r || y < 0 || y >= c;
	}

	static void init() {
		r = scan.nextInt();
		c = scan.nextInt();
		board = new char[r][c];
		for (int i = 0; i < r; i++) {
			board[i] = scan.next().toCharArray();
		}

		visited = new boolean['Z' - 'A' + 1];
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String fileName) {
			try {
				br = new BufferedReader(new FileReader(fileName));
			} catch (IOException e) {
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
	}
}
