import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static int t, n, m;
	static long[] a, b;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static long solve() {
		long answer = 0;

		long[] pSumA = calculatePrefixSum(a);
		long[] pSumB = calculatePrefixSum(b);

		Map<Long, Long> sumCounterA = getRangeSumCounter(pSumA);
		Map<Long, Long> sumCounterB = getRangeSumCounter(pSumB);

		for (long key : sumCounterA.keySet()) {
			answer += (sumCounterA.get(key) * sumCounterB.getOrDefault(t - key, 0L));
		}

		return answer;
	}

	// 각 구간에서 나올 수 있는 모든 합들 카운트
	static Map<Long, Long> getRangeSumCounter(long[] pSum) {
		Map<Long, Long> rangeSumCounter = new HashMap<>();

		for (int i = pSum.length - 1; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				long rangeSum = pSum[i];
				if (j - 1 >= 0) {
					rangeSum -= pSum[j - 1];
				}

				rangeSumCounter.put(rangeSum, rangeSumCounter.getOrDefault(rangeSum, 0L) + 1);
			}
		}

		return rangeSumCounter;
	}

	static long[] calculatePrefixSum(long[] arr) {
		long[] pSum = Arrays.copyOf(arr, arr.length);
		for (int i = 1; i < arr.length; i++) {
			pSum[i] += pSum[i - 1];
		}

		return pSum;
	}

	static void init() {
		t = scan.nextInt();

		n = scan.nextInt();
		a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = scan.nextLong();
		}

		m = scan.nextInt();
		b = new long[m];
		for (int i = 0; i < m; i++) {
			b[i] = scan.nextLong();
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
