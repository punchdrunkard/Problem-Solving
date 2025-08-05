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

	public static void main(String[] args) {
		int k;

		// 집합 s (길이 k)의 숫자 중에서 6개를 구하는 모든 경우
		while ((k = scan.nextInt()) != 0) {
			int[] s = new int[k];
			for (int i = 0; i < k; i++) {
				s[i] = scan.nextInt();
			}
			solve(s, k, 0, new ArrayList<>());
			sb.append('\n');
		}

		System.out.println(sb);
	}

	// 집합 s (길이 k)의 숫자 중에서 6개를 구하는 모든 경우
	static void solve(int[] s, int k, int start, List<Integer> current) {
		// base case
		if (current.size() == 6) {
			for (int number : current) {
				sb.append(number).append(' ');
			}
			sb.append('\n');
			return;
		}

		// recursive case
		for (int i = start; i < k; i++) {
			// i번째 원소를 선택
			current.add(s[i]);
			solve(s, k, i + 1, current);
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
