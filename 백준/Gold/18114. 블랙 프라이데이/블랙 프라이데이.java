import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, c;
	static long[] w;

	public static void main(String[] args) {
		init();
		System.out.println(solve() ? 1 : 0);
	}

	static boolean solve() {
		Arrays.sort(w);

		for (int i = 0; i < n; i++) {
			long target = c - w[i];

			if (target == 0) {
				return true;
			}

			// 물건 두 개
			int idx = Arrays.binarySearch(w, target);

			if (idx < n && idx >= 0 && idx != i) {
				return true;
			}

			// 물건 세 개
			for (int j = 0; j < n; j++) {
				if (i == j) {
					continue;
				}

				idx = Arrays.binarySearch(w, target - w[j]);
				if (idx < n && idx >= 0 && idx != j && idx != i) {
					return true;
				}
			}

		}

		return false;
	}

	static void init() {
		n = scan.nextInt();
		w = new long[n];

		c = scan.nextInt();

		for (int i = 0; i < n; i++) {
			w[i] = scan.nextLong();
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		long nextLong() {
			return Long.parseLong(next());
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			try {
				while (st == null || !st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return st.nextToken();
		}
	}
}
