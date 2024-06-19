import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] first = new int[] {2, 3, 5, 7};

	public static void main(String[] args) {
		n = scan.nextInt();

		// 제일 첫번째 숫자부터 소수여야 하므로
		for (int i = 0; i < 4; i++) {
			solve(first[i], 1);
		}

		System.out.println(sb);
	}

	// 첫째 자리가 first 인 소수
	// idx 번째 수를 채운다.
	static void solve(int number, int idx) {
		if (idx == n) {
			sb.append(number).append('\n');
			return;
		}

		// 소수를 만족시키기 위해서, 일의 자리수는 1, 3, 5, 7, 9만 가능
		for (int val = 1; val < 10; val += 2) {
			int next = number * 10 + val;
			if (isPrime(next)) {
				solve(next, idx + 1);
			}
		}
	}

	static boolean isPrime(int number) {
		if (number < 2) {
			return false;
		}

		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
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
