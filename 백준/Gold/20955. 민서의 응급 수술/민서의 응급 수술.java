import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();
	static int[] parent;
	static int n, m;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int count = 0;

		// 각각 분리된 트리 형태로 만들어야 한다. (끊는 연산)
		for (int i = 0; i < m; i++) {
			int u = scan.nextInt();
			int v = scan.nextInt();

			if (!union(u, v)) { // 이미 같은 root에 속해있음 -> cycle이 생김
				count++; // 사이클을 끊어줌
			}
		}

		Set<Integer> roots = new HashSet<>();
		// 분리 집합들을 합친다.
		for (int i = 1; i <= n; i++){
			roots.add(find(i));
		}

		count += (roots.size() - 1);
		return count;
	}

	static int find(int a) {
		if (parent[a] == a) {
			return a;
		}

		int root = find(parent[a]);
		parent[a] = root;
		return root;
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return false;
		}

		parent[b] = a;
		return true;

	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
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

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
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

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}

		public Long nextLong() {
			return Long.parseLong(st.nextToken());
		}

		public String nextToken() {
			return st.nextToken();
		}

		public boolean hasMoreTokens() {
			return st.hasMoreTokens();
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
