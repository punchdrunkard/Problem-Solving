import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/1.inp");
	static FastReader scan = new FastReader();

	static StringBuilder sb = new StringBuilder();

	static int n;
	static Info[] results;

	public static void main(String[] args) {
		init();
		Arrays.sort(results);

		for (int i = 0; i < 3; i++) {
			sb.append(results[i].id).append(' ');
		}

		System.out.println(sb);
	}

	static void init() {
		n = scan.nextInt();
		results = new Info[n];

		for (int i = 0; i < n; i++) {
			int id = scan.nextInt(); // 등번호
			int lead = scan.nextInt();
			int speed = scan.nextInt();
			int bouldering = scan.nextInt();

			results[i] = new Info(id, lead * speed * bouldering, lead + speed + bouldering);
		}
	}

	static class Info implements Comparable<Info> {
		int id; // 등 번호
		int multipliedScore; // 곱한 점수
		int scoreSum; // 합산 점수

		Info(int id, int multipliedScore, int scoreSum) {
			this.id = id;
			this.multipliedScore = multipliedScore;
			this.scoreSum = scoreSum;
		}

		@Override
		public int compareTo(Info o) {
			if (multipliedScore != o.multipliedScore) {
				return Integer.compare(multipliedScore, o.multipliedScore);
			}

			if (scoreSum != o.scoreSum) {
				return Integer.compare(scoreSum, o.scoreSum);
			}

			return Integer.compare(id, o.id);
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String fileName) {
			try {
				br = new BufferedReader(new FileReader(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		boolean hasNext() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = br.readLine();
					if (line == null) { // EOF에 도달하면 false 반환
						return false;
					}
					st = new StringTokenizer(line);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
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
