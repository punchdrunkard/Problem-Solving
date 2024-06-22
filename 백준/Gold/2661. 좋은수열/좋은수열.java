import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static String answer = "";

	public static void main(String[] args) {
		n = scan.nextInt();
		solve(0, "");
		System.out.println(answer);
	}

	static boolean solve(int count, String seq) {
		if (count == n) {
			answer = seq;
			return true;
		}

		for (int i = 1; i <= 3; i++) {
			String next = seq + (char) (i + '0');
			if (isGood(next)) {
				if (solve(count + 1, next)) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean isGood(String seq) {
		int len = seq.length();
		for (int size = 1; size <= len / 2; size++) {
			String sub1 = seq.substring(len - size * 2, len - size);
			String sub2 = seq.substring(len - size, len);
			if (sub1.equals(sub2)) {
				return false;
			}
		}
		return true;
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
