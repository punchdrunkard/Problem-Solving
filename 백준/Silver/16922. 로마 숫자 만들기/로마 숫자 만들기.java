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
		dfs(0, 0, 0);
		System.out.println(results.size());
	}

	/**
	 * 중복 조합 생성
	 *
	 * @param k     현재 선택한 로마 숫자의 인덱스 (0~3)
	 * @param count 현재까지 선택한 문자의 개수
	 * @param sum   현재까지 계산된 합
	 */
	static void dfs(int k, int count, int sum) {
		// Base Case 1: n개의 문자를 모두 선택했을 때
		if (count == n) {
			results.add(sum);
			return;
		}

		// Base Case 2: 모든 로마 숫자(I, V, X, L)를 다 고려했을 때
		if (k == 4) {
			return;
		}

		// Recursive Step: 현재 로마 숫자(unit[k])를 i개 선택하는 경우
		for (int i = 0; i <= n - count; i++) {
			dfs(k + 1, count + i, sum + (unit[k] * i));
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
