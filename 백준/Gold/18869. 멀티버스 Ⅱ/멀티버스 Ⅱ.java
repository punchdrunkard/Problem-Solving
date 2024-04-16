import java.util.*;
import java.io.*;

public class Main {
	static FastReader scan = new FastReader();
	static int m, n;
	static int[][] planets, sorted;

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		// 크기 순 인덱스가 필요함 -> 좌표 압축

		for (int i = 0; i < m; i++) {
			sorted[i] = Arrays.stream(planets[i]).sorted().distinct().toArray();
		}

		// 압축 결과를 얻기 위해 이분 탐색 -> lowerbound
		int[][] compressed = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				compressed[i][j] = lowerBound(planets[i][j], sorted[i]);
			}
		}

		int count = 0;

		// 균등한 우주의 순서쌍을 찾기
		for (int i = 0; i < m - 1; i++) {
			for (int j = i + 1; j < m; j++) {
				boolean flag = true;

				for (int k = 0; k < n; k++) {
					if (compressed[i][k] != compressed[j][k]) {
						flag = false;
						break;
					}
				}

				if (flag) count++;
			}
		}

		System.out.println(count);
	}

	static int lowerBound(int value, int[] arr) { // value보다 같거나 작은 최소의 인덱스
		int lo = -1;
		int hi = arr.length;

		while (lo + 1 < hi) {
			int mid = (lo + hi) / 2;

			if (value <= arr[mid]) { // 만족한다면
				hi = mid;
			} else {
				lo = mid;
			}
		}

		return hi;
	}

	static void init() {
		m = scan.nextInt(); // 우주의 개수
		n = scan.nextInt(); // 행성의 개수

		planets = new int[m][n];
		sorted = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				planets[i][j] = scan.nextInt();
				sorted[i][j] = planets[i][j];
			}
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
