import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int k, m, p; // m은 항상 바다와 만나는 노드
	static List<Integer>[] adj;
	static int[] indegree;

	public static void main(String[] args) {
		int t = scan.nextInt();

		for (int tc = 1; tc <= t; tc++) {
			input();
			sb.append(tc).append(' ').append(solve()).append('\n');
		}

		System.out.print(sb);
	}

	static int solve() {
		// 순서 기록
		int[] strahler = new int[m + 1];

		// 그 노드로 들어오는 강의 순서들을 저장한다.
		List<Integer>[] parents = new ArrayList[m + 1];
		for (int i = 1; i <= m; i++) {
			parents[i] = new ArrayList<>();
		}

		Deque<Integer> q = new LinkedList<>();

		// 강의 근원인 노드 (= indegree 가 0인 노드)의 순서는 1
		for (int node = 1; node <= m; node++) {
			if (indegree[node] == 0) {
				q.offer(node);
				strahler[node] = 1;
			}
		}

		while (!q.isEmpty()) {
			int current = q.poll();

			for (int child : adj[current]) {
				indegree[child]--;
				parents[child].add(strahler[current]);

				if (indegree[child] == 0) {
					int maxStrahler = parents[child].stream().max(Integer::compareTo).orElse(0);
					long countMaxStrahler = parents[child].stream().filter(p -> p == maxStrahler).count();
					strahler[child] = countMaxStrahler >= 2 ? maxStrahler + 1 : maxStrahler;
					q.offer(child);
				}
			}
		}

		return strahler[m];
	}

	static void input() {
		k = scan.nextInt();
		m = scan.nextInt();
		p = scan.nextInt();

		indegree = new int[m + 1];
		adj = new ArrayList[m + 1];
		for (int i = 1; i <= m; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < p; i++) {
			int a = scan.nextInt();
			int b = scan.nextInt();

			adj[a].add(b);
			indegree[b]++;
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
