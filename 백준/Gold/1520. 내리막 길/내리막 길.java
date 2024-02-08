import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static FastReader scan = new FastReader();

	static int[][] board, memo; // memo[i][j] := (i, j)까지 갈 수 있는 경로의 개수
	static int m, n;

	public static void main(String[] args) {
		input();
		System.out.println(countPaths(m - 1, n - 1));
	}

	// (x, y) 로 갈 수 있는 경로의 갯수는?
	public static int countPaths(int x, int y){
		if (!isValidRange(x, y)) {
			return 0;
		}

		if (x == 0 && y == 0){
			memo[x][y] = 1;
			return 1; // base case
		}

		if (memo[x][y] != -1){
			return memo[x][y];
		}

		memo[x][y] = 0;

		for (int dir = 0; dir < 4; dir++){
			int px = x + DX[dir];
			int py = y + DY[dir];

			if (!isValidRange(px, py)) continue;
			if (board[px][py] <= board[x][y]) continue; // 내리막길이 아닌 경우
			memo[x][y] += countPaths(px, py);
		}

		return memo[x][y];
	}

	public static boolean isValidRange(int x, int y){
		return 0 <= x && x < m && 0 <= y && y < n;
	}

	public static void input() {
		m = scan.nextInt();
		n = scan.nextInt();
		board = new int[m][n];
		memo = new int[m][n];

		for (int i = 0; i < m; i++){
			for (int j = 0; j < n; j++){
				board[i][j] = scan.nextInt();
				memo[i][j] = -1;
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
