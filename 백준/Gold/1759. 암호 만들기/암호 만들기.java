import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/1.inp");
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	// l -> 암호의 길이
	// c -> 알파벳 집합의 길이
	static int l, c;
	static char[] alphabets;

	public static void main(String[] args) {
		init();
		solve(0, new ArrayList<>(), 0, 0);
		System.out.println(sb);
	}

	// alphabets에 있는 char 을 "조건에 맞게" 개 고르기
	static void solve(int start, List<Character> current, int vowelCount, int consonantCount) {
		// base case
		if (current.size() == l) {
			// 조건 : 최소 한 개의 모음, 최소 두 개의 자음
			if (vowelCount >= 1 && consonantCount >= 2) {
				for (char c : current) {
					sb.append(c);
				}
				sb.append('\n');
				return;
			}
		}

		// recursive case
		for (int i = start; i < alphabets.length; i++) {
			// i번째 원소를 택한다.
			current.add(alphabets[i]);

			boolean isCurrentCharVowel = isVowel(alphabets[i]);
			solve(i + 1, current, vowelCount + (isCurrentCharVowel ? 1 : 0),
				consonantCount + (isCurrentCharVowel ? 0 : 1));
			current.remove(current.size() - 1);
		}
	}

	static boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	static void init() {
		l = scan.nextInt();
		c = scan.nextInt();
		alphabets = new char[c];
		for (int i = 0; i < c; i++) {
			alphabets[i] = scan.next().charAt(0);
		}

		Arrays.sort(alphabets);
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
