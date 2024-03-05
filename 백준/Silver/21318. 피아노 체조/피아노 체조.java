import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();

	static int n, q; // 악보의 개수

	static int[] scores, pSum;

	public static void main(String[] args) {
		input();
		solve();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < q; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			sb.append(pSum[y - 1] - pSum[x - 1]).append('\n');
		}

		System.out.println(sb);
	}

	static void solve() {
		pSum = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			if (i == n) {
				// 마지막으로 연주하는 y번 악보에선 절대 실수하지 않는다.
				pSum[i] = pSum[i - 1];
			} else {
				if (scores[i] > scores[i + 1]) {
					pSum[i] = pSum[i - 1] + 1;
				} else {
					pSum[i] = pSum[i - 1];
				}
			}
		}
	}

	static void input() {
		n = scan.nextInt();
		scores = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			scores[i] = scan.nextInt();
		}

		q = scan.nextInt();
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
