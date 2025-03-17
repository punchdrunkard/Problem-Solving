import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static StringBuilder sb = new StringBuilder();

	static int n, k;
	static int[] score;

	static int[] cache;

	public static void main(String[] args) {
		init();
		int maxEnergy = solve(0);
		System.out.println(maxEnergy);
	}

	// pos 위치에서 시작했을 때 얻을 수 있는 최대 탈피 에너지
	static int solve(int pos) {
		// base case
		if (pos >= n) {
			return 0;
		}

		if (cache[pos] != -1) {
			return cache[pos];
		}

		// 현재 위치의 먹이를 먹지 않음
		int skipCurrent = solve(pos + 1);

		// 현재 위치부터 먹이를 먹음
		int eatCurrent = 0;
		int satisfaction = 0;
		int nextPos;

		for (int i = pos; i < n; i++) {
			satisfaction += score[i];
			nextPos = i + 1;

			if (satisfaction >= k) {
				int energyGained = satisfaction - k;
				eatCurrent = energyGained + solve(nextPos);
				break;
			}
		}
		cache[pos] = Math.max(skipCurrent, eatCurrent);
		return cache[pos];
	}

	static void init() {
		n = scan.nextInt();
		k = scan.nextInt();
		score = new int[n];
		for (int i = 0; i < n; i++) {
			score[i] = scan.nextInt();
		}
		cache = new int[n];
		Arrays.fill(cache, -1);
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String next() {
			while (st == null || !st.hasMoreTokens()) {
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
	}
}
