import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	static int n;
	static Deque<Integer> q;

	public static void main(String[] args) {
		input();
		System.out.println(solve());
	}

	static String solve() {
		int currentOrder = 1;
		Deque<Integer> stk = new ArrayDeque<>();

		while (!q.isEmpty()) {
			int current = q.poll();

			if (current == currentOrder) {
				currentOrder++;
			} else {
				// 현재 스택에 있는 사람
				if (!stk.isEmpty()) {
					if (stk.getLast() == currentOrder) {
						currentOrder++;
						stk.pollLast();
						q.offerFirst(current); // 원래 상태로 복구
					} else {
						stk.offerLast(current);
					}
				} else {
					stk.offerLast(current);
				}
			}
		}

		while (!stk.isEmpty()) {
			int current = stk.pollLast();

			if (current == currentOrder) {
				currentOrder++;
			} else {
				return "Sad";
			}
		}

		return "Nice";
	}

	static void input() {
		n = scan.nextInt();
		q = new LinkedList<>();

		for (int i = 1; i <= n; i++) {
			q.offer(scan.nextInt());
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
