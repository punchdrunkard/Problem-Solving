import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static long[] b;

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		// 첫 원소가 될 x를 모두 시도하기
		for (int i = 0; i < n; i++) {
			long x = b[i];
			boolean[] visited = new boolean[n];
			visited[i] = true;
			List<Long> seq = new ArrayList<>();
			seq.add(x);

			if (makeSeq(x, seq, visited)) {
				return;
			}
		}
	}

	static boolean makeSeq(long x, List<Long> seq, boolean[] visited) {

		if (seq.size() == n) {
			StringBuilder sb = new StringBuilder();
			for (long num : seq) {
				sb.append(num).append(" ");
			}
			System.out.println(sb);
			return true;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i]) {
				continue;
			}

			// 현재 수를 다음 수열에 넣을 수 있나요?
			if (b[i] == x * 2 || (x % 3 == 0 && b[i] == x / 3)) {
				seq.add(b[i]);
				visited[i] = true;
				if (makeSeq(b[i], seq, visited)) {
					return true;
				}

				visited[i] = false;
				seq.remove(seq.size() - 1);
			}
		}

		return false;
	}

	static void init() {
		n = scan.nextInt();
		b = new long[n];
		for (int i = 0; i < n; i++) {
			b[i] = scan.nextLong();
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

		long nextLong() {
			return Long.parseLong(next());
		}
	}
}
