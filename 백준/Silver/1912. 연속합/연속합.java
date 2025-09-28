import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static int n;
	static int[] numbers;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int[] dp = new int[n];
		Arrays.fill(dp, -1);
		dp[0] = numbers[0]; // 단, 수는 한 개 이상 선택해야 한다.
		for (int i = 1; i < n; i++) {
			dp[i] = Math.max(dp[i - 1] + numbers[i], numbers[i]);
		}

		return Arrays.stream(dp).max().getAsInt();
	}

	static void init() {
		n = scan.nextInt();
		numbers = new int[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = scan.nextInt();
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
