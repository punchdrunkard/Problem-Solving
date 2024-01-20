import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int n, m; // 장소의 수, 도로의 수
	static int src, dest;
	static List<Edge>[] adj; // 그래프

	static class Edge implements Comparable<Edge> {
		int en;
		int cost;

		public Edge(int en, int cost) {
			this.en = en;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		while (true) {
			OneLineParser lineParser = new OneLineParser(br.readLine());
			n = lineParser.nextInt();
			m = lineParser.nextInt();
			if (n == 0 && m == 0) {
				break;
			}

			input(lineParser);
			sb.append(solve()).append('\n');
		}

		System.out.print(sb);
	}

	public static int solve() {
		// isOptimalPath[i] := edge(i -> j)가 최단 경로에 포함되어 있는 edge인가?
		boolean[][] isOptimalPath = new boolean[n][n];

		// previousNode[i] := 최단 경로를 방문하는 도중, 이전에 방문한 노드들
		List<Integer>[] previousNode = new ArrayList[n];
		for (int i = 0; i < n; i++)
			previousNode[i] = new ArrayList<>();

		int bestOptimal = dijkstra(src, isOptimalPath, previousNode);

		tracePath(previousNode, isOptimalPath);
		int approxOptimal = dijkstra(src, isOptimalPath, previousNode);
		int answer = approxOptimal == Integer.MAX_VALUE ? -1 : approxOptimal;
		return answer;
	}

	// 최단 경로를 역추적
	public static void tracePath(List<Integer>[] previousNode, boolean[][] isOptimalPath) {
		// 도착 점 부터
		Deque<Integer> queue = new ArrayDeque<>();
		queue.offer(dest);

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int node : previousNode[current]) {
				if (isOptimalPath[node][current])
					continue;
				queue.offer(node);
				isOptimalPath[node][current] = true;
			}
		}

	}

	public static int dijkstra(int start, boolean[][] isOptimalPath, List<Integer>[] previousNode) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);

		pq.offer(new Edge(start, 0));
		dist[start] = 0;

		while (!pq.isEmpty()) {
			Edge current = pq.poll();
			if (dist[current.en] < current.cost) {
				continue;
			}

			for (Edge edge : adj[current.en]) {
				// 최단 거리에 포함된 edge 인 경우
				if (isOptimalPath[current.en][edge.en])
					continue;

				int nextCost = current.cost + edge.cost;

				// 거리를 갱신할 수 있다면?
				if (dist[edge.en] > nextCost) {
					previousNode[edge.en].clear(); // 초기화
					dist[edge.en] = nextCost;
					pq.offer(new Edge(edge.en, nextCost));
					previousNode[edge.en].add(current.en);
				} else if (dist[edge.en] == nextCost) {
					previousNode[edge.en].add(current.en);
				}
			}
		}

		return dist[dest];
	}

	public static void input(OneLineParser lineParser) throws IOException {
		adj = new ArrayList[n];
		for (int i = 0; i < n; i++)
			adj[i] = new ArrayList<>();

		lineParser.setNewLine(br.readLine());

		src = lineParser.nextInt();
		dest = lineParser.nextInt();

		for (int i = 0; i < m; i++) {
			lineParser.setNewLine(br.readLine());
			int u = lineParser.nextInt();
			int v = lineParser.nextInt();
			int p = lineParser.nextInt();

			adj[u].add(new Edge(v, p));
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
