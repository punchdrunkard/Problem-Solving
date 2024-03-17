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
	static int[][] board;

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

		while (move()) {
			day++;
		}

		return day;
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
		int totalPopulation = board[x][y]; // 연합의 인구수

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

				if (!isValidRange(nx, ny) || visited[nx][ny] || !canOpenBorder(board[cx][cy], board[nx][ny])) {
					continue;
				}

				count++;
				totalPopulation += board[nx][ny];
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

			if (board[cx][cy] != target) {
				moved = true;
				board[cx][cy] = target;
			}
		}

		return moved;
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

		board = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = scan.nextInt();
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

