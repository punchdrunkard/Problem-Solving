import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/1.inp");
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		while (scan.hasNext()) {
			int n = scan.nextInt();
			solve(n);
		}
	}

	static void solve(int n) {
		//  시작 문자열 -> `-이 n개
		// 집합 배열의 크기
		int len = (int)Math.pow(3, n);
		boolean[] blank = new boolean[len];

		go(0, len - 1, blank);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < blank.length; i++) {
			sb.append(blank[i] ? ' ' : '-');
		}
		System.out.println(sb);
	}

	static void go(int lo, int hi, boolean[] blank) {
		// base case
		if (hi - lo + 1 == 1) {
			return;
		}

		// recursive case
		int unit = (hi - lo + 1) / 3;
		// 3등분 한 후, 가운데 문자열을 공백으로 바꾼다.
		for (int i = 0; i < unit; i++) {
			blank[lo + unit + i] = true;
		}

		go(lo, lo + unit - 1, blank); // for left
		go(lo + unit + unit, hi, blank); // for right
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

		boolean hasNext() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = br.readLine();
					if (line == null) { // EOF에 도달하면 false 반환
						return false;
					}
					st = new StringTokenizer(line);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
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
