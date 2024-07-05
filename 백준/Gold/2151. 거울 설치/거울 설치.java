import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};

	static int n;
	static char[][] board;

	static int sx, sy; // 시작점

	static List<Integer> targetX = new ArrayList<>();
	static List<Integer> targetY = new ArrayList<>();

	static class Triple {
		int x, y, dir; // dir := 빛의 진행 방향

		Triple(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	static Queue<Triple> q = new LinkedList<>();

	// count[x][y][d] := (x, y)에서 빛이 d 방향으로 움직일 때, 설치한 거울의 갯수
	static int[][][] count;

	public static void main(String[] args) {
		init();
		bfs();

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			answer = Math.min(count[targetX.get(1)][targetY.get(1)][i], answer);
		}
		
		System.out.println(answer);
	}

	static int[] changeDirection(int dir) {
		if (dir == 0 || dir == 1) {
			return new int[] {2, 3};
		}

		return new int[] {0, 1};
	}

	static void bfs() {
		// 시작점 문에서 가능한 빛의 방향으로 탐사
		for (int dir = 0; dir < 4; dir++) {
			q.add(new Triple(sx, sy, dir));
			// 시작점에 대한 count 배열 초기화
			count[sx][sy][dir] = 0;
		}

		// 탐색
		while (!q.isEmpty()) {
			// 현재 위치
			Triple pos = q.poll();

			// 다음 위치 => 지금 빛 방향으로 진행
			int nx = pos.x + DX[pos.dir];
			int ny = pos.y + DY[pos.dir];

			if (isOutOfRange(nx, ny) || board[nx][ny] == '*') {
				continue;
			}

			// 문에 도달한 경우
			if (board[nx][ny] == '#') {
				count[nx][ny][pos.dir] = Math.min(count[pos.x][pos.y][pos.dir], count[nx][ny][pos.dir]);
			} else if (board[nx][ny] == '!') {
				// 거울 설치 -> 현재 위치에서 dir 이 변함
				int[] nextDirs = changeDirection(pos.dir);

				for (int d : nextDirs) {
					if (count[pos.x][pos.y][pos.dir] + 1 < count[nx][ny][d]) {
						count[nx][ny][d] = count[pos.x][pos.y][pos.dir] + 1;
						q.offer(new Triple(nx, ny, d));
					}
				}

				// 거울 설치 X
				if (count[pos.x][pos.y][pos.dir] < count[nx][ny][pos.dir]) {
					count[nx][ny][pos.dir] = count[pos.x][pos.y][pos.dir];
					q.offer(new Triple(nx, ny, pos.dir));
				}
			} else if (board[nx][ny] == '.') { // 거울 설치 X
				if (count[pos.x][pos.y][pos.dir] < count[nx][ny][pos.dir]) {
					count[nx][ny][pos.dir] = count[pos.x][pos.y][pos.dir];
					q.offer(new Triple(nx, ny, pos.dir));
				}
			}
		}
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= n;
	}

	static void init() {
		n = scan.nextInt();
		board = new char[n][n];

		for (int i = 0; i < n; i++) {
			board[i] = scan.next().toCharArray();

			for (int j = 0; j < n; j++) {
				if (board[i][j] == '#') {
					targetX.add(i);
					targetY.add(j);
				}
			}
		}

		// count 배열 초기화
		count = new int[n][n][4];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < 4; k++) {
					count[i][j][k] = Integer.MAX_VALUE;
				}
			}
		}

		// 탐색 시작점 초기화
		sx = targetX.get(0);
		sy = targetY.get(0);
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
