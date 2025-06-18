import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static final int INF = Integer.MAX_VALUE;
	static FastReader scan = new FastReader();
	static int n, m;
	static boolean[] isBroken;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	// 어짜피
	static int solve() {
		if (n == 100) {
			return 0;
		}

		int min = Math.abs(n - 100);
		for (int channel = 0; channel <= 1000000; channel++) {
			if (canMakeChannel(channel)) {
				int buttonCount = countDigit(channel) + Math.abs(n - channel);
				min = Math.min(min, buttonCount);
			}
		}

		return min;
	}

	static int countDigit(int channel) {
		String channelStr = String.valueOf(channel);
		return channelStr.length();
	}

	static boolean canMakeChannel(int channel) {
		String channelStr = String.valueOf(channel);
		for (int i = 0; i < channelStr.length(); i++) {
			if (isBroken[channelStr.charAt(i) - '0']) {
				return false;
			}
		}

		return true;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		isBroken = new boolean[10];
		for (int i = 0; i < m; i++) {
			isBroken[scan.nextInt()] = true;
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String fileName) {
			try {
				br = new BufferedReader(new FileReader(fileName));
			} catch (IOException e) {
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
	}
}
