import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n; // 별의 갯수
	static Point[] stars;
	static List<Edge> edges;
	static int[] parent;

	static class Point {
		double x;
		double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		static Double getDistance(Point p1, Point p2) {
			return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
		}

		@Override
		public String toString() {
			return "Point{" +
				"x=" + x +
				", y=" + y +
				'}';
		}
	}

	static class Edge {
		int start; // 시작 별 인덱스
		int end;
		double cost;

		public Edge(int start, int end, double cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Edge{" +
				"start=" + start +
				", end=" + end +
				", cost=" + cost +
				'}';
		}
	}

	public static void main(String[] args) throws IOException {
		input();

		String formatted = String.format("%.2f", solve());
		System.out.println(formatted);
	}

	public static double solve(){
		Collections.sort(edges, Comparator.comparingDouble(e -> e.cost));

		int edgeCount = 0;
		double mstCost = 0;

		for (int i = 0; i < edges.size(); i++){
			Edge current = edges.get(i);

			// 이미 합쳐져있는 경우
			if (find(current.start) == find(current.end)) {
				continue;
			}

			union(current.start, current.end);
			mstCost += current.cost;
			edgeCount++;

			if (edgeCount == n - 1) {
				break;
			}
		}

		return mstCost;
	}

	public static void union(int a, int b){
		a = find(a);
		b = find(b);

		if (a == b) return;

		parent[a] = b;
	}

	public static int find(int a){
		if (parent[a] == a) {
			return a;
		}

		int root = find(parent[a]);
		parent[a] = root;
		return root;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		stars = new Point[n];
		edges = new ArrayList();

		parent = new int[n];
		for (int i = 0; i < n; i++){
			parent[i] = i;
		}

		for (int i = 0; i < n; i++){
			OneLineParser line = new OneLineParser(br.readLine());
			double x = line.nextDouble();
			double y = line.nextDouble();

			stars[i] = new Point(x, y);
		}

		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (i == j) continue;
				double dist = Point.getDistance(stars[i], stars[j]);
				edges.add(new Edge(i, j, dist));
			}
		}
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public double nextDouble(){
			return Double.parseDouble(st.nextToken());
		}
	}
}
