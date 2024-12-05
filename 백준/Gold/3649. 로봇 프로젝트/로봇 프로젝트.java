import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static long target;
	static int n;
	static long[] blocks;

	static StringBuilder sb = new StringBuilder();

	static class Pair {
		long x, y;

		Pair(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return x + " " + y;
		}
	}

	public static void main(String[] args) {

		String next;
		while ((next = scan.next()) != null) {
			init(next);
			solve();
		}

		System.out.println(sb);
	}

	static void solve() {
		Arrays.sort(blocks);

		int lo = 0;
		int hi = n - 1;
		boolean found = false;

		Pair answer = new Pair(0, 0);

		while (lo < hi) {
			long currentSum = blocks[lo] + blocks[hi];

			if (currentSum > target) {
				hi--;
				continue;
			}

			if (currentSum < target) {
				lo++;
				continue;
			}

			found = true;

			if (Math.abs(blocks[lo] - blocks[hi]) >= Math.abs(answer.x - answer.y)) {
				answer = new Pair(blocks[lo], blocks[hi]);
			}

			lo++;
			hi--;
		}

		if (found && answer.x + answer.y == target) {
			sb.append("yes ").append(answer).append('\n');
			return;
		}

		sb.append("danger").append('\n');

	}

	static void init(String next) {
		long width = Long.parseLong(next) * (10_000_000);
		target = width;
		n = scan.nextInt();
		blocks = new long[n];
		for (int i = 0; i < n; i++) {
			blocks[i] = scan.nextLong();
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		String next() {
			try {
				while (st == null || !st.hasMoreTokens()) {
					String line = br.readLine();

					if (line == null) {
						return null;
					}

					st = new StringTokenizer(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return st.nextToken();
		}
	}
}
