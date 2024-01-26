import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static int n, k;
	static int[][] board;
	static boolean[][] visited;

	static int[] parent; // parent[i] := i번째 칸이 어떤 문명에 해당되는가
	static Point[] civilizations;

	static Deque<Point> currentQ; // 가장 최근에 문명이 전해진 곳의 좌표를 저장

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

	public static int solve() {
		init();

		int count = 0;

		while (!isAllCivilizationMerged()) {
			// 현재 큐에서 4방향 탐색하며, 탐색한 곳마다 합친다.
			Deque<Point> nextQ = new ArrayDeque<>();

			// 최근에 들어온 문명에서 부터 union
			while (!currentQ.isEmpty()) {
				Point current = currentQ.poll();
				int cx = current.x;
				int cy = current.y;

				for (int dir = 0; dir < 4; dir++) {
					int nx = cx + DX[dir];
					int ny = cy + DY[dir];

					if (!isValidRange(nx, ny) || visited[nx][ny]) {
						continue;
					}

					union(calcIndex(cx, cy), calcIndex(nx, ny));
					visited[nx][ny] = true;
					nextQ.offer(new Point(nx, ny));
					mergeAdjacentCivilization(nx, ny);
				}
			}

			currentQ = nextQ;
			// printSimulation();
			count++;
		}

		return count;
	}

	public static void printSimulation(){
		System.out.println("-------------");
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++){
				int civil = find(calcIndex(i, j));
				if (!visited[i][j]) civil = -1;

				sb.append(civil).append(" ");
			}

			sb.append("\n");
		}
		System.out.println(sb);
	}

	// 만약 두 인접하는 지역에 다른 문명이 전파되었거나,
	// 한 지역에 둘 이상의 다른 문명이 전파된다면 이 문명들은 결합된다.
	public static void mergeAdjacentCivilization(int x, int y) {
		for (int dir = 0; dir < 4; dir++) {
			int nx = x + DX[dir];
			int ny = y + DY[dir];

			// 방문이 되었다 = 문명에 편입되었다 를 의미하므로
			if (!isValidRange(nx, ny) || !visited[nx][ny]) {
				continue;
			}

			union(calcIndex(x, y), calcIndex(nx, ny));
		}
	}

	public static boolean isAllCivilizationMerged() {
		Set<Integer> parentSet = new HashSet<>();

		for (Point civilization : civilizations) {
			parentSet.add(find(parent[calcIndex(civilization.x, civilization.y)]));
		}

		return parentSet.size() == 1;
	}

	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return false;
		}

		parent[b] = a;
		return true;
	}

	public static int find(int a) {
		if (parent[a] == a) {
			return a;
		}

		int root = find(parent[a]);
		parent[a] = root;
		return root;
	}

	public static boolean isValidRange(int x, int y) {
		return 1 <= x && x <= n && 1 <= y && y <= n;
	}

	public static int calcIndex(int x, int y) {
		return (n * (x - 1)) + (y - 1);
	}

	public static void init(){
		// 문명의 최초 발상지 처리
		for (Point civilization: civilizations) {
			// 만약 문명 최초 발상지끼리 인접해 있다면, 이들은 처음부터 하나로 결합된다.
			int cx = civilization.x;
			int cy = civilization.y;

			for (int dir = 0; dir < 4; dir++){
				int nx = cx + DX[dir];
				int ny = cy + DY[dir];

				if (!isValidRange(nx, ny) || !visited[nx][ny]) {
					continue;
				}

				union(calcIndex(cx, cy), calcIndex(nx, ny));
			}
		}
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		n = line.nextInt();
		k = line.nextInt();
		parent = new int[n * n];

		for (int i = 0; i < n * n; i++) {
			parent[i] = i;
		}

		board = new int[n + 1][n + 1];
		visited = new boolean[n + 1][n + 1];

		civilizations = new Point[k];
		currentQ = new ArrayDeque<>();

		for (int i = 0; i < k; i++) {
			line.setNewLine(br.readLine());
			int x = line.nextInt();
			int y = line.nextInt();
			civilizations[i] = new Point(x, y);
			currentQ.offer(civilizations[i]);
			visited[x][y] = true;
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

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}

	}
}
