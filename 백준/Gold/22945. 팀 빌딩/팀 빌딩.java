import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static int[] arr;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int maxStatus = -1;
		int lo = 0;
		int hi = n - 1;

		while (lo < hi) {
			int distance = hi - lo - 1;
			int aStatus = arr[lo];
			int bStatus = arr[hi];
			maxStatus = Math.max(maxStatus, calculateTeamStatus(distance, aStatus, bStatus));

			if (aStatus < bStatus) {
				lo++;
			} else {
				hi--;
			}
		}

		return maxStatus;
	}

	static int calculateTeamStatus(int distance, int aStatus, int bStatus) {
		return distance * Math.min(aStatus, bStatus);
	}

	static void init() {
		n = scan.nextInt();
		arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scan.nextInt();
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
