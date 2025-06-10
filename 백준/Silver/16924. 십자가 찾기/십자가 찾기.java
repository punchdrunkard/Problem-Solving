import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int n, m;
	static char[][] board;
	static List<Pair> targets; // 십자가로 덮어야 할 대상

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		StringBuilder sb = new StringBuilder();
		List<Triple> crossInfo = new ArrayList<>();

		// target 에 있는 점을 중심으로 하는 점에서 십자가를 만들어서 해당 컴포넌트를 채울 수 있나요?

		// canFill[i] := i번쨰 인덱스가 십자가로 채워질 수 있음
		boolean[] canFill = new boolean[targets.size()];
		boolean[][] fill = new boolean[n][m];

		Map<Pair, Integer> indexMap = createIndexMap();

		// 애초에 정확한 모양으로 채워지려면 '*' 인 칸만 후보가 될 수 있으므로
		for (int i = 0; i < targets.size(); i++) {
			int x = targets.get(i).x;
			int y = targets.get(i).y;

			int crossLen = findCrossLen(x, y);
			if (crossLen > 0) {
				for (int l = 1; l <= crossLen; l++) {
					for (int d = 0; d < 4; d++) {
						int nx = x + l * DX[d];
						int ny = y + l * DY[d];

						fill[nx][ny] = true;
					}
				}
				// 포함된 십자가 부분을 채운다.
				fill[x][y] = true;
				crossInfo.add(new Triple(x + 1, y + 1, crossLen));
			}
		}

		for (Pair target : targets) {
			if (!fill[target.x][target.y]) {
				System.out.println(-1);
				return;
			}
		}

		sb.append(crossInfo.size()).append('\n');
		for (Triple c : crossInfo) {
			sb.append(c.x).append(' ').append(c.y).append(' ').append(c.z).append('\n');
		}
		System.out.println(sb);
	}

	static Map<Pair, Integer> createIndexMap() {
		Map<Pair, Integer> indexMap = new HashMap<>();
		for (int i = 0; i < targets.size(); i++) {
			indexMap.put(targets.get(i), i);
		}
		return indexMap;
	}

	static int findCrossLen(int x, int y) {
		// (x,y) 를 중심으로 한 십자가를 어디까지 뻗을 수 있는가?
		// 길이가 len인 십자가에 대해서
		int len = 1;
		while (len < n) {
			// 네 방향을 확인한다.
			// 만들 수 없다면 바로 break
			boolean canMakeCross = true;

			for (int d = 0; d < 4; d++) {
				int nx = x + len * DX[d];
				int ny = y + len * DY[d];

				if (isOutOfRange(nx, ny) || board[nx][ny] != '*') {
					canMakeCross = false;
					len--;
					break;
				}
			}

			if (!canMakeCross) {
				break;
			}

			// len 증가
			len++;
		}

		return len;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		board = new char[n][m];
		targets = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			board[i] = scan.next().toCharArray();
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '*') {
					targets.add(new Pair(i, j));
				}
			}
		}
	}

	static class Triple {
		int x, y, z;

		Triple(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
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
