import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static final char EMPTY = '.';
	static final char GOLD = '#';
	static final char START = 'S';
	static final char EXIT = 'E';

	static final int[] DX = {0, 0, -1, 1, 0, 0};
	static final int[] DY = {1, -1, 0, 0, 0, 0};
	static final int[] DZ = {0, 0, 0, 0, -1, 1};

	static int sz, sx, sy;
	static int l, r, c;
	static char[][][] building;

	static Queue<State> q = new LinkedList<>();
	static boolean[][][] visited;

	static StringBuilder answerString = new StringBuilder();

	static class State {
		int x, y, z;

		State(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public String toString() {
			return "State [x=" + x + ", y=" + y + ", z=" + z + "]";
		}
	}

	public static void main(String[] args) {
		while (init()) {
			int result = solve();
			makeAnswerString(result);
		}

		System.out.println(answerString);
	}

	static int solve() {

		// bfs init
		q.clear();
		visited = new boolean[l][r][c];
		visited[sz][sx][sy] = true;
		q.offer(new State(sx, sy, sz));
		int dist = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				State current = q.poll();
				int cx = current.x;
				int cy = current.y;
				int cz = current.z;

				if (building[cz][cx][cy] == EXIT) {
					return dist;
				}

				for (int dir = 0; dir < 6; dir++) {
					int nx = cx + DX[dir];
					int ny = cy + DY[dir];
					int nz = cz + DZ[dir];

					if (isOutOfRange(nx, ny, nz) || visited[nz][nx][ny] || building[nz][nx][ny] == GOLD) {
						continue;
					}

					State next = new State(nx, ny, nz);
					q.offer(next);
					visited[nz][nx][ny] = true;
				}
			}

			dist++;
		}

		return -1;
	}

	static boolean isOutOfRange(int x, int y, int z) {
		return x < 0 || x >= r || y < 0 || y >= c || z < 0 || z >= l;
	}

	static void makeAnswerString(int answer) {
		if (answer == -1) {
			answerString.append("Trapped!\n");
			return;
		}

		answerString.append("Escaped in " + answer + " minute(s).\n");
	}

	static boolean init() {
		l = scan.nextInt(); // 층 수
		r = scan.nextInt(); // 행의 갯수
		c = scan.nextInt(); // 열의 갯수

		if (isEndOfInput()) {
			return false;
		}

		building = new char[l][r][c];

		for (int z = 0; z < l; z++) {
			for (int x = 0; x < r; x++) {
				building[z][x] = scan.next().toCharArray();

				for (int y = 0; y < c; y++) {
					if (building[z][x][y] == START) {
						sz = z;
						sx = x;
						sy = y;
					}
				}

			}
		}

		return true;
	}

	private static boolean isEndOfInput() {
		return l == 0 && r == 0 && c == 0;
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			try {
				while (st == null || !st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return st.nextToken();
		}

	}
}
