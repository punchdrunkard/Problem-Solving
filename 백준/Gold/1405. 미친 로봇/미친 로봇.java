import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static boolean[][] visited;

	static final int E = 0;
	static final int W = 1;
	static final int S = 2;
	static final int N = 3;

	static final int[] DX = {0, 0, 1, -1};
	static final int[] DY = {1, -1, 0, 0};

	static int n;
	static double[] probabilities;

	static double answer = 0;

	public static void main(String[] args) {
		init();
		visited[15][15] = true; // 시작점 방문 처리
		dfs(15, 15, 0, 1);
		System.out.printf("%.10f%n", answer);
	}

	static void dfs(int x, int y, int count, double currentProbability) {
		// base-case
		if (count == n) {
			// System.out.println("base case " + currentProbability);
			answer += currentProbability;
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nx = x + DX[d];
			int ny = y + DY[d];

			if (visited[nx][ny]) {
				continue;
			}

			visited[nx][ny] = true;
			double nextProbability = currentProbability * probabilities[d];
			// System.out.println("next: " + nextProbability);
			dfs(nx, ny, count + 1, nextProbability);
			visited[nx][ny] = false;
		}
	}

	static void init() {
		n = scan.nextInt();
		visited = new boolean[31][31]; // n <= 14
		probabilities = new double[4];
		for (int i = 0; i < 4; i++) {
			probabilities[i] = scan.nextInt() / 100.0;
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
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
