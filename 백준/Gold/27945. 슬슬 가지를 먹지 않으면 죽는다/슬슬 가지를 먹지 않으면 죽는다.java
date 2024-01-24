import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n, m;
	static List<Edge> edges;
	static int[] parent;

	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int date;

		public Edge(int start, int end, int date) {
			this.start = start;
			this.end = end;
			this.date = date;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.date, o.date);
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	public static int solve() {
		int date = 1; // 현재 날짜
		int edgeCount = 0; // 현재 edge 개수

		Collections.sort(edges);

		for (int i = 0; i < m; i++) { // 각 간선들에 대해
			Edge current = edges.get(i);

			// 이미 트리에 포함되어있는 간선인가?
			if (find(current.start) != find(current.end)) {
				if (current.date != date) { // 다음 날로 진행할 수 없는 경우
					return date;
				}

				union(current.start, current.end);
				edgeCount++;
				date++;
			}

			// 트리가 완성되면 종료
			if (edgeCount == n - 1) {
				break;
			}
		}

		return date;
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return;
		}

		parent[a] = b;
	}

	public static int find(int a) {
		if (parent[a] == a) {
			return a;
		}

		int result = find(parent[a]);
		parent[a] = result;
		return result;
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());

		n = line.nextInt();
		m = line.nextInt();

		parent = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}

		edges = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			line.setNewLine(br.readLine());
			int u = line.nextInt();
			int v = line.nextInt();
			int t = line.nextInt();
			edges.add(new Edge(u, v, t));
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
