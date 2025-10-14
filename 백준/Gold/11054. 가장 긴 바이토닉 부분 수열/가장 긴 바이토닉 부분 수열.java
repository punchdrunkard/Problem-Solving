import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n;

	// dpIncrease[i] := a[i]를 마지막으로 하는 가장 긴 *증가하는* 부분 수열의 길이
	// dpDecrease[i] := a[i]를 시작으로 하는 가장 긴 *감소하는* 부분 수열의 길이
	static int[] a, dpIncrease, dpDecrease;

	public static void main(String[] args) {
		init();

		solveDpIncrease();
		solveDpDecrease();

		int answer = -1;
		for (int i = 0; i < n; i++) {
			answer = Math.max(answer, dpIncrease[i] + dpDecrease[i] - 1);
		}

		System.out.println(answer);
	}

	static void solveDpIncrease() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (a[i] > a[j]) {
					dpIncrease[i] = Math.max(dpIncrease[j], dpIncrease[i]);

				}
			}
			// 자기 자신 포함
			dpIncrease[i]++;
		}
	}

	static void solveDpDecrease() {
		dpIncrease[0] = 1;

		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				if (a[i] > a[j]) {
					dpDecrease[i] = Math.max(dpDecrease[j], dpDecrease[i]);
				}
			}
			// 자기 자신 포함
			dpDecrease[i]++;
		}
	}

	static void init() {
		n = scan.nextInt();
		a = new int[n];
		dpIncrease = new int[n];
		dpDecrease = new int[n];

		for (int i = 0; i < n; i++) {
			a[i] = scan.nextInt();
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
