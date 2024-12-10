import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static int n;

	static Map<String, Integer> wordsToIdx;
	static String[] words, original;

	static class Pair implements Comparable<Pair> {
		int i, j;

		Pair(int i, int j) {
			int smaller = i;
			int bigger = j;

			if (i > j) {
				smaller = j;
				bigger = i;
			}

			this.i = smaller;
			this.j = bigger;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.i == o.i) {
				return Integer.compare(this.j, o.j);
			}

			return Integer.compare(this.i, o.i);
		}

		@Override
		public String toString() {
			return "Pair{" +
				"i=" + i +
				", j=" + j +
				'}';
		}
	}

	public static void main(String[] args) {
		init();
		solve();
	}

	static void solve() {
		int maxCount = -1;

		List<Pair> maxList = new LinkedList<>();

		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < i; j++) {
				if (i == j)
					continue;

				int currentCount = countPrefix(words[i], words[j]);

				if (maxCount < currentCount) {
					maxCount = currentCount;
					maxList = new LinkedList<>();
					maxList.add(new Pair(wordsToIdx.get(words[i]), wordsToIdx.get(words[j])));
					continue;
				}

				if (maxCount == currentCount) {
					maxList.add(new Pair(wordsToIdx.get(words[i]), wordsToIdx.get(words[j])));
					continue;
				}
			}
		}

		Collections.sort(maxList);
		System.out.println(original[maxList.get(0).i] + '\n' + original[maxList.get(0).j]);
	}

	static int countPrefix(String s, String t) {
		int count = 0;

		for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
			if (s.charAt(i) == t.charAt(i)) {
				count++;
			} else {
				break;
			}
		}

		return count;
	}

	static void init() {
		n = scan.nextInt();
		words = new String[n];
		original = new String[n];

		wordsToIdx = new HashMap<>();

		for (int i = 0; i < n; i++) {
			words[i] = scan.next();
			original[i] = words[i];
			wordsToIdx.put(words[i], i);
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
