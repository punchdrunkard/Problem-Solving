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
		String[] res = preprocess();

		while (scan.hasNext()) {
			int n = scan.nextInt();
			sb.append(res[n]).append('\n');
		}

		System.out.println(sb);
	}

	// bottom up 방식
	static String[] preprocess() {
		String[] arr = new String[13]; // n번째 칸토어 집합

		// arr[0] = -
		// arr[1] = - - => arr[0] + ' ' + arr[0];
		// arr[2] = - -   - - => arr[1] + 공백 3칸 (3^1) + arr[1]
		// arr[3] = arr[2] + 공백 9칸 (3^2) + arr[2]
		arr[0] = "-";
		for (int i = 1; i <= 12; i++) {
			String blank = " ".repeat((int)Math.pow(3, i - 1));
			arr[i] = arr[i - 1].concat(blank).concat(arr[i - 1]);
		}

		return arr;
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
