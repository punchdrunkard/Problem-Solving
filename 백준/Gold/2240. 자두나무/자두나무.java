import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int t, w;
	static int[] plumTree;

	// cache[t][w] := t시간에 i번 나무에 있을 때, w번까지 움직일 때 받을 수 있는 자두의 갯수
	static int[][][] cache;

	public static void main(String[] args) {
		init();
		System.out.println(dp(0, 1, 0));
	}

	static int dp(int currentTime, int currentTree, int moveCount) {
		if (currentTime == t) {
			return 0;
		}

		if (moveCount > w) {
			return 0;
		}

		if (cache[currentTime][currentTree][moveCount] != -1) {
			return cache[currentTime][currentTree][moveCount];
		}

		// 현재 나무에 머무르는 경우
		int stayed = 0;
		if (plumTree[currentTime] == currentTree) {
			stayed = 1;
		}
		stayed += dp(currentTime + 1, currentTree, moveCount);

		// 다른 나무로 이동하는 경우
		int moved = 0;
		if (moveCount < w) {
			int nextTree = currentTree == 1 ? 2 : 1;
			if (plumTree[currentTime] == nextTree) {
				moved = 1;
			}
			moved += dp(currentTime + 1, nextTree, moveCount + 1);
		}

		int result = Math.max(stayed, moved);
		cache[currentTime][currentTree][moveCount] = result;
		return result;
	}

	static void init() {
		t = scan.nextInt();
		w = scan.nextInt();
		plumTree = new int[t];
		for (int i = 0; i < t; i++) {
			plumTree[i] = scan.nextInt();
		}
		cache = new int[t + 1][3][w + 1];
		for (int i = 0; i < cache.length; i++) {
			for (int j = 0; j < cache[0].length; j++) {
				Arrays.fill(cache[i][j], -1);
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
	}
}
