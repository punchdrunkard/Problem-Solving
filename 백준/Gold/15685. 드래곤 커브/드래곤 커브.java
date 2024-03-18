import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();

	static int[] DX = {1, 0, -1, 0};
	static int[] DY = {0, -1, 0, 1};

	static int n;
	static boolean[][] inCurve = new boolean[101][101];

	public static void main(String[] args) {
		init();
		System.out.println(countSquare());
	}

	public static int countSquare(){
		int count = 0;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (inCurve[i][j] && inCurve[i + 1][j] && inCurve[i][j + 1] && inCurve[i + 1][j + 1]) {
					count++;
				}
			}
		}

		return count;
	}

	public static void makeCurve(int x, int y, int d, int g) {
		inCurve[y][x] = true;

		// 현재 드래곤 커브의 방향을 저장
		List<Integer> curve = new ArrayList<>();
		curve.add(d);
		int pos = 1;

		for (int level = 1; level <= g; level++) {
			for (int i = pos - 1; i >= 0; i--) {
				curve.add(getNextDir(curve.get(i)));
			}

			pos = curve.size();
		}

		// 보드에 표시하기
		int nx = x;
		int ny = y;

		for (var dir : curve) {
			nx += (DX[dir]);
			ny += (DY[dir]);
			inCurve[ny][nx] = true;
		}
	}

	static int getNextDir(int d){
		return d == 3 ? 0 : d + 1;
	}

	public static void init() {
		n = scan.nextInt();

		for (int i = 0; i < n; i++) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			int d = scan.nextInt();
			int g = scan.nextInt();

			makeCurve(x, y, d, g);
		}
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String s) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(new File(s)));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		String nextLine() {
			String str = "";

			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return str;
		}

	}
}

