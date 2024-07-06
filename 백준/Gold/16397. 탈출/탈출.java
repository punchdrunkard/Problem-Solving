import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static final String FAIL = "ANG";
	static final int LIMIT = 99999;

	static int n, t, g;
	static Queue<Integer> q = new LinkedList<>();

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		// 초기 상태 정의
		q.offer(n);

		// dist[n] := n 이라는 수에 얼만큼 버튼을 눌렀나?
		int[] dist = new int[100000];
		Arrays.fill(dist, -1); // 초기화
		dist[n] = 0;

		// bfs
		while (!q.isEmpty()) {
			int current = q.poll();
			int count = dist[current];

			// 정답을 찾은 경우
			if (current == g) { // 탈출
				System.out.println(dist[current]);
				return;
			}

			if (count + 1 > t) {
				continue;
			}

			// 버튼 a를 누르는 경우
			int resA = current + 1;
			if (resA <= LIMIT && dist[resA] == -1) {
				dist[resA] = count + 1;
				q.offer(resA);
			}

			// 버튼 b를 누르는 경우
			int resBTemp = current * 2;
			if (resBTemp <= LIMIT) {
				int resB = changeNumber(resBTemp);
				if (resB <= LIMIT && dist[resB] == -1) {
					dist[resB] = count + 1;
					q.offer(resB);
				}
			}
		}

		System.out.println(FAIL);
	}

	static int changeNumber(int number) {
		int digit = (int)Math.log10(number); // 자릿수
		int highestDigit = (int)(number / Math.pow(10, digit));
		int decreased = highestDigit - 1;
		int remainingPart = (int)(number % Math.pow(10, digit));

		int modifiedNumber = (int)(decreased * Math.pow(10, digit) + remainingPart);
		return modifiedNumber;
	}

	static void init() {
		n = scan.nextInt();
		t = scan.nextInt();
		g = scan.nextInt();
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int nextInt() {
			return Integer.parseInt(next());
		}

		String next() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
	}
}
