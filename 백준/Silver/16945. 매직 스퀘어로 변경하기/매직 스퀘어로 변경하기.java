import java.util.*;
import java.io.*;

class Main {

	static FastReader scan = new FastReader();

	static int[][] arr = new int[3][3];
	static int[][] originalArr = new int[3][3];

	static int answer = Integer.MAX_VALUE;
	static Pair[] idxMap = new Pair[9];
	static boolean[] used = new boolean[10];

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair{" +
				"x=" + x +
				", y=" + y +
				'}';
		}
	}

	public static void main(String[] args) {
		init();
		solve(0, 0);
		System.out.println(answer);
	}

	static void solve(int idx, int totalCost) {
		if (totalCost >= answer) {
			return;
		}

		if (idx >= 9) {
			if (isMagicSquare()) {
				answer = Math.min(answer, totalCost);
			}

			return;
		}

		Pair p = idxMap[idx];
		int original = arr[p.x][p.y];

		// arr[r][c] 의 값을 val 로 바꾼다.
		for (int val = 1; val <= 9; val++) {
			if (!used[val]) {
				int cost = Math.abs(original - val);
				used[val] = true;
				arr[p.x][p.y] = val;
				solve(idx + 1, totalCost + cost);
				used[val] = false;
				arr[p.x][p.y] = original;
			}
		}
	}

	static boolean isMagicSquare() {
		int sum = 15;

		for (int i = 0; i < 3; i++) {
			if (arr[i][0] + arr[i][1] + arr[i][2] != sum) {
				return false; // Rows
			}

			if (arr[0][i] + arr[1][i] + arr[2][i] != sum) {
				return false; // Columns
			}
		}

		if (arr[0][0] + arr[1][1] + arr[2][2] != sum) {
			return false; // Diagonal 1
		}

		if (arr[0][2] + arr[1][1] + arr[2][0] != sum) {
			return false; // Diagonal 2
		}

		boolean[] used = new boolean[10];
		for (int i = 0; i < 9; i++) {
			Pair p = idxMap[i];
			used[arr[p.x][p.y]] = true;
		}

		return true;
	}

	static void init() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = scan.nextInt();
				originalArr[i][j] = arr[i][j];
				idxMap[i * 3 + j] = new Pair(i, j);
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
