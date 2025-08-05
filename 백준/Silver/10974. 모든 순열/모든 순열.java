import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/1.inp");
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	static int n;
	static boolean[] visited;
	static List<Integer> current = new ArrayList<>();

	public static void main(String[] args) {
		n = scan.nextInt();
		visited = new boolean[n + 1];
		dfs();
		System.out.println(sb);
	}

	static void dfs() {
		// base case
		if (current.size() == n) {
			for (int number : current) {
				sb.append(number).append(' ');
			}
			sb.append('\n');
			return;
		}

		// recursive case
		for (int i = 1; i <= n; i++) {
			if (visited[i]) {
				continue;
			}
			current.add(i);
			visited[i] = true;
			dfs();
			visited[i] = false;
			current.remove(current.size() - 1);
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
