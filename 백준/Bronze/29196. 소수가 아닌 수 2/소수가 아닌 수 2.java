import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	public static void main(String[] args) {
		double n = scan.nextDouble();
		double p = 1;
		double q = 1;

		while (Math.abs((p / q) - n) > Math.pow(10, -6)) {
			if ((p / q) > n) {
				q++;
			} else {
				p++;
			}
		}

		System.out.println("YES" + '\n' + (int)p + ' ' + (int)q);
	}

	static class FastReader {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		double nextDouble() {
			return Double.parseDouble(next());
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
