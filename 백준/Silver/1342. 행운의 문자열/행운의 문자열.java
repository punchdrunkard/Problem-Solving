import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/4.inp");
	static FastReader scan = new FastReader();
	static String s;
	static int n;

	static Set<Character> characters; // 사용할 수 있는 문자열 셋
	static int[] counter, currentCount;
	// static Set<String> answers = new HashSet<>();
	static StringBuilder sb = new StringBuilder();
	static int answer = 0;

	public static void main(String[] args) {
		s = scan.next();
		n = s.length();
		preprocess();
		backtrack(0);
		// System.out.println(answers.size());
		System.out.println(answer);
	}

	// 현재 문자열 구성을 이용해서 문자열을 만든다.
	// 행운의 문자열 -> 이전거랑 비교해서...인접해서 같으면 안된다.(백트래킹 조건)
	// index : 현재 char를 넣을 index
	static void backtrack(int index) {
		// base case
		if (index == n) {
			// answers.add(sb.toString());
			answer++;
			return;
		}

		// recursive case
		for (char c : characters) {
			// index 번째 문자열을 c로 결정하기
			if (index > 0) {
				if (sb.charAt(index - 1) == c) { // backtrack: 인접한 문자열이 같은 경우 제외
					continue;
				}
			}

			if (currentCount[c - 'a'] < counter[c - 'a']) {
				sb.append(c);
				currentCount[c - 'a']++;
				backtrack(index + 1);
				sb.deleteCharAt(sb.length() - 1);
				currentCount[c - 'a']--;
			}
		}
	}

	static void preprocess() {
		counter = new int['z' - 'a' + 1];
		currentCount = new int['z' - 'a' + 1];
		characters = new HashSet<>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			counter[c - 'a']++;
			characters.add(c);
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
