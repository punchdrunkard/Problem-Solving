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
		int[] window = new int[d + 1];

		int currentCount = 0;

		// 초기 k개 미리 셋팅
		for (int i = 0; i < k; i++) {
			int sushi = arr[i % arr.length];

			if (window[sushi] == 0) {
				currentCount++;
			}
			window[sushi]++;
		}

		// 슬라이딩 윈도우 시작
		for (int i = 0; i < arr.length; i++) {
			// 쿠폰 초밥 포함 여부에 따라 최대 종류 수 갱신
			if (window[c] == 0) {
				localMax = Math.max(localMax, currentCount + 1);
			} else {
				localMax = Math.max(localMax, currentCount);
			}

			// 윈도우를 오른쪽으로 이동
			int removeSushi = arr[i % arr.length];
			int addSushi = arr[(i + k) % arr.length];

			// 기존 초밥 제거
			if (window[removeSushi] > 0) {
				window[removeSushi]--;
			}

			if (window[removeSushi] == 0) {
				currentCount--;
			}

			// 새로운 초밥 추가
			if (window[addSushi] == 0) {
				currentCount++;
			}

			window[addSushi]++;
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
