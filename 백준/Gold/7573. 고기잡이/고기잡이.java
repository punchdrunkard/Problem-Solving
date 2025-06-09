import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n, l, m;
	static Pair[] fish;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int answer = 0;

		for (int i = 0; i < m; i++) {
			// 그물의 위쪽 경계
			int x1 = fish[i].x;

			for (int j = 0; j < m; j++) {
				// 그물의 왼쪽 경계
				int y1 = fish[j].y;

				// 가능한 모든 그물 크기에 대해
				for (int w = 1; w < l / 2; w++) {
					int count = 0;
					int h = l / 2 - w;
					
					for (int k = 0; k < m; k++) {
						int x = fish[k].x;
						int y = fish[k].y;

						if (x1 <= x && x <= x1 + h && y1 <= y && y <= y1 + w) {
							count++;
						}
					}

					answer = Math.max(answer, count);
				}
			}
		}
		return answer;

	} // end of solve

	static void init() {
		n = scan.nextInt();
		l = scan.nextInt();
		m = scan.nextInt();
		fish = new Pair[m];

		for (int i = 0; i < m; i++) {
			fish[i] = new Pair(scan.nextInt(), scan.nextInt());
		}
	}

	static class Pair {
		int x, y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair{" +
				"x=" + x +
				", y=" + y +
				'}';
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
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
