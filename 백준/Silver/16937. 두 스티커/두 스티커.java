import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static FastReader scan = new FastReader();
	static int h, w, n;
	static int[] sr, sc;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int max = 0;

		// 서로 다른 스티커 i, j 를 고름
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (canPut(i, j)) {
					max = Math.max(max, sr[i] * sc[i] + sr[j] * sc[j]);
				}
			}
		}

		return max;
	}

	static boolean canPut(int i, int j) {
		// 옆으로 이어 붙이는 경우
		if (sc[i] + sc[j] <= w && Math.max(sr[i], sr[j]) <= h) {
			return true;
		}

		// 옆으로 이어 붙이는 경우 - i 를 rotated
		if (sr[i] + sc[j] <= w && Math.max(sc[i], sr[j]) <= h) {
			return true;
		}

		// 옆으로 이어 붙이는 경우 - j 를 rotate
		if (sc[i] + sr[j] <= w && Math.max(sr[i], sc[j]) <= h) {
			return true;
		}

		// 옆으로 이어 붙이는 경우 - 둘 다 rotate
		if (sr[i] + sr[j] <= w && Math.max(sc[i], sc[j]) <= h) {
			return true;
		}

		// 아래로 이어 붙이는 경우
		if (sr[i] + sr[j] <= h && Math.max(sc[i], sc[j]) <= w) {
			return true;
		}

		// 아래로 이어 붙이는 경우 - i를 rotate
		if (sc[i] + sr[j] <= h && Math.max(sr[i], sc[j]) <= w) {
			return true;
		}

		// 아래로 이어 붙이는 경우 - j를 rotate
		if (sr[i] + sc[j] <= h && Math.max(sc[i], sr[j]) <= w) {
			return true;
		}

		// 아래로 이어 붙이는 경우 - i, j 를 rotate
		if (sc[i] + sc[j] <= h && Math.max(sr[i], sr[j]) <= w) {
			return true;
		}

		return false;

	}

	static void init() {
		h = scan.nextInt();
		w = scan.nextInt();
		n = scan.nextInt();
		sr = new int[n];
		sc = new int[n];

		for (int i = 0; i < n; i++) {
			sr[i] = scan.nextInt();
			sc[i] = scan.nextInt();
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
