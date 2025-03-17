import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static StringBuilder sb = new StringBuilder();

	static int[] DX = {-1, 1, 0, 0, -1, -1, 1, 1};
	static int[] DY = {0, 0, -1, 1, -1, 1, -1, 1};

	static int n, m, k;

	static char[][] board;
	static Map<String, Integer> counter = new HashMap<>();

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		// 나올 수 있는 모든 문자열에 대한 카운터를 센다.
		// (x, y)에서 출발해서 모든 방향으로 가보기
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				dfs(x, y, Character.toString(board[x][y]));
			}
		}

		for (int i = 0; i < k; i++) {
			String target = scan.next();
			sb.append(counter.getOrDefault(target, 0)).append('\n');
		}
		System.out.println(sb);
	}

	// (x, y) 지점,
	static void dfs(int x, int y, String curr) {

		counter.put(curr, counter.getOrDefault(curr, 0) + 1);

		// base case : 문자열 길이는 최대 5까지 가능
		if (curr.length() == 5) {
			return;
		}

		// 탐색
		for (int dir = 0; dir < 8; dir++) {
			int nx = (x + DX[dir]) % n;
			int ny = (y + DY[dir]) % m;

			if (nx < 0) {
				nx += n;
			}

			if (ny < 0) {
				ny += m;
			}

			dfs(nx, ny, curr + board[nx][ny]);
		}

	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		k = scan.nextInt();

		board = new char[n][m];
		for (int i = 0; i < n; i++) {
			board[i] = scan.next().toCharArray();
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		String next() {
			while (st == null || !st.hasMoreTokens()) {
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
