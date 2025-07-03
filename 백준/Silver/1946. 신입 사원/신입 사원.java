import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	static int n;
	static Rank[] ranks;

	public static void main(String[] args) {
		int t = scan.nextInt();
		for (int tc = 0; tc < t; tc++) {
			init();
			sb.append(solve()).append('\n');
		}

		System.out.println(sb);
	}

	static int solve() {
		Arrays.sort(ranks, Comparator.comparingInt(a -> a.dRank));

		int count = 1;
		int minInterviewRank = ranks[0].iRank;

		// 서류 순위로 정렬했기 때문에, i번째 지원자는 항상 i-1 번째 지원자보다 서류 점수가 낮다.
		// 따라서 면접 점수만 비교하면 된다.
		for (int i = 1; i < n; i++) {
			if (minInterviewRank > ranks[i].iRank) {
				count++;
			}

			minInterviewRank = Math.min(minInterviewRank, ranks[i].iRank);
		}

		return count;
	}

	static void init() {
		n = scan.nextInt();
		ranks = new Rank[n];

		for (int i = 0; i < n; i++) {
			ranks[i] = new Rank(scan.nextInt(), scan.nextInt());
		}
	}

	static class Rank {
		int dRank, iRank;

		Rank(int dRank, int iRank) {
			this.dRank = dRank;
			this.iRank = iRank;
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
