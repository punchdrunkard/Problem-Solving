import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int n, m; // 국가의 수, 비행기의 종류

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			input();

			// 모든 정점을 연결 하면서 간선의 갯수를 최소로 해야 한다. -> spanning tree
			// 연결 그래프 에서는 항상 spanning tree 가 존재 한다.
			sb.append(n - 1).append("\n");
		}

		System.out.print(sb);
	}

	public static void input() throws IOException {
		OneLineParser line = new OneLineParser(br.readLine());

		n = line.nextInt();
		m = line.nextInt();

		for (int i = 0; i < m; i++) {
			br.readLine();
		}
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}
	}
}
