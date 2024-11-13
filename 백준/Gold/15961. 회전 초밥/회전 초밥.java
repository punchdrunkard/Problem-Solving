import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n, d, k, c;
	static int[] rail;

	public static void main(String[] args) {
		init();
		int answer = solve(rail);
		System.out.println(answer);
	}

	static int solve(int[] arr) {
		int localMax = 0;
		Map<Integer, Integer> window = new HashMap<>();
		int currentCount = 0;

		// 초기 k개 미리 셋팅
		for (int i = 0; i < k; i++) {
			int sushi = arr[i % arr.length];
			window.put(sushi, window.getOrDefault(sushi, 0) + 1);
			if (window.get(sushi) == 1) currentCount++;
		}

		// 슬라이딩 윈도우 시작
		for (int i = 0; i < arr.length; i++) {
			// 쿠폰 초밥 포함 여부에 따라 최대 종류 수 갱신
			if (!window.containsKey(c)) {
				localMax = Math.max(localMax, currentCount + 1);
			} else {
				localMax = Math.max(localMax, currentCount);
			}

			// 윈도우를 오른쪽으로 이동
			int removeSushi = arr[i % arr.length];
			int addSushi = arr[(i + k) % arr.length];

			// 기존 초밥 제거
			window.put(removeSushi, window.get(removeSushi) - 1);
			if (window.get(removeSushi) == 0) {
				window.remove(removeSushi);
				currentCount--;
			}

			// 새로운 초밥 추가
			window.put(addSushi, window.getOrDefault(addSushi, 0) + 1);
			if (window.get(addSushi) == 1) currentCount++;
		}

		return localMax;
	}

	static void init() {
		n = scan.nextInt();
		d = scan.nextInt();
		k = scan.nextInt();
		c = scan.nextInt();
		rail = new int[n];

		for (int i = 0; i < n; i++) {
			rail[i] = scan.nextInt();
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
