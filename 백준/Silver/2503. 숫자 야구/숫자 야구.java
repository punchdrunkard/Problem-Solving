import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static List<Integer> candidates;
	static int[][] queries;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int count = 0;

		// 특정 수가 쿼리의 모든 조건을 만족해야 한다.
		for (int candidate : candidates) {
			boolean flag = true;

			for (int[] query : queries) {
				int y = query[0];
				int strikeCount = query[1];
				int ballCount = query[2];

				if (!canBeAnswer(candidate, y, strikeCount, ballCount)) {
					flag = false;
					break;
				}
			}

			if (flag) {
				count++;
			}
		}

		return count;
	}

	static boolean canBeAnswer(int candidate, int y, int targetStrikeCount, int targetBallCount) {
		int[] cDigits = breakDownToDigit(candidate);
		int[] yDigits = breakDownToDigit(y);

		int strikeCount = countStrikeCount(cDigits, yDigits);
		int ballCount = countBallCount(cDigits, yDigits);

		return strikeCount == targetStrikeCount && ballCount == targetBallCount;
	}

	// 세 자리수 a,b의 strike 갯수를 세는 함수
	static int countStrikeCount(int[] a, int[] b) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			if (a[i] == b[i]) {
				count++;
			}
		}
		return count;
	}

	// 세자리 수 a,b의 ball 갯수를 세는 함수
	static int countBallCount(int[] a, int[] b) {
		int count = 0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) {
					continue; // strike 는 제외
				}

				if (a[i] == b[j]) {
					count++;
				}
			}
		}

		return count;
	}

	// 세자리 수를 분해함
	static int[] breakDownToDigit(int a) {
		int hund = a / 100;
		int ten = (a % 100) / 10;
		int one = a % 10;
		int[] result = {hund, ten, one};

		return result;
	}

	static void init() {
		n = scan.nextInt();
		queries = new int[n][3];
		for (int i = 0; i < n; i++) {
			queries[i] = new int[] {scan.nextInt(), scan.nextInt(), scan.nextInt()};
		}

		candidates = new ArrayList<>();
		for (int i = 1; i <= 9; i++) { // 100의 자리
			for (int j = 1; j <= 9; j++) { // 10의 자리
				for (int k = 1; k <= 9; k++) { // 1의 자리
					if (i == j || i == k || j == k) {
						continue;
					}
					candidates.add(100 * i + 10 * j + k);
				}

			}
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

		long nextLong() {
			return Long.parseLong(next());
		}
	}
}
