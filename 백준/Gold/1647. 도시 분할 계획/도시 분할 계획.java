import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static List<Edge> edges;
	static int[] parent;

	static class Edge{
		int start;
		int end;
		int cost;

		public Edge (int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(kruskal());
	}

	public static int kruskal(){
		edges.sort(Comparator.comparingInt((e) -> e.cost));

		int mstCost = 0;
		int edgeCount = 0;
		int maxEdgeCost = 0;

		for (int i = 0; i < edges.size(); i++){
			Edge current = edges.get(i);

			if (union(current.start, current.end)) {
				mstCost += current.cost;
				edgeCount++;
			}

			if (edgeCount == n - 1) {
				maxEdgeCost = current.cost;
				break;
			}
		}

		// 마지막으로 연결된 간선은 최대 가중치를 가질 것이므로,
		// 해당 간선을 빼서 마을을 두 개로 분리한다.
		return mstCost - maxEdgeCost;
	}

	public static int find(int a){
		if (parent[a] == a) {
			return a;
		}

		int root = find(parent[a]);
		parent[a] = root;
		return root;
	}

	public static boolean union(int a, int b){
		a = find(a);
		b = find(b);

		if (a == b){
			return false; // 이미 합쳐져 있어서, 작업을 하지 않음
		}

		parent[a] = b;
		return true; // 합치기 작업을 함
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		n = line.nextInt();
		parent = new int[n + 1];
		for (int i = 1; i <= n; i++){
			parent[i] = i;
		}

		m = line.nextInt();
		edges = new ArrayList<>();

		for (int i = 0; i < m; i++){
			line.setNewLine(br.readLine());

			int a = line.nextInt();
			int b = line.nextInt();
			int c = line.nextInt();

			edges.add(new Edge(a, b, c));
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

		public double nextDouble(){
			return Double.parseDouble(st.nextToken());
		}
		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
