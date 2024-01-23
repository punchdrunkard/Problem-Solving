import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, m, q;
	static int[] tanks, parents, old, clean;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		init();

		for (int i = 0; i < q; i++) {
			int k = Integer.parseInt(br.readLine());
			sb.append(solve(k)).append("\n");
		}

		System.out.print(sb);
	}

	public static int solve(int k){
		int root = find(k);
		int answer = old[root] >= clean[root] ? 0 : 1;
		return answer;
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return;
		}

		parents[a] = b;
		old[b] += old[a];
		clean[b] += clean[a];
	}

	public static int find(int a) {
		if (parents[a] == a) {
			return a;
		}

		parents[a] = find(parents[a]);
		return parents[a];
	}

	public static void initParents(){
		for (int i = 1; i <= n; i++){
			parents[i] = i;
		}
	}

	public static void init() throws IOException {
		OneLineParser line = new OneLineParser(br.readLine());
		n = line.nextInt();
		m = line.nextInt();
		q = line.nextInt();

		line.setNewLine(br.readLine());
		tanks = new int[n + 1];

		for (int i = 1; i <= n; i++){
			tanks[i] = line.nextInt();
		}

		parents = new int[n + 1];
		old = new int[n + 1];
		clean = new int[n + 1];

		initParents();

		for (int i = 1; i <= n; i++){
			if (tanks[i] == 0){
				old[i] += 1;
			} else {
				clean[i] += 1;
			}
		}

		for (int i = 0; i < m; i++) {
			line.setNewLine(br.readLine());
			int u = line.nextInt();
			int v = line.nextInt();

			union(u, v);
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

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
