import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static int[] a;

	public static void main(String[] args) {
		init();
		int answer = solve();
		System.out.println(answer);
	}

	static int solve() {
		int answer = 1;

		for (int i = 0; i < n; i++) {
			int k = i;

			// 증가하는 부분
			int increasingLength = findMaxIncreasingLength(k);
			// 감소하는 부분
			int decreasingLength = findMaxDecreasingLength(k);
			if (decreasingLength == 1 && increasingLength == 1) {
				continue;
			}

			// System.out.println("increasingLength = " + increasingLength);
			// System.out.println("decreasingLength = " + decreasingLength);
			// System.out.println("k = " + k);
			answer = Math.max(answer, increasingLength + decreasingLength - 1);
		}

		return answer;
	}

	static int findMaxIncreasingLength(int k) {
		int[] dp = new int[n];

		// dp[i] := i번 index 까지, i번 원소를 포함하는 가장 긴 증가하는 ~
		dp[0] = 1;

		for (int i = 1; i <= k; i++) {
			dp[i] = 1;

			for (int j = 0; j < i; j++) {
				if (a[i] > a[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		return dp[k];
	}

	static int findMaxDecreasingLength(int k) {
		int[] dp = new int[n];

		// dp[i] := i를 포함하여, i에서 시작하는 가장 긴 감소 수열의 길이
		dp[n - 1] = 1;

		for (int i = n - 1; i >= k; i--) {
			dp[i] = 1;

			for (int j = i + 1; j < n; j++) {
				if (a[j] < a[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		return dp[k];
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
