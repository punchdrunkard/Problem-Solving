import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();
	static int n, m;
	static Set<String> keywords = new HashSet<>();

	public static void main(String[] args) {
		input();

		for (int i = 0; i < m; i++){
			String post = scan.nextLine();
			String[] postKeywords = post.split(",");
			for (String keyword: postKeywords) {
				keywords.remove(keyword);
			}

			sb.append(keywords.size()).append("\n");
		}

		System.out.print(sb);
	}


	static void input() {
		n = scan.nextInt();
		m = scan.nextInt();

		for (int i = 0; i < n; i++){
			keywords.add(scan.nextLine());
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
