import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static int n, r, length;
	static int[][] original, temp;

	public static void main(String[] args) {
		init();

		for (int i = 0; i < r; i++) {
			int k = scan.nextInt();
			int l = scan.nextInt();

			solve(k, l);
		}

		printAnswer();
	}

	static void solve(int opNum, int l) {
		int unitLength = 1 << l;
		copyOriginalToTemp();

		if (opNum == 1) {
			op1(unitLength);
			return;
		}

		if (opNum == 2) {
			op2(unitLength);
			return;
		}

		if (opNum == 3) {
			op3(unitLength);
			return;
		}

		if (opNum == 4) {
			op4(unitLength);
			return;
		}

		if (opNum == 5) {
			op5(unitLength);
			return;
		}

		if (opNum == 6) {
			op6(unitLength);
			return;
		}

		if (opNum == 7) {
			op7(unitLength);
			return;
		}

		if (opNum == 8) {
			op8(unitLength);
			return;
		}
	}

	// 상하 반전
	static void op1(int unitLength) {
		for (int startX = 0; startX < length; startX += unitLength) {
			for (int startY = 0; startY < length; startY += unitLength) {
				for (int x = 0; x < unitLength; x++) {
					for (int y = 0; y < unitLength; y++) {
						original[startX + x][startY + y] = temp[startX + unitLength - x - 1][startY + y];
					}
				}
			}
		}
	}

	// 좌우 반전
	static void op2(int unitLength) {
		for (int startX = 0; startX < length; startX += unitLength) {
			for (int startY = 0; startY < length; startY += unitLength) {
				for (int x = 0; x < unitLength; x++) {
					for (int y = 0; y < unitLength; y++) {
						original[startX + x][startY + y] = temp[startX + x][startY + unitLength - y - 1];
					}
				}
			}
		}
	}

	// 반시계방향 90도 회전
	static void op3(int unitLength) {
		for (int startX = 0; startX < length; startX += unitLength) {
			for (int startY = 0; startY < length; startY += unitLength) {
				for (int x = 0; x < unitLength; x++) {
					for (int y = 0; y < unitLength; y++) {
						original[startX + x][startY + y] = temp[startX + unitLength - y - 1][startY + x];
					}
				}
			}
		}
	}

	// 시계방향 90도 회전
	static void op4(int unitLength) {
		for (int startX = 0; startX < length; startX += unitLength) {
			for (int startY = 0; startY < length; startY += unitLength) {
				for (int x = 0; x < unitLength; x++) {
					for (int y = 0; y < unitLength; y++) {
						original[startX + x][startY + y] = temp[startX + y][startY + unitLength - x - 1];
					}
				}
			}
		}
	}

	// 부분 배열 단위 상하 반전
	static void op5(int unitLength) {
		for (int startX = 0; startX < length; startX += unitLength) {
			for (int startY = 0; startY < length; startY += unitLength) {

				int newStartX = length - unitLength - startX;
				int newStartY = startY;

				for (int x = 0; x < unitLength; x++) {
					for (int y = 0; y < unitLength; y++) {
						original[newStartX + x][newStartY + y] = temp[startX + x][startY + y];
					}
				}
			}
		}
	}

	// 부분 배열 단위 좌우 반전
	static void op6(int unitLength) {
		for (int startX = 0; startX < length; startX += unitLength) {
			for (int startY = 0; startY < length; startY += unitLength) {

				int newStartX = startX;
				int newStartY = length - unitLength - startY;

				for (int x = 0; x < unitLength; x++) {
					for (int y = 0; y < unitLength; y++) {
						original[newStartX + x][newStartY + y] = temp[startX + x][startY + y];
					}
				}
			}
		}
	}

	// 부분 배열 단위 시계 방향 90도 회전
	static void op7(int unitLength) {
		for (int startX = 0; startX < length; startX += unitLength) {
			for (int startY = 0; startY < length; startY += unitLength) {

				int newStartX = (startY / unitLength) * unitLength;
				int newStartY = (length / unitLength - startX / unitLength - 1) * unitLength;

				for (int x = 0; x < unitLength; x++) {
					for (int y = 0; y < unitLength; y++) {
						original[newStartX + x][newStartY + y] = temp[startX + x][startY + y];
					}
				}
			}
		}
	}

	// 부분 배열 단위 반시계 방향 90도 회전
	static void op8(int unitLength) {
		for (int startX = 0; startX < length; startX += unitLength) {
			for (int startY = 0; startY < length; startY += unitLength) {

				int newStartX = (length / unitLength - 1 - startY / unitLength) * unitLength;
				int newStartY = (startX / unitLength) * unitLength;

				for (int x = 0; x < unitLength; x++) {
					for (int y = 0; y < unitLength; y++) {
						original[newStartX + x][newStartY + y] = temp[startX + x][startY + y];
					}
				}
			}
		}
	}

	static void copyOriginalToTemp() {
		for (int x = 0; x < length; x++) {
			for (int y = 0; y < length; y++) {
				temp[x][y] = original[x][y];
			}
		}
	}

	static void init() {
		n = scan.nextInt();
		r = scan.nextInt();
		length = 1 << n;

		original = new int[length][length];
		temp = new int[length][length];

		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				original[row][col] = scan.nextInt();
			}
		}
	}

	static void printAnswer() {
		StringBuilder sb = new StringBuilder();

		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				sb.append(original[row][col]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

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

		int nextInt() {
			return Integer.parseInt(next());
		}

	}
}
