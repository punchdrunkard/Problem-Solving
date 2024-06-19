import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static int k;
	static String[] relations;
	static boolean[] used = new boolean[10]; // 0부터 9까지의 숫자

	static String minVal = "99999999999";
	static String maxVal = "00000000000";

	public static void main(String[] args) {
		init();
		solve(new ArrayList<>(), 0);
		printAnswer();
	}

	static void printAnswer() {
		StringBuilder sb = new StringBuilder();
		sb.append(maxVal).append("\n").append(minVal);
		System.out.println(sb);
	}

	// numbers: 현재 수열, idx: 현재 채울 번호
	static void solve(List<Integer> numbers, int idx) {
		if (idx == k + 1) {
			String result = makeValue(numbers);

			if (minVal.compareTo(result) > 0) {
				minVal = result;
			}

			if (maxVal.compareTo(result) < 0) {
				maxVal = result;
			}

			return;
		}

		// numbers 의 idx 번째 인덱스에 수를 넣는다.
		for (int num = 0; num <= 9; num++) {
			if (!used[num]) {
				if (idx != 0) {
					int prev = numbers.get(idx - 1);
					if (!isValid(relations[idx - 1], prev, num)) {
						continue;
					}
				}

				numbers.add(num);
				used[num] = true;
				solve(numbers, idx + 1);
				numbers.remove(idx);
				used[num] = false;
			}
		}
	}

	static String makeValue(List<Integer> nums) {
		StringBuilder sb = new StringBuilder();

		for (int num : nums) {
			sb.append(num);
		}

		return sb.toString();
	}

	static boolean isValid(String rel, int a, int b) {
		if (rel.equals("<")) {
			return a < b;
		} else {
			return a > b;
		}
	}

	static void init() {
		k = scan.nextInt();
		relations = new String[k];

		for (int i = 0; i < k; i++) {
			relations[i] = scan.next();
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
