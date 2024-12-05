import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, T;

	static int[] px, py;
	static HashMap<Integer, List<Pair>> yAdj;

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(0, 0));
		boolean[] visited = new boolean[n];

		int distance = 0;

		while (!q.isEmpty()) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				Pair current = q.poll();
				int cx = current.x;
				int cy = current.y;

				if (cy == T) {
					return distance;
				}

				for (int dy = -2; dy <= 2; dy++) {
					int ny = cy + dy;

					if (isOutOfRange(ny)) {
						continue;
					}

					if (!yAdj.containsKey(ny)) {
						continue;
					}

					for (Pair node : yAdj.get(ny)) {
						int x = node.x;
						int idx = node.y;

						if (visited[idx]) {
							continue;
						}

						if (canGo(cx, x)) {
							q.offer(new Pair(px[idx], py[idx]));
							visited[idx] = true;
						}
					}
				}
			}

			distance++;
		}

		return -1;
	}

	private static boolean canGo(int cx, int x) {
		return Math.abs(cx - x) <= 2;
	}

	private static boolean isOutOfRange(int ny) {
		return ny < 0 || ny > T;
	}

	static void init() {
		n = scan.nextInt();
		px = new int[n];
		py = new int[n];

		yAdj = new HashMap<>();

		T = scan.nextInt();

		for (int i = 0; i < n; i++) {
			px[i] = scan.nextInt();
			py[i] = scan.nextInt();

			yAdj.putIfAbsent(py[i], new ArrayList<>());
			yAdj.get(py[i]).add(new Pair(px[i], i));
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
