import java.util.*;
import java.io.*;

public class Main {
	static FastReader scan = new FastReader();
	static int n;
	static int[] a;
	static Queue<Integer> q = new LinkedList<>();
	static boolean[] visited;

	public static void main(String[] args) {
		init();
		System.out.println(bfs());
	}

	static int bfs() {
		visited[0] = true;
		int[] dist = new int[n];
		Arrays.fill(dist, -1);
		dist[0] = 0;
		q.offer(0);

		while (!q.isEmpty()) {
			int current = q.poll();

			if (current == n - 1) {
				return dist[current];
			}

			for (int i = 1; i <= a[current]; i++) {
				int next = current + i;
				if (next >= n || visited[next]) {
					continue;
				}

				dist[next] = dist[current] + 1;
				visited[next] = true;
				q.offer(next);
			}
		}

		return dist[n - 1];
	}

	static void init() {
		n = scan.nextInt();
		a = new int[n];
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
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
