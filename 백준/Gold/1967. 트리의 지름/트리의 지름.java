import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();

	static int n;
	static List<Node>[] adj;

	static class Node {
		int node;
		int cost;

		Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
	}

	public static void main(String[] args) {
		init();

		Node startOfDiameter = findFurtherMostNode(1, -1, 0);
		Node endOfDiameter = findFurtherMostNode(startOfDiameter.node, -1, 0);

		System.out.println(endOfDiameter.cost);
	}

	// 현재 노드에서 가장 먼 노드와, 그 떄의 cost 를 구한다.
	static Node findFurtherMostNode(int st, int par, int cost){
		Node res = new Node(st, cost);

		for (Node child: adj[st]) {
			if (child.node == par) {
				continue;
			}

			Node next = findFurtherMostNode(child.node, st, child.cost + cost);

			if (res.cost < next.cost) {
				res = next;
			}
		}

		return res;
	}

	static void init() {
		n = scan.nextInt();
		adj = new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			adj[i] = new ArrayList<>();
		}

		for (int i = 0; i < n - 1; i++) {
			int par = scan.nextInt();
			int child = scan.nextInt();
			int cost = scan.nextInt();

			adj[par].add(new Node(child, cost));
			adj[child].add(new Node(par, cost));
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
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

