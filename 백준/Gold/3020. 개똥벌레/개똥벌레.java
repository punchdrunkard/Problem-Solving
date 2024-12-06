import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, h;

	// bottom[i] := i번째 구간에서의 지나가는 석순 갯수
	static int[] bottom, top; // 석순, 종유석

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		int[] pSum = new int[h];

		for (int i = 1; i < h; i++) {
			bottom[i] += bottom[i - 1];
			top[h - i - 1] += top[h - i];
		}

		for (int i = 0; i < h; i++) {
			pSum[i] = bottom[i] + top[i];
		}

		Arrays.sort(pSum);

		int minValue = pSum[0];
		int lo = lowerBound(pSum, minValue);
		int hi = upperBound(pSum, minValue);

		System.out.println(minValue + " " + (hi - lo));
	}

	static int lowerBound(int[] arr, int value) {
		int lo = -1;
		int hi = arr.length;

		while (lo + 1 < hi) {
			int mid = lo + (hi - lo) / 2;

			if (!(arr[mid] >= value)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return hi;
	}

	static int upperBound(int[] arr, int value) {
		int lo = -1;
		int hi = arr.length;

		while (lo + 1 < hi) {
			int mid = lo + (hi - lo) / 2;

			if (!(arr[mid] > value)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return hi;
	}

	static void init() {
		n = scan.nextInt();
		h = scan.nextInt();
		bottom = new int[h];
		top = new int[h];

		for (int i = 0; i < n; i++) {
			int sz = scan.nextInt();

			if (i % 2 == 0) { // 석순
				bottom[h - sz]++;
			} else { // 종유석
				top[sz - 1]++;
			}
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
