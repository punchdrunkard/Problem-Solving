import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();

	static int n;
	static int[] a;

	public static void main(String[] args) {
		input();
		System.out.println(solve());
	}

	static int solve() {
		int[] dp = new int[1000];
		int answer = -1;

		for (int i = 0; i < n; i++) {
			dp[i] = a[i];
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] < a[j]) {
					dp[i] = Math.max(dp[i], dp[j] + a[i]);
				}
			}

			answer = Math.max(answer, dp[i]);
		}

		return answer;
	}

	static void input() {
		n = scan.nextInt();
		a = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
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
