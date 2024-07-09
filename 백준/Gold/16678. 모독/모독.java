import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static int[] a;

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		Arrays.sort(a);

		// a 수열은 1부터 시작해야 하고, 각각의 차이가 1이하여야 한다.
		long answer = 0;

		if (a[0] != 1) {
			answer += (a[0] - 1);
			a[0] = 1;
		}

		for (int i = 1; i < n; i++) {
			int diff = a[i] - a[i - 1];

			if (diff <= 1) {
				continue;
			}

			a[i] -= (diff);
			a[i]++;
			answer += (diff - 1);
		}

		System.out.println(answer);
	}

	static void init() {
		n = scan.nextInt();
		a = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
	}
}
