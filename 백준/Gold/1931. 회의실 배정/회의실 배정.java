import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	static int n;
	static State[] meetings;

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	// 일찍 끝나는 회의 우선으로 배치
	static int solve() {

		Arrays.sort(meetings);

		int count = 1;
		int latestEndTime = meetings[0].e;

		for (int i = 1; i < n; i++) {
			if (latestEndTime <= meetings[i].s) {
				count++;
				latestEndTime = meetings[i].e;
			}
		}

		return count;
	}

	static void init() {
		n = scan.nextInt();
		meetings = new State[n];

		for (int i = 0; i < n; i++) {
			meetings[i] = new State(scan.nextInt(), scan.nextInt());
		}
	}

	static class State implements Comparable<State> {
		int s, e; // 시작시간, 끝나는 시간

		State(int s, int e) {
			this.s = s;
			this.e = e;
		}

		@Override
		public int compareTo(State o) {
			if (e == o.e) {
				return Integer.compare(s, o.s);
			}

			return Integer.compare(e, o.e);
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
