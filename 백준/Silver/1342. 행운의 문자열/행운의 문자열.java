import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/4.inp");
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	static String s;
	static Set<String> candidates;
	static int n;

	static boolean[] visited;

	public static void main(String[] args) {
		init();
		dfs(new ArrayList<>());
		// System.out.println(candidates);
		System.out.println(solve());
	}

	static int solve() {
		int count = 0;

		for (String candidate : candidates) {
			if (isLucky(candidate)) {
				count++;
			}
		}

		return count;
	}

	static boolean isLucky(String str) {

		// 그 이전거랑만 비교하면 됨
		for (int i = 1; i < n; i++) {
			if (str.charAt(i) == str.charAt(i - 1)) {
				return false;
			}
		}

		return true;
	}

	// 현재 string 으로 만들 수 있는 모든 문자열 순열 구하기
	static void dfs(List<Integer> res) {

		// base case
		if (res.size() == n) {
			// 만들어진 문자열을 set 에 포함한다.
			StringBuilder sb = new StringBuilder();
			for (int i : res) {
				sb.append(s.charAt(i));
			}

			candidates.add(sb.toString());
			return;
		}

		// index로 시작하는 ~
		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				continue;
			}

			// 현재 원소를 쓴다
			visited[i] = true;
			res.add(i);
			dfs(res);

			visited[i] = false;
			res.remove(res.size() - 1);
		}

	}

	static void init() {
		candidates = new HashSet<>();
		s = scan.next();
		n = s.length();
		visited = new boolean[n];
		candidates.add(s);

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
