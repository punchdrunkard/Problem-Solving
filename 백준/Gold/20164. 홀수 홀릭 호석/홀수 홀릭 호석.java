import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static StringBuilder sb = new StringBuilder();

	static int n;
	static int ansMin = Integer.MAX_VALUE;
	static int ansMax = Integer.MIN_VALUE;

	// TODO: 숫자를 분할할 때 마다 출현하는 홀수의 갯수를 세기

	public static void main(String[] args) {
		n = scan.nextInt();
		dfs(n, getOddCount(n));
		System.out.println(ansMin + " " + ansMax);
	}

	//  x 라는 숫자에 도달했을 때, 지금까지 등장한 홀수의 갯수는?
	static void dfs(int x, int totalCount) {
		// base case
		if (x < 10) {
			ansMin = Math.min(ansMin, totalCount);
			ansMax = Math.max(ansMax, totalCount);
			return;
		}

		// 2자리 수
		if (x < 100) {
			int newX = (x / 10) + (x % 10);
			dfs(newX, totalCount + getOddCount(newX));
			return;
		}

		// 3자리 수 이상 -> 가능한 모든 방법으로 분할한다.
		String s = Integer.toString(x);
		for (int i = 0; i < s.length() - 2; i++) {
			for (int j = i + 1; j < s.length() - 1; j++) {
				String part1 = s.substring(0, i + 1); // s[0..i]
				String part2 = s.substring(i + 1, j + 1); // s[i+1..j]
				String part3 = s.substring(j + 1); // s[j+1..end]

				int num1 = Integer.parseInt(part1);
				int num2 = Integer.parseInt(part2);
				int num3 = Integer.parseInt(part3);
				int sum = num1 + num2 + num3;

				dfs(sum, totalCount + getOddCount(sum));
			}
		}
	}

	static int getOddCount(int num) {
		int count = 0;

		while (num > 0) {
			int digit = num % 10;
			if (digit % 2 == 1) {
				count++;
			}
			num /= 10;
		}

		return count;
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String next() {
			while (st == null || !st.hasMoreTokens()) {
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
