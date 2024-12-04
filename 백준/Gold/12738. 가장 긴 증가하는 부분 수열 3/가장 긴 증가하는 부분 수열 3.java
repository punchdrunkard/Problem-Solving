import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static int[] nums;

	public static void main(String[] args) {
		init();
		System.out.println(solve());

	}

	static int solve() {
		int[] top = new int[n];
		int piles = 0;

		for (int i = 0; i < n; i++) {
			int poker = nums[i];
			int target = lowerBound(piles, top, poker);

			if (target == piles) {
				top[piles] = poker;
				piles++;
			} else {
				top[target] = poker;
			}
		}

		return piles;
	}

	static int lowerBound(int size, int[] arr, int value) {
		int lo = -1;
		int hi = size;

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

	static void init() {
		n = scan.nextInt();
		nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = scan.nextInt();
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
