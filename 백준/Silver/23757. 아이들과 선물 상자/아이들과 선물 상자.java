import java.util.*;
import java.io.*;

public class Main {
	static FastReader scan = new FastReader();
	static int n, m;
	// 최대힙 써야 하므로, 부호 바꾸는 거 주의!
	static PriorityQueue<Integer> pq = new PriorityQueue<>();
	static int[] c;

	public static void main(String[] args) {
		init();
		solve();
	}
	
	static void solve() {
		boolean flag = true;
		
		for (int i = 0; i < m; i++) {
			int current = -pq.poll();
			if (c[i] > current) {
				flag = false;
				break;
			}
			
			pq.offer(-(current - c[i]));
		}
		
		if (flag) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	
	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		c = new int[m];

		for (int i = 0; i < n; i++) {
			pq.offer(-scan.nextInt());
		}

		for (int i = 0; i < m; i++) {
			c[i] = scan.nextInt();
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
				while (st == null || !st.hasMoreElements()) {
					st = new StringTokenizer(br.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return st.nextToken();
		}
	}
}
