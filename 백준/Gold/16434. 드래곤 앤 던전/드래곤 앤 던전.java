import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static int n;
	static long heroInitialATK;
	static int[] t, a, h;

	public static void main(String[] args) {
		init();
		long maxHp = solve();
		System.out.println(maxHp);
	}

	static long solve() {
		long lo = 0;
		long hi = Long.MAX_VALUE;

		while (lo + 1 < hi) {
			long mid = lo + (hi - lo) / 2;

			if (!canClear(mid)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return hi;
	}

	static boolean canClear(long maxHp) {
		long currentHp = maxHp;
		long currentAtk = heroInitialATK;

		for (int roomIdx = 0; roomIdx < n; roomIdx++) {
			if (t[roomIdx] == 1) { // 몬스터
				int monsterHp = h[roomIdx];
				int monsterAtk = a[roomIdx];

				long attackCountForWin = (monsterHp / currentAtk) + (monsterHp % currentAtk == 0 ? 0 : 1);
				currentHp -= (monsterAtk * (attackCountForWin - 1));

				if (currentHp <= 0) {
					return false;
				}

			} else if (t[roomIdx] == 2) { // 포션
				// 회복
				currentAtk += a[roomIdx];
				currentHp = Math.min(maxHp, currentHp + h[roomIdx]);
			}
		}

		return true;
	}

	static void init() {
		n = scan.nextInt();
		t = new int[n];
		a = new int[n];
		h = new int[n];

		heroInitialATK = scan.nextInt();

		for (int i = 0; i < n; i++) {
			t[i] = scan.nextInt();
			a[i] = scan.nextInt();
			h[i] = scan.nextInt();
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
