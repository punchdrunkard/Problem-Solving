import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int h, w;
	static int[] heights;

	public static void main(String[] args) {
		input();
		System.out.println(solve());
	}

	static int solve() {
		int totalWater = 0;

		int[] leftMax = new int[w]; // leftMax[i] := 0 ~ i 까지 왼쪽 최대
		leftMax[0] = heights[0];

		int[] rightMax = new int[w]; // rightMax[i] := i ~ w - 1 까지의 오른쪽 최대
		rightMax[w - 1] = heights[w - 1];

		for (int i = 1; i < w; i++) {
			leftMax[i] = Math.max(leftMax[i - 1], heights[i]);
		}
		for (int i = w - 2; i >= 0; i--) {
			rightMax[i] = Math.max(rightMax[i + 1], heights[i]);
		}

		for (int i = 1; i < w - 1; i++) {
			int waterLevel = Math.min(leftMax[i], rightMax[i]);
			int water = waterLevel - heights[i];

			if (water > 0) {
				totalWater += water;
			}
		}

		return totalWater;
	}

	static void input() {
		h = scan.nextInt();
		w = scan.nextInt();
		heights = new int[w];
		for (int i = 0; i < w; i++) {
			heights[i] = scan.nextInt();
		}

	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) {
			try {
				br = new BufferedReader(new FileReader(new File(s)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
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

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
