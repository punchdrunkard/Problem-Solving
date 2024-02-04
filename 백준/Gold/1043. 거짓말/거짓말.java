import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int[] parent, truthKnowers;
	static List<Integer>[] parties;
	static int n, m, k;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int count = 0;

		for (var party: parties){
			for (int i = 1; i < party.size(); i++){
				union(party.get(i - 1), party.get(i));
			}
		}

		// 참가할 수 있는 파티 갯수 세기
		for (var party : parties) {
			boolean canJoinParty = true;

			for (int i = 0; i < party.size(); i++) {
				for (int knower : truthKnowers) {
					if (find(knower) == find(party.get(i))) {
						canJoinParty = false;
						break;
					}
				}
			}

			if (canJoinParty) {
				count++;
			}
		}

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
		k = scan.nextInt(); // 진실을 아는 사람의 수

		parties = new ArrayList[m];
		for (int i = 0; i < m; i++) {
			parties[i] = new ArrayList<>();
		}

		truthKnowers = new int[k];

		for (int i = 0; i < k; i++) {
			truthKnowers[i] = scan.nextInt();
		}

		for (int i = 0; i < m; i++) {
			int partyCount = scan.nextInt();

			for (int j = 0; j < partyCount; j++) {
				parties[i].add(scan.nextInt());
			}
		}

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
