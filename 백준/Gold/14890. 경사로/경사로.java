import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();

	static int n, l;
	static int[][] board;

	public static void main(String[] args) {
		init();

		int answer = 0;
		answer += solve();
		rotate();
		answer += solve();

		System.out.println(answer);
	}

	static int solve() {
		int count = 0;

		for (int row = 0; row < n; row++) {
			int current = 1; // 현재 유효거리
			boolean flag = true;

			for (int col = 1; col < n; col++) {
				if (board[row][col - 1] == board[row][col]) { // 높이 차이가 나지 않는 경우
					current++;
				} else if (board[row][col] - board[row][col - 1] == 1 && current >= l) { // 오르막길을 세워야 하는 경우
					current = 1;
				} else if (board[row][col - 1] - board[row][col] == 1 && current >= 0) { // 내리막길의 경우
					current = 1 - l;
				} else { // 경사로를 놓을 수 없는 경우
					flag = false;
					break;
				}
			}
			if (flag && current >= 0)
				count++;
		}

		return count;
	}

	static void rotate() {
		int[][] temp = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				temp[i][j] = board[j][n - i - 1];
			}
		}

		board = temp;
	}

	static void init() {
		n = scan.nextInt();
		l = scan.nextInt();
		board = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = scan.nextInt();
			}
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

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
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

