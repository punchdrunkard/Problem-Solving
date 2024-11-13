import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static int n, len;
	static char[] arr;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int lo = 0;
		int hi = 0;
		int maxLength = 0;

		int[] currentAlphabets = new int[26];
		int currentCount = 0;  // 현재 서로 다른 알파벳 개수

		while (hi < arr.length) {
			// 새로운 알파벳 추가
			char addAlphabet = arr[hi];
			if (currentAlphabets[addAlphabet - 'a'] == 0) {
				currentCount++;
			}

			currentAlphabets[addAlphabet - 'a']++;
			hi++;

			// 서로 다른 알파벳이 n을 초과할 경우 축소
			while (currentCount > n) {
				char removeAlphabet = arr[lo];
				currentAlphabets[removeAlphabet - 'a']--;
				if (currentAlphabets[removeAlphabet - 'a'] == 0) {
					currentCount--;
				}
				lo++;
			}

			// 현재 최대 길이 갱신
			maxLength = Math.max(maxLength, hi - lo);
		}

		return maxLength;
	}

	static void init() {
		n = scan.nextInt();
		arr = scan.next().toCharArray();
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
