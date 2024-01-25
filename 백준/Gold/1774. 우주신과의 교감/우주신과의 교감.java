import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m;

	static class Edge {
		int start;
		int end;
		double dist;

		public Edge(int start, int end, double dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
	}

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		static public double getDist(Point p1, Point p2) {
			return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
		}
	}

	static List<Edge> edges;
	static Point[] points;
	static int[] parent;
	static int edgeCount;

	public static void main(String[] args) throws IOException {
		input();
		String answer = String.format("%.2f", solve());
		System.out.println(answer);
	}

	public static double solve(){
		double mstCost = 0;

		edges.sort(Comparator.comparingDouble(edge -> edge.dist));

		for (Edge edge: edges){
			if (union(edge.start, edge.end)) {
				mstCost += edge.dist;
				edgeCount++;
			} else {
				// 합쳐지지 않은 경우 -> 이미 같은 그래프에 속하는 경우
				continue;
			}

			if (edgeCount == n - 1) { // 트리의 성질에 의해
				break;
			}
		}

		return mstCost;
	}

	public static int find(int a) {
		if (parent[a] == a) {
			return a;
		}

		int root = find(parent[a]);
		parent[a] = root;
		return root;
	}

	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return false; // 합치기 작업이 실행되지 않았다. (이미 합쳐져 있음)
		}

		parent[a] = b;
		return true; // 합치기 작업이 실행되었다.
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		n = line.nextInt();
		m = line.nextInt();

		edges = new ArrayList<>();
		points = new Point[n + 1];

		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		// 우주신들의 정점 입력
		for (int i = 1; i <= n; i++) {
			line.setNewLine(br.readLine());
			int x = line.nextInt();
			int y = line.nextInt();
			points[i] = new Point(x, y);
		}

		// 각 정점들에 대한 Edge를 만들어준다.
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) {
					continue;
				}

				edges.add(new Edge(i, j, Point.getDist(points[i], points[j])));
			}
		}

		int count = 0;

		// 이미 연결된 통로
		// !! 중복된 값이 들어갈 수 있음
		for (int i = 0; i < m; i++) {
			line.setNewLine(br.readLine());
			int p1 = line.nextInt();
			int p2 = line.nextInt();

			if (union(p1, p2)) {
				count++;
			}
		}

		edgeCount = count;
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
