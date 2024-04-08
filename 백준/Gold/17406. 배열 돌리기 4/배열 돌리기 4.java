import java.io.*;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();
	static int n, m, k;
	static int[][] a, backup;
	static List<Integer>[] rotations; // 회전 연산
	static boolean[] visited;

	static List<List<Integer>> steps = new ArrayList<>();

	public static void main(String[] args) {
		init();
		dfs(0, new ArrayList<>());
		System.out.println(solve());
	}

	static int solve() {
		int min = Integer.MAX_VALUE;

		for (List<Integer> step : steps) {
			for (int idx : step) { // 순서대로 연산한다.
				List<Integer> rotation = rotations[idx];
				rotate(rotation.get(0), rotation.get(1), rotation.get(2));
			}

			// 배열의 값을 구한다.
			int value = getArrValue();
			min = Math.min(min, value);

			restore();
		}

		return min;
	}

	static int getArrValue() {
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			int rowSum = 0;
			for (int j = 0; j < m; j++) {
				rowSum += a[i][j];
			}

			min = Math.min(min, rowSum);
		}

		return min;
	}

	static void print2D() {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < n; i++){
			for (int j = 0;j < m;j++){
				sb.append(a[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void rotate(int r, int c, int s) {
		int x1 = r - s;
		int y1 = c - s;
		int x2 = r + s;
		int y2 = c + s;

		while (x1 != x2 || y1 != y2) {
			rotate(x1, y1, x2, y2);
			x1++;
			y1++;
			x2--;
			y2--;
		}
	}

	static void rotate(int x1, int y1, int x2, int y2) {
		int temp = a[x1][y1];

		for (int i = x1; i < x2; i++) {
			a[i][y1] = a[i + 1][y1];
		}

		for (int j = y1; j < y2; j++) {
			a[x2][j] = a[x2][j + 1];
		}

		for (int i = x2; i > x1; i--) {
			a[i][y2] = a[i - 1][y2];
		}

		for (int j = y2; j > y1; j--) {
			a[x1][j] = a[x1][j - 1];
		}

		a[x1][y1 + 1] = temp;
	}

	// 배열을 회전하는 순서를 만든다.
	static void dfs(int count, List<Integer> current) {
		if (count == k) {
			steps.add(new ArrayList<>(current));
			return;
		}

		for (int i = 0; i < k; i++) {
			if (visited[i]) {
				continue;
			}

			current.add(i);
			visited[i] = true;
			dfs(count + 1, current);

			visited[i] = false;
			current.remove(current.size() - 1);
		}
	}

	static void restore() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = backup[i][j];
			}
		}
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		k = scan.nextInt();

		a = new int[n][m];
		backup = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = scan.nextInt();
				backup[i][j] = a[i][j];
			}
		}

		rotations = new ArrayList[k];
		visited = new boolean[k];

		for (int i = 0; i < k; i++) {
			int r = scan.nextInt() - 1;
			int c = scan.nextInt() - 1;
			int s = scan.nextInt();

			rotations[i] = new ArrayList<>(Arrays.asList(r, c, s));
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
