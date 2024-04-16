import java.util.*;
import java.io.*;

public class Main {
	static FastReader scan = new FastReader();
	static int n;
	static int[] f;

	public static void main(String[] args) {
		init();
		Arrays.sort(f);

		long count = 0;

		for (int i = 0; i < n; i++) {
			// 검사해야 하는 가장 큰 파일의 상한 값을 구하기
			int upper = upperBound(f, (double)f[i] / 0.9);
			count += (upper - i - 1);
		}

		System.out.println(count);
	}

	// arr[i] > x 를 만족하는 i의 최솟값은?
	static int upperBound(int[] arr, double x) {
		int lo = -1;
		int hi = arr.length;

		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;

			if (!(arr[mid] > x)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return hi;
	}

	static void init() {
		n = scan.nextInt();
		f = new int[n];
		for (int i = 0; i < n; i++) {
			f[i] = scan.nextInt();
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

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
