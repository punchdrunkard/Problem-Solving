import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int[] a;


	public static void main(String[] args) {
		input();
		solve();
	}

	public static void solve() {
		Deque<Integer> deque = new ArrayDeque<>();
		Deque<Integer> answer = new ArrayDeque<>();

		// 초기 상태
		for (int i = 1; i <= n; i++) {
			deque.offerLast(i);
		}

		for (int i = n - 1; i >= 0; i--) {
			int action = a[i];

			if (action == 1){
				answer.offerFirst(deque.pollFirst());
			} else if (action == 2) {
				int temp = answer.pollFirst();
				answer.offerFirst(deque.pollFirst());
				answer.offerFirst(temp);
			} else {
				answer.offerLast(deque.pollFirst());
			}
		}

		for (Integer i : answer) {
			sb.append(i).append(' ');
		}

		System.out.println(sb);
	}
	public static void input() {
		n = scan.nextInt();
		a = new int[n];

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

		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
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

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
