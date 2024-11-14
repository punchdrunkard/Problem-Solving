import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, k;
	static int[] arr;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int maxLen = -1;
		int lo = 0;
		int hi = 0;
		int removeCount = 0;
		int currentLen = 0;

		while (hi < n) {
			// expand
			int addElement = arr[hi++];

			// 홀수인 경우 -> 삭제하고, 길이에는 포함하지 않음
			if (isOdd(addElement)) {
				removeCount++;
			} else { // 짝수인 경우 -> 길이에 포함시키
				currentLen++;
			}

			// shrink
			while (removeCount > k) {
				int removeElement = arr[lo++];

				if (isOdd(removeElement)) {
					removeCount--;
				} else {
					currentLen--;
				}
			}

			// 답 업데이트
			maxLen = Math.max(maxLen, currentLen);
		}

		return maxLen;
	}

	static boolean isOdd(int number) {
		return number % 2 != 0;
	}

	static void init() {
		n = scan.nextInt();
		k = scan.nextInt();
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
