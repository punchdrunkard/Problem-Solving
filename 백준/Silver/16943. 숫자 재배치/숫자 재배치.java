import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int a, b;

	static List<List<Integer>> sequences = new ArrayList<>();

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		int answer = -1;

		String s = String.valueOf(a);
		int n = s.length();
		boolean[] visited = new boolean[n];
		dfs(0, n, new ArrayList<>(), visited);

		for (List<Integer> seq : sequences) {
			// 해당 seq 로
			StringBuilder c = new StringBuilder();

			for (int i : seq) {
				c.append(s.charAt(i));
			}

			String cStr = c.toString();
			int cVal = Integer.parseInt(cStr);
			if (cStr.charAt(0) != '0' && cVal < b) {
				answer = Math.max(cVal, answer);
			}
		}

		System.out.println(answer);
	}

	static void dfs(int idx, int n, List<Integer> current, boolean[] visited) {
		if (idx >= n) {
			if (current.size() == n) {
				sequences.add(new ArrayList<>(current));
			}

			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				continue;
			}

			visited[i] = true;
			current.add(i);
			dfs(idx + 1, n, current, visited);
			visited[i] = false;
			current.remove(current.size() - 1);
		}
	}

	static void init() {
		a = scan.nextInt();
		b = scan.nextInt();
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
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
	}
}
