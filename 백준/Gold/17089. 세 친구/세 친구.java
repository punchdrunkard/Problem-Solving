import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n, m;
	static List<Integer>[] adj;
	static boolean[][] isFriend;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int min = Integer.MAX_VALUE;

		// 세 친구가 서로 친구라는 점을 이용해서
		// 임의의 정점을 골라서 그 정점과 역녈된 다른 두 정점끼리 확인한다
		for (int a = 0; a < n; a++) {
			// 	해당 정점과 연결된 다른 두 정점을 고름
			for (int j = 0; j < adj[a].size(); j++) {
				int b = adj[a].get(j);
				for (int k = j + 1; k < adj[a].size(); k++) {
					int c = adj[a].get(k);
					if (isFriend[b][c]) {
						min = Math.min(min, adj[a].size() + adj[b].size() + adj[c].size() - 6);
					}
				}
			}
		}

		return min == Integer.MAX_VALUE ? -1 : min;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		adj = new List[n];
		isFriend = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			int a = scan.nextInt() - 1;
			int b = scan.nextInt() - 1;
			adj[a].add(b);
			adj[b].add(a);
			isFriend[a][b] = true;
			isFriend[b][a] = true;
		}
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
