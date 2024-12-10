import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;
	static String[] words;

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		// todo 가능한 최대 접두사 길이 찾기
		int lo = -1;
		int hi = 101;

		while (lo + 1 < hi) {
			int mid = lo + (hi - lo) / 2;

			// 현재 단어에서 길이가 mid인 prefix 가 존재하는가?
			if (hasDuplicatePrefix(mid, words)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		
		// 현재 prefix가 처음으로 등장한 index를 저장한다.
		Map<String, Integer> prefixToIndex = new HashMap<>();
		String firstString = null;
		String secondString = null;

		int earliestIdx = Integer.MAX_VALUE;

		for (int idx = 0; idx < n; idx++) {
			if (words[idx].length() < lo) {
				continue;
			}

			String currentPrefix = words[idx].substring(0, lo);

			if (prefixToIndex.containsKey(currentPrefix)) {
				int previousIdx = prefixToIndex.get(currentPrefix);

				if (earliestIdx > previousIdx) {
					earliestIdx = previousIdx;
					firstString = words[previousIdx];
					secondString = words[idx];
				}
			}

			prefixToIndex.put(currentPrefix, idx);
		}

		System.out.println(firstString);
		System.out.println(secondString);
	}

	static boolean hasDuplicatePrefix(int mid, String[] words) {
		Set<String> prefixes = new HashSet<>();

		for (int i = 0; i < n; i++) {
			if (words[i].length() < mid) {
				continue;
			}

			String prefix = words[i].substring(0, mid);

			if (prefixes.contains(prefix)) {
				return true;
			}

			prefixes.add(prefix);
		}

		return false;
	}

	static void init() {
		n = scan.nextInt();
		words = new String[n];
		for (int i = 0; i < n; i++) {
			words[i] = scan.next();
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		long nextLong() {
			return Long.parseLong(next());
		}

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
