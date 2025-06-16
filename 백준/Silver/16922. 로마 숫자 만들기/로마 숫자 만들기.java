import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
    
	static int[] unit = {1, 5, 10, 50};
	static int n;
	static Set<Integer> results;

	public static void main(String[] args) {
		init();
		solve();
		System.out.println(results.size());
	}

	static void solve() {
		// n을 4개의 집합으로 나눈 후, 각 집합에 로마 숫자를 배정한다. (중복 조합)
		// i + j + k + l = n
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n - i; j++) {
				for (int k = 0; k <= n - i - j; k++) {
					int l = n - i - j - k;

					if (l < 0) {
						continue;
					}
                    
					int result = (unit[0] * i) + (unit[1] * j) + (unit[2] * k) + (unit[3] * l);
					results.add(result);
				}
			}
		}
	}

	static void init() {
		n = scan.nextInt();
		results = new HashSet<>();
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
