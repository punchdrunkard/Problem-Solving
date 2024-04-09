import java.util.*;
import java.io.*;

public class Main {
	static FastReader scan = new FastReader();
	static int[] DX = { -1, 1, 0, 0 };
	static int[] DY = { 0, 0, -1, 1 };

	static final char EMPTY = '.';
	static final char R = 'R';
	static final char G = 'G';
	static final char B = 'B';
	static final char P = 'P';
	static final char Y = 'Y';

	static char[][] field;
	static boolean[][] visited = new boolean[12][6];
	static Queue<Pair> q = new LinkedList<>();

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int count = 0;

		while (true) {
			initVisited();
			boolean canBomb = false;

			List<List<Pair>> blocks = new ArrayList<>();

			// bfs 로 연결 성분을 찾는다.
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if (visited[i][j] || field[i][j] == EMPTY) {
						continue;
					}

					List<Pair> block = bfs(i, j, field[i][j]);

					if (block.size() >= 4) {
						blocks.add(block);
						canBomb = true;
					}
				}
			}

			// System.out.println(blocks);

			if (!canBomb) {
				break;
			}
				
//			System.out.println("터뜨리기 전");
//			printField();
			
			// 터뜨린다.
			for (List<Pair> block : blocks) {
				for (Pair b : block) {
					field[b.x][b.y] = EMPTY;
				}
			}
			
//			System.out.println("터뜨린 후");
//			printField();

			// 중력을 적용시킨다.
			gravity();
			
//			System.out.println("중력 적용 후");
//			printField();
//			break;
			count++;
		}

		return count;
	}

	static void printField() {
		StringBuilder sb = new StringBuilder();
		
		
		for (int i = 0; i <12 ;i++) {
			for (int j = 0; j <6; j++) {
				sb.append(field[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
	
	static void gravity() {
		char[][] temp = new char[12][6];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				temp[i][j] = EMPTY;
			}
		}

		for (int c = 0; c < 6; c++) {
			int cursor = 11;

			for (int r = 11; r >= 0; r--) {
				if (field[r][c] != EMPTY) {
					temp[cursor--][c] = field[r][c];
				}
			}
		}
		
		field = temp;
	}

	static List<Pair> bfs(int x, int y, char color) {
		List<Pair> puyos = new ArrayList<>();

		visited[x][y] = true;
		q.offer(new Pair(x, y));
		puyos.add(new Pair(x, y));

		while (!q.isEmpty()) {
			Pair current = q.poll();
			int cx = current.x;
			int cy = current.y;

			for (int dir = 0; dir < 4; dir++) {
				int nx = cx + DX[dir];
				int ny = cy + DY[dir];

				if (isOutOfRange(nx, ny) || visited[nx][ny] || field[nx][ny] != color) {
					continue;
				}

				visited[nx][ny] = true;
				q.offer(new Pair(nx, ny));
				puyos.add(new Pair(nx, ny));
			}
		}

		return puyos;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= 12 || y < 0 || y >= 6;
	}

	static void initVisited() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				visited[i][j] = false;
			}
		}
	}

	static void init() {
		field = new char[12][6];

		for (int i = 0; i < 12; i++) {
			field[i] = scan.next().toCharArray();
		}
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
