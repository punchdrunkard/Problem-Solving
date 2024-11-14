import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static long[] arr;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static long solve() {
		long minDifference = Long.MAX_VALUE;

		// 첫 번째 구간의 포인터 [firstLow, firstHigh]
		for (int firstHigh = 0; firstHigh < n; firstHigh++) {
			for (int firstLow = 0; firstLow < firstHigh; firstLow++) {
				long firstSum = arr[firstLow] + arr[firstHigh];

				// 두 번째 구간의 포인터 [secondLow, secondHigh]
				int secondLow = firstLow + 1;
				int secondHigh = firstHigh - 1;

				while (secondLow < secondHigh) {
					long secondSum = arr[secondLow] + arr[secondHigh];
					minDifference = Math.min(minDifference, Math.abs(firstSum - secondSum));

					if (firstSum > secondSum) {
						secondLow++;
					} else {
						secondHigh--;
					}
				}
			}
		}

		return minDifference;
	}

	static void init() {
		n = scan.nextInt();
		arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scan.nextLong();
		}
		Arrays.sort(arr);
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
