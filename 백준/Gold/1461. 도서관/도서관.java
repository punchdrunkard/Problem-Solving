import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static int n, m;
	static List<Integer> pos, neg;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		int answer = 0;
		int max = -1;

		//한 번 출발했을 때 모든 책을 두는게 이득
		for (int i = 0; i < pos.size(); i += m) {
			answer += (2 * pos.get(i));
			max = Math.max(pos.get(i), max);
		}

		for (int i = 0; i < neg.size(); i += m) {
			answer += (2 * neg.get(i));
			max = Math.max(neg.get(i), max);
		}

		return answer - max;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		pos = new ArrayList<>();
		neg = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			int val = scan.nextInt();
			if (val < 0) {
				neg.add(-val);
			} else {
				pos.add(val);
			}
		}

		//먼 곳부터 가야하므로 내림차순 정렬
		pos.sort(Comparator.reverseOrder());
		neg.sort(Comparator.reverseOrder());
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
