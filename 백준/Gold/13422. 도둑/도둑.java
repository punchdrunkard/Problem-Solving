import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int  n, m, k;
	static int[] money;

	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) {
		int t = scan.nextInt();

		for (int testCase = 0; testCase < t; testCase++){
			init();
			answer.append(solve()).append('\n');
		}

		System.out.println(answer);
	}

	static int solve() {
		int count = 0;
		long total = 0;

		// 현재 어디어디 홈침
		boolean[] stolen = new boolean[n];

		// init
		for (int i = 0; i < m; i++){
			total += money[i];
			stolen[i] = true;
		}

		if (n == m) {
			return total < k ? 1 : 0;
		}

		for (int i = 0; i < n; i++){
			if (total < k) {
				count++;
			}

			int removeHouse = i % n;
			int addHouse = (i + m) % n;

			stolen[removeHouse] = false;
			stolen[addHouse] = true;

			total -= money[removeHouse];
			total += money[addHouse];

		}

		return count;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		k = scan.nextInt();

		money = new int[n];
		for (int i = 0; i< n; i++){
			money[i] = scan.nextInt();
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
