import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	static long m, n, l;
	static Pair[] animals;
	static long[] turrets;

	static class Pair implements Comparable<Pair> {
		long x, y;

		Pair(long x, long y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Pair{" +
				"x=" + x +
				", y=" + y +
				'}';
		}

		@Override
		public int compareTo(Pair o) {
			return Long.compare(this.x, o.x);
		}
	}

	public static void main(String[] args) {
		init();
		System.out.println(solve());
	}

	static int solve() {
		// 정렬
		Arrays.sort(turrets);
		Arrays.sort(animals);

		int count = 0;

		for (int i = 0; i < n; i++) {
			// 가장 가까운 사대를 찾는다. -> 두 군데있음..
			int nearestTurretHi = lowerBound(turrets, animals[i].x);
			int nearestTurretLo = lowerBound(turrets, animals[i].x) - 1;

			if (canCatch(animals[i], nearestTurretHi) || canCatch(animals[i], nearestTurretLo)) {
				count++;
			}
		}

		return count;
	}

	static int lowerBound(long[] arr, long value) {
		int lo = -1;
		int hi = arr.length;

		while (lo + 1 < hi) {
			int mid = lo + (hi - lo) / 2;

			if (!(arr[mid] >= value)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return hi;
	}

	static boolean canCatch(Pair animal, int turretIdx) {
		if (turretIdx == -1 || turretIdx == m) {
			return false;
		}

		return calculateDistance(animal, turrets[turretIdx]) <= l;
	}

	static long calculateDistance(Pair p, long x) {
		return Math.abs(x - p.x) + p.y;
	}

	static void init() {
		m = scan.nextLong(); // 사대의 수
		turrets = new long[(int)m];

		n = scan.nextLong(); // 동물의 수
		animals = new Pair[(int)n];

		l = scan.nextLong(); // 사정 거리

		for (int i = 0; i < m; i++) {
			turrets[i] = scan.nextLong();
		}

		for (int i = 0; i < n; i++) {
			animals[i] = new Pair(scan.nextLong(), scan.nextLong());
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
