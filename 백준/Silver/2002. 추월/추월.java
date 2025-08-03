import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/3.inp");
	static FastReader scan = new FastReader();

	static int n;
	static Map<String, Integer> in;
	static String[] out;
	
	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int count = 0;
		// a는 추월한 차량인가?
		for (int i = 0; i < n; i++) {
			String current = out[i];
			int currentIn = in.get(current);

			// "추월했다"의 정의 -> 내 차 뒤에 나보다 일찍 들어온 차가 존재함
			for (int j = i + 1; j < n; j++) {
				if (currentIn > in.get(out[j])) {
					count++;
					break;
				}
			}
		}

		return count;
	}

	static void init() {
		n = scan.nextInt();
		in = new HashMap<>();
		for (int i = 0; i < n; i++) {
			in.put(scan.next(), i);
		}

		out = new String[n];
		for (int i = 0; i < n; i++) {
			out[i] = scan.next();
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
	}
}
