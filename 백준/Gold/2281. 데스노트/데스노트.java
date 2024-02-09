import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int n, m;
	static int[] nameLengths;
	static int[][] memo;

	public static void main(String[] args) {
		input();
		System.out.println(solve(1, nameLengths[0] + 1));
	}

	// 현재 적어야 할 이름이 i번째 이름이고, begin 부터 시작한다.
	public static int solve(int i, int begin) {
		if (i == n) {
			return 0;
		}

		// 예외: 한 칸 남았거나 꽉 찬 경우
		if (begin >= m) {
			return solve(i + 1, nameLengths[i] + 1) + (begin == m ? 1 : 0);
		}

		if (memo[i][begin] != INF) {
			return memo[i][begin];
		}

		// 다음 칸에 쓰는 경우, 현재 공백을 더하고 다음 상태로 간다.
		memo[i][begin] = solve(i + 1, nameLengths[i] + 1) + getSquare(m - begin + 1);

		// 현재 칸에 쓸 수 있는 경우
		if (begin + nameLengths[i] <= m) {
			memo[i][begin] = Math.min(memo[i][begin],
				solve(i + 1, begin + nameLengths[i] + 1));
		}

		return memo[i][begin];
	}

	public static int getSquare(int n) {
		return n * n;
	}

	public static void input() {
		n = scan.nextInt();
		m = scan.nextInt();
		nameLengths = new int[n];
		memo = new int[n + 1][m + 1];

		for (int i = 0; i <= n; i++){
			for (int j = 0; j <= m; j++){
				memo[i][j] = INF;
			}
		}

		for (int i = 0; i < n; i++) {
			nameLengths[i] = scan.nextInt();
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
