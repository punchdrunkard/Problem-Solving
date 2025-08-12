import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static int n;
	static int[] a, b;

	public static void main(String[] args) {
		init();
		System.out.println(solve());

	}

	static long solve() {
		long s = 0;
		Arrays.sort(b);
		Arrays.sort(a);

		for (int i = 0; i < n; i++) {
			s += ((long)a[n - i - 1] * b[i]);
		}
		return s;
	}

	static void init() {
		n = scan.nextInt();
		a = new int[n];
		b = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
		}
		for (int i = 0; i < n; i++) {
			b[i] = scan.nextInt();
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
