import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, k;

	static int answer = 0;

	// 현재 가지고 있는 단어 set
	static Set<Character> basicSet = new HashSet<>(Arrays.asList('a', 'n', 't', 'c', 'i'));
	static Set<Character>[] words;

	static boolean[] visited;

	public static void main(String[] args) {
		input();

		solve(0, 'a');

		System.out.println(answer);
	}

	// 현재 가지고 있는 단어 set 을 만들고,
	// 이 set 으로 최대 몇 개의 단어를 읽을 수 있는지 찾는다.
	static void solve(int count, char last) {
		if (k < 5) {
			return;
		}

		if (k >= 26) {
			answer = n;
			return;
		}

		// k 개의 단어를 모두 고른 경우
		if (count == k - 5) {
			int readCount = countReadableWords();
			answer = Math.max(answer, readCount);
			return;
		}

		for (char c = last; c <= 'z'; c++) {
			if (visited[c - 'a']) {
				continue;
			}

			visited[c - 'a'] = true;
			solve(count + 1, c);
			visited[c - 'a'] = false;
		}
	}

	// 현재 가지고 있는 알파벳 set -> visited 에 정보가 존재함
	static int countReadableWords() {
		int count = 0;

		for (var word : words) {
			if (canRead(word)) {
				count++;
			}
		}

		return count;
	}

	static boolean canRead(Set<Character> word) {
		for (char c : word) {
			if (!visited[c - 'a']) {
				return false;
			}
		}
		return true;
	}

	static void input() {
		n = scan.nextInt();
		k = scan.nextInt();

		words = new HashSet[n];
		for (int i = 0; i < n; i++) {
			words[i] = new HashSet<>();
		}

		// anta__tica 에 들어가는 단어들은 기본적으로 가지고 있어야 함
		visited = new boolean[26];
		for (char c : basicSet) {
			visited[c - 'a'] = true;
		}

		for (int i = 0; i < n; i++) {
			String word = scan.next();
			String sub = word.substring(4, word.length() - 4);

			for (char ch : sub.toCharArray()) {
				words[i].add(ch);
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
