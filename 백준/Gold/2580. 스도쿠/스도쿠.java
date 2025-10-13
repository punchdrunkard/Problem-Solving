import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
	static FastReader scan = new FastReader();

	static final int EMPTY = 0;
	static int[][] board = new int[9][9];

	// 탐색해야하는 좌표들
	static List<Pair> targets = new ArrayList<>();

	// area[x][y] := (x, y)가 몇 번째 영역에 속하는지 저장
	static int[][] area = new int[9][9];

	// areaStandard[i] := i번 area의 기준점 저장 (좌상단좌표)
	static Pair[] areaStandard = new Pair[9];

	public static void main(String[] args) {
		init();
		dfs(0);
	}

	static boolean dfs(int idx) {
		// base case
		if (idx >= targets.size()) {
			print2D(board);
			return true;
		}

		Pair current = targets.get(idx);
		int cx = current.x;
		int cy = current.y;

		// recursive case
		// 해당 좌표에 1 ~ 9까지의 값을 채운다
		for (int val = 1; val <= 9; val++) {

			// 가능한지 확인하기 -> 현재 수가 존재하는가?
			if (checkRow(cx, val) && checkCol(cy, val) && checkBox(cx, cy, val)) {
				// 채우기
				board[cx][cy] = val;
				// 진행하기
				if (dfs(idx + 1)) {
					return true;
				}
			}

			// 백트래킹
			board[cx][cy] = 0;
		}

		return false;
	}

	static void print2D(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static boolean checkBox(int row, int col, int value) {
		int areaIdx = area[row][col];
		Pair standard = areaStandard[areaIdx];
		int sx = standard.x;
		int sy = standard.y;

		for (int dx = 0; dx < 3; dx++) {
			for (int dy = 0; dy < 3; dy++) {
				if (board[sx + dx][sy + dy] == value) {
					return false;
				}
			}
		}

		return true;
	}

	static boolean checkRow(int row, int value) {
		for (int y = 0; y < 9; y++) {
			if (board[row][y] == value) {
				return false;
			}
		}

		return true;
	}

	static boolean checkCol(int col, int value) {
		for (int x = 0; x < 9; x++) {
			if (board[x][col] == value) {
				return false;
			}
		}

		return true;
	}

	static void init() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = scan.nextInt();
				if (board[i][j] == 0) {
					targets.add(new Pair(i, j));
				}
			}
		}

		int cnt = 0;

		// 좌상단 좌표 기준
		for (int x = 0; x < 9; x += 3) {
			for (int y = 0; y < 9; y += 3) {

				areaStandard[cnt] = new Pair(x, y);

				for (int dx = 0; dx < 3; dx++) {
					for (int dy = 0; dy < 3; dy++) {
						area[x + dx][y + dy] = cnt;
					}
				}

				cnt++;
			}
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
