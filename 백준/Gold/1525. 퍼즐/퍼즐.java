import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static final String TARGET = "123456780";

	static int[][] board = new int[3][3];
	static String initState;
	static int sx, sy;

	// indexToPoint[i] := index i의 좌표 위치
	static Pair[] indexToPoint = new Pair[9];
	static Queue<State> q = new LinkedList<>();
	static Map<String, Integer> dist = new HashMap<>();

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		// bfs init
		q.offer(new State(initState, sx, sy));
		dist.put(initState, 0);

		while (!q.isEmpty()) {
			State current = q.poll();
			String state = current.board;

			if (state.equals(TARGET)) {
				return dist.get(state);
			}

			int zx = current.zero.x;
			int zy = current.zero.y;
			int currentDist = dist.get(state);

			// 빈칸을 옮긴다고 생각
			for (int dir = 0; dir < 4; dir++) {
				// 빈칸의 다음 위치 -> 움직일 칸의 위치
				int nx = zx + DX[dir];
				int ny = zy + DY[dir];

				if (isOutOfRange(nx, ny)) {
					continue;
				}

				// 빈칸을 옮겼을 때의 상태
				// -> 빈칸의 위치는 (nx, ny) 가 되고
				// -> 영향 받은 다른 칸의 위치는 (zx, zy) 가 된다.
				StringBuilder nextState = new StringBuilder(state);
				int zeroIndex = findIndex(nx, ny);
				char moved = state.charAt(zeroIndex);
				int movedIndex = findIndex(zx, zy);

				nextState.setCharAt(movedIndex, moved);
				nextState.setCharAt(zeroIndex, '0');
				String nextStateString = nextState.toString();

				if (dist.containsKey(nextStateString)) {
					continue;
				}

				dist.put(nextStateString, currentDist + 1);
				q.offer(new State(nextStateString, nx, ny));
			}
		}

		return dist.getOrDefault(TARGET, -1);
	}

	static int findIndex(int x, int y) {
		return x * 3 + y;
	}

	static boolean isOutOfRange(int x, int y) {
		return x < 0 || x >= 3 || y < 0 || y >= 3;
	}

	static void init() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = scan.nextInt();
				indexToPoint[3 * i + j] = new Pair(i, j);
				sb.append(board[i][j]);

				if (board[i][j] == 0) {
					sx = i;
					sy = j;
				}
			}
		}

		initState = sb.toString();
	}

	static class State {
		String board; // 현재 보드 상태
		Pair zero; // 0의 위치

		State(String board, int x, int y) {
			this.board = board;
			zero = new Pair(x, y);
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
