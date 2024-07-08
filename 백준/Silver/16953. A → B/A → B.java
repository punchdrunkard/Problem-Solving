import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	
	static long a, b;

	static Queue<Long> q = new LinkedList<>();
	static Map<Long, Long> dist = new HashMap<>();

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		dist.put(a, 0L);
		q.offer(a);

		while (!q.isEmpty()) {
			long current = q.poll();
			long currentDist = dist.get(current);

			if (current == b) {
				break;
			}

			// 연산은 수를 계속 커지게 하는 방향으로 적용되므로, b를 벗어나면 더 이상 답이 될 수 없음
			if (current > b) {
				continue;
			}

			long[] results = new long[] {current * 2, (current * 10) + 1};
			for (var result : results) {
				if (dist.containsKey(result)) {
					continue;
				}

				dist.put(result, currentDist + 1);
				q.offer(result);
			}
		}

		System.out.println(dist.containsKey(b) ? dist.get(b) + 1 : -1);
	}

	static void init() {
		a = scan.nextInt();
		b = scan.nextInt();
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
	}
}
