import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();

	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int n, r, l;
	static State[][] board;

	static class State {
		int population; //인구 수
		int border; // 국경선 상태

		State(int population) {
			this.population = population;
			this.border = 0; // 초기상태 -> 국경선이 연결되어 있지 않음
		}
	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		init();
		System.out.println(simulate());
	}

	static int simulate() {
		int day = 0;

		while (true) {
			open();

			if (!move()) {
				break;
			}

			close();
			day++;
		}

		return day;
	}

	static void close() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j].border = 0;
			}
		}
	}

	static boolean move() {
		boolean[][] visited = new boolean[n][n];
		boolean moved = false;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!visited[i][j]) {
					moved |= bfs(i, j, visited);
				}
			}
		}

		return moved;
	}

	static boolean bfs(int x, int y, boolean[][] visited) {
		List<Integer> unionX = new ArrayList<>();
		List<Integer> unionY = new ArrayList<>();

		boolean moved = false;

		int count = 1; // 연합을 이루고 있는 칸의 갯수
		int totalPopulation = board[x][y].population; // 연합의 인구수

		Queue<Point> q = new LinkedList<>();
		q.offer(new Point(x, y));
		visited[x][y] = true;
		unionX.add(x);
		unionY.add(y);

		while (!q.isEmpty()) {
			Point current = q.poll();
			int cx = current.x;
			int cy = current.y;

			for (int dir = 0; dir < 4; dir++) {
				int nx = cx + DX[dir];
				int ny = cy + DY[dir];

				boolean borderOpened = ((board[cx][cy].border) & (1 << dir)) != 0;

				if (!isValidRange(nx, ny) || visited[nx][ny] || !borderOpened) {
					continue;
				}

				count++;
				totalPopulation += board[nx][ny].population;
				visited[nx][ny] = true;
				unionX.add(nx);
				unionY.add(ny);
				q.offer(new Point(nx, ny));
			}
		}

		int target = totalPopulation / count;

		for (int i = 0; i < count; i++) {
			int cx = unionX.get(i);
			int cy = unionY.get(i);

			if (board[cx][cy].population != target) {
				moved = true;
				board[cx][cy].population = target;
			}
		}

		return moved;
	}

	static void open() {
		// 여기서 모든 나라를 다 보니까, 중복 체크안해도 될듯?
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int dir = 0; dir < 4; dir++) {
					int nx = i + DX[dir];
					int ny = j + DY[dir];

					if (!isValidRange(nx, ny) || !canOpenBorder(board[i][j].population, board[nx][ny].population)) {
						continue;
					}

					board[i][j].border |= (1 << dir);
				}
			}
		}
	}

	static void printBoard() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(board[i][j].population).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static boolean canOpenBorder(int p1, int p2) {
		int diff = Math.abs(p1 - p2);
		return l <= diff && diff <= r;
	}

	static boolean isValidRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

	static void init() {
		n = scan.nextInt();
		l = scan.nextInt();
		r = scan.nextInt();

		board = new State[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = new State(scan.nextInt());
			}
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
}

