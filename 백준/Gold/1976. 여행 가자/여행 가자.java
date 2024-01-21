import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static int[][] adj;
	static int[] schedule;
	static final String YES = "YES";
	static final String NO = "NO";

	static int[] parents;

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	public static String solve() {
		// 1. 간선의 정보를 이용해서 union-find!
		initParent();

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (adj[i][j] == 1) {
					union(i, j);
				}
			}
		}

		for (int i = 1; i < m; i++) {
			if (find(schedule[i - 1]) != find(schedule[i]))
				return NO;
		}

		return YES;
	}

	public static int find(int a) {
		if (parents[a] == a)
			return a;

		// 경로 압축
		parents[a] = find(parents[a]);
		return parents[a];
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b)
			return;

		parents[a] = b;
	}

	public static void initParent() {
		parents = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		adj = new int[n + 1][n + 1];
		schedule = new int[m];

		for (int i = 1; i <= n; i++) {
			OneLineParser line = new OneLineParser(br.readLine());
			for (int j = 1; j <= n; j++) {
				adj[i][j] = line.nextInt();
			}
		}

		schedule = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
