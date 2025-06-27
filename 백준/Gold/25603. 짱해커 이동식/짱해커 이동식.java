import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static int n, k;
	static int[] requests;

	public static void main(String[] args) {
		init();
		int answer = solve();
		System.out.println(answer);
	}

	static int solve() {
		int lo = 0;
		int hi = Integer.MAX_VALUE;

		while (lo + 1 < hi) {
			int mid = lo + ((hi - lo) / 2);

			if (!check(mid)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return hi;
	}

	static boolean check(int cost) {

		int count = 0; // cost 보다 큰 연속된 구간의 최솟값

		for (int i = 0; i < n; i++) {

			if (requests[i] > cost) {
				count++;
			} else {
				if (count >= k) {
					return false;
				}

				count = 0;
			}
		}

		return count < k;
	}

	static void init() {
		n = scan.nextInt();
		requests = new int[n];
		k = scan.nextInt();
		for (int i = 0; i < n; i++) {
			requests[i] = scan.nextInt();
		}
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
