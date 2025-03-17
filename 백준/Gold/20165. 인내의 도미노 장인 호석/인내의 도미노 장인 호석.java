import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static StringBuilder sb = new StringBuilder();

	// 동, 서, 남, 북
	static final int[] DX = {0, 0, 1, -1};
	static final int[] DY = {1, -1, 0, 0};

	static final char F = 'F';
	static final char S = 'S';

	static int n, m, r; // 행, 열, 라운드 횟수
	static int[][] board;
	static char[][] result;
	static int score = 0;

	static class State {
		int x, y, h;

		State(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}
	}

	static Queue<State> attackQ;

	public static void main(String[] args) {
		init();

		for (int round = 0; round < r; round++) {
			attack(scan.nextInt() - 1, scan.nextInt() - 1, getDirIndex(scan.next()));
			defense(scan.nextInt() - 1, scan.nextInt() - 1);
		}

		System.out.println(score);
		print2D(result);
	}

	// x행 y열의 도미노를 d 방향으로 민다.
	static void attack(int x, int y, int d) {

		// 이미 넘어진 격자의 도미노를 공격수가 넘어뜨리려 할 때에는 아무 일도 일어나지 않는다.
		if (result[x][y] == F) {
			return;
		}

		// 현재 결과 임시 저장
		char[][] temp = new char[n][m];
		boolean[][] visited = new boolean[n][m];

		copy2D(result, temp);

		attackQ.offer(new State(x, y, board[x][y]));
		temp[x][y] = F;
		visited[x][y] = true;
		score++;

		while (!attackQ.isEmpty()) {
			State current = attackQ.poll();
			int cx = current.x;
			int cy = current.y;
			int ch = current.h;

			// 연쇄적으로 넘어뜨린다.
			for (int i = 1; i < ch; i++) {
				int nx = cx + i * DX[d];
				int ny = cy + i * DY[d];

				if (isOutOfRange(nx, ny) || result[nx][ny] == F || visited[nx][ny]) {
					continue;
				}

				score++;
				temp[nx][ny] = F;
				visited[nx][ny] = true;
				attackQ.offer(new State(nx, ny, board[nx][ny]));
			}
		}

		copy2D(temp, result);
	}

	// x행, y열의 도미노를 다시 세운다.
	static void defense(int x, int y) {
		// 또한 만약 넘어지지 않은 도미노를 수비수가 세우려고 할 때에도 아무 일도 일어나지 않는다.
		if (result[x][y] == S) {
			return;
		}

		result[x][y] = S;
	}

	static void copy2D(char[][] from, char[][] to) {
		for (int i = 0; i < from.length; i++) {
			for (int j = 0; j < from[0].length; j++) {
				to[i][j] = from[i][j];
			}
		}
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= n || y < 0 || y >= m;
	}

	static int getDirIndex(String s) {
		if ("E".equals(s)) {
			return 0;
		}

		if ("W".equals(s)) {
			return 1;
		}

		if ("S".equals(s)) {
			return 2;
		}

		if ("N".equals(s)) {
			return 3;
		}

		return -1;
	}

	static void print2D(char[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (char[] chars : arr) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(chars[j]).append(' ');
			}
			sb.append('\n');

		}
		System.out.println(sb);
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		r = scan.nextInt();
		board = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				board[i][j] = scan.nextInt();
			}
		}
		result = new char[n][m];
		for (int i = 0; i < n; i++) {
			Arrays.fill(result[i], S);
		}
		attackQ = new LinkedList<>();

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
