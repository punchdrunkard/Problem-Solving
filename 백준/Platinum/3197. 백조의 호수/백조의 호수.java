import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] DX = {-1, 1, 0, 0};
	static int[] DY = {0, 0, -1, 1};

	static int r, c;
	static int[] parent;
	static char[][] board;
	static int[] swans;

	static final char WATER = '.';
	static final char ICE = 'X';
	static final char SWAN = 'L';

	static Queue<Point> waterQ = new ArrayDeque<>();

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	// 얼음을 녹일 때, 직전에 녹은 얼음 주변만 확인한다.
	public static int solve() {
		init();

		int day = 0;

		while (find(swans[0]) != find(swans[1])) {
			day++;

			// 현재 존재하는 물들에 대해서, 상하좌우 탐색을 하면서 새롭게 물이 될 것들을 찾는다.
			Queue<Point> nextWaterQ = new ArrayDeque<>();

			while (!waterQ.isEmpty()) {
				Point current = waterQ.poll();

				int cx = current.x;
				int cy = current.y;

				for (int dir = 0; dir < 4; dir++) {
					int nx = cx + DX[dir];
					int ny = cy + DY[dir];

					if (!isValidRange(nx, ny) || board[nx][ny] != ICE) {
						continue;
					}

					// 새로운 물이 생긴 구역 확인
					board[nx][ny] = WATER;
					union(c * cx + cy, c * nx + ny);
					Point newWater = new Point(nx, ny);
					nextWaterQ.offer(newWater);
					mergeAdjacentWater(newWater);
				}
			}

			waterQ = nextWaterQ;
		}

		return day;
	}

	// 해당 구역이 다른 구역과 합쳐지지 않는지 확인
	public static void mergeAdjacentWater(Point newWater) {
		int nx = newWater.x;
		int ny = newWater.y;

		for (int dir = 0; dir < 4; dir++) {
			int nnx = nx + DX[dir];
			int nny = ny + DY[dir];

			if (!isValidRange(nnx, nny) || board[nnx][nny] == ICE) {
				continue;
			}

			union(c * nx + ny, c * nnx + nny);
		}
	}

	public static void init() {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (board[i][j] == ICE) {
					continue;
				}

				// bfs
				for (int dir = 0; dir < 4; dir++) {
					int ni = i + DX[dir];
					int nj = j + DY[dir];

					if (!isValidRange(ni, nj) || board[ni][nj] == ICE) {
						continue;
					}

					union(c * i + j, c * ni + nj);
				}

				waterQ.offer(new Point(i, j));
			}
		}
	}

	public static int find(int a) {
		if (parent[a] == a) {
			return a;
		}

		int root = find(parent[a]);
		parent[a] = root;
		return root;
	}

	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return false;
		}

		parent[a] = b;
		return true;
	}

	public static boolean isValidRange(int x, int y) {
		return 0 <= x && x < r && 0 <= y && y < c;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		r = line.nextInt();
		c = line.nextInt();
		board = new char[r][c];

		parent = new int[r * c];
		for (int i = 0; i < r * c; i++) {
			parent[i] = i;
		}

		swans = new int[2];
		int swanIndex = 0;

		for (int i = 0; i < r; i++) {
			board[i] = br.readLine().toCharArray();

			for (int j = 0; j < c; j++) {
				if (board[i][j] == SWAN) {
					swans[swanIndex] = c * i + j;
					swanIndex++;
				}
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
	}
}
