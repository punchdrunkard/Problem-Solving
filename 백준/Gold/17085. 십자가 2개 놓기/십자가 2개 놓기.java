import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int n, m;
	static char[][] map;

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		int answer = -1;

		// 보드의 두 점(x1, y1), (x2, y2) 을 선택하는 모든 경우의 수에서
		for (int x1 = 0; x1 < n; x1++) {
			for (int x2 = 0; x2 < n; x2++) {
				for (int y1 = 0; y1 < m; y1++) {
					for (int y2 = 0; y2 < m; y2++) {
						if (map[x1][y1] == '.' || map[x2][y2] == '.') {
							continue;
						}
						
						if (x1 == x2 && y1 == y2) {
							continue;
						}

						// 첫 번째 십자가
						for (int sz1 = 0; sz1 <= 7; sz1++) {
							boolean[][] visited = new boolean[n][m];
							if (canPutCross(x1, y1, sz1, visited)) {
								int area1 = 1 + 4 * sz1;

								// 두 번째 십자가
								for (int sz2 = 0; sz2 <= 7; sz2++) {
									if (canPutCross(x2, y2, sz2, visited)) {
										int area2 = 1 + 4 * sz2;
										answer = Math.max(answer, area1 * area2);
										// visited 를 초기화 시켜야 한다.
										for (int s = 0; s <= sz2; s++) {
											for (int d = 0; d < 4; d++) {
												int nx = x2 + s * DX[d];
												int ny = y2 + s * DY[d];
												visited[nx][ny] = false;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}

		System.out.println(answer);
	}

	static boolean canPutCross(int x, int y, int size, boolean[][] visited) {
		visited[x][y] = true;

		// 십자가 (x, y) 를 중심으로 방향 백터만큼 size 로 뻗어 나감
		for (int l = 1; l <= size; l++) {
			for (int dir = 0; dir < 4; dir++) {
				int nx = x + l * DX[dir];
				int ny = y + l * DY[dir];

				if (isOutOfRange(nx, ny) || visited[nx][ny] || map[nx][ny] != '#') {
					return false;
				}

				visited[nx][ny] = true;
			}
		}

		return true;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		map = new char[n][m];
		for (int i = 0; i < n; i++) {
			map[i] = scan.next().toCharArray();
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
