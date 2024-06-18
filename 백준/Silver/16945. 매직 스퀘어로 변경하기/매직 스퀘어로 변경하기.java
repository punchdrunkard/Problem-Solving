import java.util.*;
import java.io.*;

class Main {

	static FastReader scan = new FastReader();
	static int[][] arr = new int[3][3];
	static int[][][] magicSquares = {
		{{8, 1, 6}, {3, 5, 7}, {4, 9, 2}},
		{{6, 1, 8}, {7, 5, 3}, {2, 9, 4}},
		{{4, 9, 2}, {3, 5, 7}, {8, 1, 6}},
		{{2, 9, 4}, {7, 5, 3}, {6, 1, 8}},
		{{8, 3, 4}, {1, 5, 9}, {6, 7, 2}},
		{{4, 3, 8}, {9, 5, 1}, {2, 7, 6}},
		{{6, 7, 2}, {1, 5, 9}, {8, 3, 4}},
		{{2, 7, 6}, {9, 5, 1}, {4, 3, 8}}
	};
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		init();
		solve();
		System.out.println(answer);
	}

	static void solve() {
		for (int[][] magic : magicSquares) {
			int cost = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					cost += Math.abs(arr[i][j] - magic[i][j]);
				}
			}
			answer = Math.min(answer, cost);
		}
	}

	static void init() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				arr[i][j] = scan.nextInt();
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
