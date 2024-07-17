import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

	static FastReader scan = new FastReader();
	static int n, m;
	static int[] parent; // uf
	static int[] power; // 병력

	public static void main(String[] args) {
		init();

		for (int i = 0; i < m; i++) {
			int o = scan.nextInt();
			int p = scan.nextInt() - 1;
			int q = scan.nextInt() - 1;

			// 내 동맹의 동맹도 나의 동맹이고, 내 동맹이 적과 전쟁을 시작하면 같이 참전한다.
			p = find(p);
			q = find(q);

			if (p == -1 || q == -1) {
				continue;
			}

			if (o == 1) { // 동맹
				ally(p, q);
			} else { // 전쟁
				war(p, q);
			}
		}

		printAnswer();
	}

	static void ally(int p, int q) {
		if (union(p, q)) { // 동맹이 맺어졌다면
			// 두 나라의 병력이 하나로 합쳐진다.
			int mergedPower = power[p] + power[q];
			power[p] = mergedPower;
			power[q] = mergedPower;
		}
	}

	static void war(int p, int q) {
		// 두 나라의 병력이 같으면 둘 다 멸망
		if (power[p] == power[q]) {
			parent[p] = -1;
			parent[q] = -1;
			power[p] = 0;
			power[q] = 0;
		} else {
			// 병력이 더 많은 나라를 찾아야 한다.
			int win, lose;

			if (power[p] > power[q]) {
				win = p;
				lose = q;
			} else {
				win = q;
				lose = p;
			}

			// 이긴 국가의 속국이 되야 함
			union(lose, win);
			power[win] -= power[lose];
		}
	}

	static void printAnswer() {
		StringBuilder sb = new StringBuilder();

		Set<Integer> nations = new HashSet<>();

		for (int i = 0; i < n; i++) {
			int root = find(i);

			if (root == -1) {
				continue;
			}

			nations.add(root);
		}

		sb.append(nations.size()).append('\n');

		nations.stream()
			.map(nation -> power[nation])
			.sorted()
			.forEach(p -> sb.append(p).append(' '));

		System.out.println(sb);
	}

	// union-find
	static int find(int a) {
		if (parent[a] == -1) {
			return -1;
		}

		if (parent[a] == a) {
			return a;
		}

		// path compression
		parent[a] = find(parent[a]);
		return parent[a];
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a == b) {
			return false;
		}

		parent[a] = b;
		return true;
	}

	static void init() {
		n = scan.nextInt();
		m = scan.nextInt();
		power = new int[n];

		for (int i = 0; i < n; i++) {
			power[i] = scan.nextInt();
		}

		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}

	}

	static class FastReader {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			try {
				while (st == null || !st.hasMoreTokens()) {
					st = new StringTokenizer(br.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return st.nextToken();
		}
	}
}
