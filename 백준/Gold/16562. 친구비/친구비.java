import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int n, m, k;
	static final String NO_FRIENDS = "Oh no";
	static int[] parent;
	static int[] friendCost;
	static final int FRIEND_COST_MAX = 10001;

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(solve());
	}

	public static String solve() {
		int costSum = 0;

		int[] groupCost = new int[n + 1];
		Arrays.fill(groupCost, FRIEND_COST_MAX);

		for (int i = 1; i <= n; i++){
			int current = find(i);
			groupCost[current] = Math.min(groupCost[current], friendCost[i]);
		}

		for (int cost: groupCost) {
			if (cost == FRIEND_COST_MAX) continue;
			costSum += cost;
		}

		String answer = costSum <= k ? Integer.toString(costSum) : NO_FRIENDS;
		return answer;
	}

	public static int find(int a) {
		if (parent[a] == a) {
			return a;
		}

		parent[a] = find(parent[a]);
		return parent[a];
	}

	public static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return;
		}

		parent[a] = b;
	}

	public static void initParent(int n) {
		parent = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			parent[i] = i;
		}
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OneLineParser line = new OneLineParser(br.readLine());
		n = line.nextInt();
		m = line.nextInt();
		k = line.nextInt();

		friendCost = new int[n + 1];
		line.setNewLine(br.readLine());
		for (int i = 1; i <= n; i++){
			friendCost[i] = line.nextInt();
		}

		initParent(n);

		for (int i = 0; i < m; i++) {
			line.setNewLine(br.readLine());
			int v = line.nextInt();
			int w = line.nextInt();
			union(v, w);
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

		public void setNewLine(String line) {
			st = new StringTokenizer(line);
		}
	}
}
