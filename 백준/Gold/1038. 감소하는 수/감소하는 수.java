import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;

	static List<Long> decreasingNumbers = new ArrayList<>();

	public static void main(String[] args) {
		n = scan.nextInt();

		for (long i = 0; i <= 9; i++) {
			decreasingNumbers.add(i);
			solve(1, i);
		}

		Collections.sort(decreasingNumbers);
		System.out.println(decreasingNumbers.size() > n ? decreasingNumbers.get(n) : -1);
	}

	static void solve(int digit, long now) {
		if (digit > 10) {
			return;
		}

		long lastNumber = now % 10;

		for (int num = 0; num <= 9; num++) {
			if (num >= lastNumber) {
				return;
			} else {
				long next = now * 10 + num;
				decreasingNumbers.add(next);
				solve(digit + 1, next);
			}
		}
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
