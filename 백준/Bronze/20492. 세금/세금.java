import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	public static void main(String[] args) {
		int n = scan.nextInt();
		int res1 = (n / 100) * 78;
		int res2 = n - ((((n / 100) * 20) / 100) * 22);
		System.out.println(res1 + " " + res2);
	}

	static class FastReader {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
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
