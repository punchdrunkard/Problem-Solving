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

		for (int i = 1; i < w - 1; i++) {
			int leftMax = getLeftMax(i);
			int rightMax = getRightMax(i);

			int waterLevel = Math.min(leftMax, rightMax);
			int water = waterLevel - heights[i];

			if (water > 0) {
				totalWater += water;
			}
		}

		return totalWater;
	}

	static int getLeftMax(int k) {
		int max = 0;
		for (int i = 0; i <= k; i++) {
			max = Math.max(heights[i], max);
		}
		return max;
	}

	static int getRightMax(int k) {
		int max = 0;
		for (int i = k; i < w; i++) {
			max = Math.max(heights[i], max);
		}
		return max;
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
