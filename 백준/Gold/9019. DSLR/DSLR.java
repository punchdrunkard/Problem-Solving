import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int MAX = 10000;

	static final char[] OP_TYPES = {'D', 'S', 'L', 'R'};

	static class State {
		int value;
		String prev;

		State(int value, String prev) {
			this.value = value;
			this.prev = prev;
		}
	}

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());

		for (int i = 0; i < t; i++) {
			OneLineParser line = new OneLineParser(br.readLine());
			int a = line.nextInt();
			int b = line.nextInt();

			sb.append(solve(a, b)).append('\n');
		}

		System.out.print(sb);
	}

	// 가중치 없는 그래프에서 최단거리 -> BFS
	public static String solve(int start, int target) {
		Deque<State> q = new ArrayDeque<>();
		String answer = "";

		boolean[] visited = new boolean[MAX];

		visited[start] = true;
		q.offer(new State(start, ""));

		while (!q.isEmpty()) {
			State current = q.poll();
			int value = current.value;

			if (current.value == target) {
				answer = current.prev;
				break;
			}

			for (char opType : OP_TYPES) {
				int next = calculate(opType, value);

				if (visited[next]) {
					continue;
				}

				visited[next] = true;
				q.offer(new State(next, current.prev + opType));
			}
		}

		return answer;
	}

	public static int calculate(char opType, int value) {
		if (opType == OP_TYPES[0]) {
			return (2 * value) % MAX;
		}

		if (opType == OP_TYPES[1]) {
			return value > 0 ? value - 1 : 9999;
		}

		int d1 = value / 1000;
		int d2 = (value % 1000) / 100;
		int d3 = (value % 100) / 10;
		int d4 = (value % 10);

		if (opType == OP_TYPES[2]) {
			return 1000 * d2 + 100 * d3 + 10 * d4 + d1;
		}

		if (opType == OP_TYPES[3]) {
			return 1000 * d4 + 100 * d1 + 10 * d2 + d3;
		}

		return -1;
	}

	public static boolean isValidRange(int x) {
		return 0 <= x && x < MAX;
	}

	public static class OneLineParser {
		private StringTokenizer st;

		public OneLineParser(String line) {
			st = new StringTokenizer(line);
		}

		public int nextInt() {
			return Integer.parseInt(st.nextToken());
		}

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}

	}
}

