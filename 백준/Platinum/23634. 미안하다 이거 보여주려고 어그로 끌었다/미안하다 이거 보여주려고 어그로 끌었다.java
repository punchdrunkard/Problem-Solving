import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static final int FIRE = 0;
	static final int TREE = 1;
	static final int STONE = 2;

	static int n, m;
	static int[][] map;
	static int[] parent;
	static int fireCount;

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getIndex() {
			return m * x + y;
		}
	}

	public static Deque<Point> fireQ = new ArrayDeque<>();
	public static List<Point> fires = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		input();
		solve();
	}

	public static void solve() {
		init();

		int time = 0;
		int totalRootCount = countMergableFire();
		int currentRootCount = countCurrentRootCount();

		while (currentRootCount != totalRootCount) {
			Deque<Point> nextQ = new ArrayDeque<>();
			int prevFireCount = nextQ.size();

			while (!fireQ.isEmpty()) {
				Point current = fireQ.poll();
				int cx = current.x;
				int cy = current.y;

				for (int dir = 0; dir < 4; dir++) {
					int nx = cx + DX[dir];
					int ny = cy + DY[dir];

					if (!isValidRange(nx, ny) || map[nx][ny] != TREE) {
						continue;
					}

					union(getIndex(cx, cy), getIndex(nx, ny));
					map[nx][ny] = FIRE;
					fireCount++;

					mergeFire(nx, ny);
					nextQ.offer(new Point(nx, ny));
				}
			}

			currentRootCount = countCurrentRootCount();


			time++;
			fireQ = nextQ;

			if (currentRootCount == totalRootCount) { // 합칠 수 있는 불이 모두 합쳐진 경우
				// 불이 하나로 합쳐진 경우
				if (totalRootCount == 1) {
					System.out.println(time + " " + (countFire() - prevFireCount));
				} else {
					System.out.println(time + " " + countFire());
				}

				return;
			}
		}

		System.out.println(time + " " + countFire());
	}

	public static int countFire() {
		int count = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == FIRE) {
					count++;
				}
			}
		}

		return count;
	}

	public static int countCurrentRootCount() {
		Set<Integer> roots = new HashSet<>();

		for (Point fire : fires) {
			roots.add(find(fire.getIndex()));
		}

		return roots.size();
	}

	public static int countMergableFire() {
		int count = 0;

		boolean[][] visited = new boolean[n][m];

		for (Point fire : fires) {
			if (visited[fire.x][fire.y]) {
				continue;
			}

			count++;
			Deque<Point> q = new ArrayDeque<>();
			q.offer(fire);
			visited[fire.x][fire.y] = true;

			while (!q.isEmpty()) {
				Point current = q.poll();
				int cx = current.x;
				int cy = current.y;

				for (int dir = 0; dir < 4; dir++) {
					int nx = cx + DX[dir];
					int ny = cy + DY[dir];

					if (!isValidRange(nx, ny) || visited[nx][ny] || map[nx][ny] == STONE) {
						continue;
					}

					visited[nx][ny] = true;
					q.offer(new Point(nx, ny));
				}
			}
		}

		return count;
	}

	public static void mergeFire(int i, int j) {
		for (int dir = 0; dir < 4; dir++) {
			int ni = i + DX[dir];
			int nj = j + DY[dir];

			if (!isValidRange(ni, nj) || map[ni][nj] != FIRE) {
				continue;
			}

			union(getIndex(i, j), getIndex(ni, nj));
		}
	}

	public static void init() {
		// 초기 불들을 다 union 한다.
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == FIRE) {
					fireQ.offer(new Point(i, j));
					fires.add(new Point(i, j));
					mergeFire(i, j);
					fireCount++;
				}
			}
		}
	}

	public static int getIndex(int x, int y) {
		return m * x + y;
	}

	public static boolean isValidRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}

	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return false;
		}

		parent[b] = a;
		return true;
	}

	public static int find(int a) {
		if (parent[a] == a) {
			return a;
		}

		int root = find(parent[a]);
		parent[a] = root;
		return root;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		n = line.nextInt();
		m = line.nextInt();
		map = new int[n][m];

		parent = new int[n * m];
		for (int i = 0; i < n * m; i++) {
			parent[i] = i;
		}

		for (int i = 0; i < n; i++) {
			char[] row = br.readLine().toCharArray();

			for (int j = 0; j < m; j++) {
				map[i][j] = row[j] - '0';
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

		public Long nextLong() {
			return Long.parseLong(st.nextToken());
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
