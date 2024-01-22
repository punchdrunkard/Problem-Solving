import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int n, q;
	static int[][] adj; // 0이라면 연결 하는 길이 없음
	static int INF = 170325 * 301;
	static int[][][] dist;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		input();
		floyd();

		for (int i = 1; i <= q; i++) {
			OneLineParser line = new OneLineParser(br.readLine());
			int c = line.nextInt();
			int s = line.nextInt();
			int e = line.nextInt();
			sb.append(query(c, s, e)).append("\n");
		}

		System.out.print(sb);
	}

	public static int query(int c, int s, int e) {
		return dist[c - 1][s][e] == INF ? -1 : dist[c - 1][s][e];
	}

	public static void floyd() {
		// dist[dew][i][j] := 이슬량 2^dew 이하로 i -> j로 가는 최단 거리
		dist = new int[n + 1][n + 1][n + 1];

		// init
		for (int k = 0; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					if (i == j) {
						dist[k][i][j] = 0;
					} else if (adj[i][j] != 0) {
						dist[k][i][j] = adj[i][j];
					} else {
						dist[k][i][j] = INF;
					}
				}
			}
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= n; j++) {
					// 이전 까지의 경유 지점을 모두 거친 경우 = 2^(k - 1) - 2 (= 2^1 + 2^2 + 2^3 + ... + 2^(k-1))
					// 현재 경유 지점을 거칠 경우 = 2^k
					// 2^k > 2^(k - 1) - 2 은 자명하므로 bottom up 으로 올라가면서 업데이트 가능!
					dist[k][i][j] = Math.min(dist[k - 1][i][j], dist[k - 1][i][k] + dist[k - 1][k][j]);
				}
			}
		}
	}

	public static void initDpTable(){}

	public static void input() throws IOException {
		OneLineParser line = new OneLineParser(br.readLine());
		n = line.nextInt();
		q = line.nextInt();
		adj = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			line.setNewLine(br.readLine());
			for (int j = 1; j <= n; j++) {
				adj[i][j] = line.nextInt();
			}
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
