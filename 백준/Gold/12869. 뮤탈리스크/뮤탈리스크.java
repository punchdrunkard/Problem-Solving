import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static final int[] DAMAGE = {9, 3, 1};
	static final int UNCACHED = Integer.MAX_VALUE;
	static final int MAX_HP = 60;

	static int n;
	static int[] scv;
	static int[][][] memo;
	static List<int[]> atkSequences;

	public static void main(String[] args) {
		init();
		System.out.println(dp(scv[0], scv[1], scv[2]));
	}

	// dp[a][b][c] := 체력이 a, b, c 인 scp를 모두 파괴하기 위한 최소 공격 횟수
	static int dp(int a, int b, int c) {
		// base case
		if (a == 0 && b == 0 && c == 0) {
			return 0;
		}

		if (memo[a][b][c] != UNCACHED) {
			return memo[a][b][c];
		}

		// 공격
		for (int[] seq : atkSequences) {
			int remainA = Math.max(0, a - DAMAGE[seq[0]]);
			int remainB = Math.max(0, b - DAMAGE[seq[1]]);
			int remainC = Math.max(0, c - DAMAGE[seq[2]]);

			memo[a][b][c] = Math.min(memo[a][b][c], dp(remainA, remainB, remainC) + 1);
		}

		return memo[a][b][c];
	}

	static void init() {
		n = scan.nextInt();
		scv = new int[3];
		for (int i = 0; i < n; i++) {
			scv[i] = scan.nextInt();
		}

		memo = new int[MAX_HP + 1][MAX_HP + 1][MAX_HP + 1];
		for (int i = 0; i < memo.length; i++) {
			for (int j = 0; j < memo.length; j++) {
				Arrays.fill(memo[i][j], UNCACHED);
			}
		}

		atkSequences = makeSequence();
	}

	static List<int[]> makeSequence() {
		List<int[]> seq = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == j) {
					continue;
				}

				for (int k = 0; k < 3; k++) {
					if (j == k || i == k) {
						continue;
					}

					seq.add(new int[] {i, j, k});
				}
			}
		}

		return seq;
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
