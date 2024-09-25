import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	// 주어진 배열

	static int n, r;
	static int[][] board, temp;

	static int boardLength;

	public static void main(String[] args) {

		init();

		for (int i = 0; i < r; i++) {
			int k = scan.nextInt();
			int l = scan.nextInt();

			solve(k, l);
		}

		printBoard();
	}

	static void solve(int opNum, int stage) {
		copyOriginalBoardToTemp();
		int subArrayLength = 1 << stage;

		if (opNum == 1) {
			op1(subArrayLength);
			return;
		}

		if (opNum == 2) {
			op2(subArrayLength);
			return;
		}

		if (opNum == 3) {
			op3(subArrayLength);
			return;
		}

		if (opNum == 4) {
			op4(subArrayLength);
			return;
		}

		if (opNum == 5) {
			op5(subArrayLength);
			return;
		}

		if (opNum == 6) {
			op6(subArrayLength);
			return;
		}

		if (opNum == 7) {
			op7(subArrayLength);
			return;
		}

		if (opNum == 8) {
			op8(subArrayLength);
			return;
		}
	}

	// 각 부분 배열을 상하 반전시킨다.
	static void op1(int subArrayLength) {
		for (int startX = 0; startX < boardLength; startX += subArrayLength) {
			for (int startY = 0; startY < boardLength; startY += subArrayLength) {

				for (int x = 0; x < subArrayLength; x++) {
					for (int y = 0; y < subArrayLength; y++) {
						board[x + startX][y + startY] = temp[startX + subArrayLength - x - 1][y + startY];
					}
				}
			}
		}
	}

	// 각 부분 배열을 좌우 반전시킨다.
	static void op2(int subArrayLength) {
		for (int startX = 0; startX < boardLength; startX += subArrayLength) {
			for (int startY = 0; startY < boardLength; startY += subArrayLength) {

				for (int x = 0; x < subArrayLength; x++) {
					for (int y = 0; y < subArrayLength; y++) {
						board[startX + x][startY + y] = temp[startX + x][startY + (subArrayLength - y - 1)];
					}
				}
			}
		}
	}

	// 반시계 90도 회전
	static void op3(int subArrayLength) {
		for (int startX = 0; startX < boardLength; startX += subArrayLength) {
			for (int startY = 0; startY < boardLength; startY += subArrayLength) {

				for (int x = 0; x < subArrayLength; x++) {
					for (int y = 0; y < subArrayLength; y++) {
						board[startX + x][startY + y] = temp[startX + subArrayLength - y - 1][startY + x];
					}
				}
			}
		}
	}

	// 시계 90도 회전
	static void op4(int subArrayLength) {
		for (int startX = 0; startX < boardLength; startX += subArrayLength) {
			for (int startY = 0; startY < boardLength; startY += subArrayLength) {

				for (int x = 0; x < subArrayLength; x++) {
					for (int y = 0; y < subArrayLength; y++) {
						board[startX + x][startY + y] = temp[startX + y][startY + subArrayLength - x - 1];
					}
				}
			}
		}
	}



	static void op5(int subArrayLength) {
		for (int startX = 0; startX < boardLength; startX += subArrayLength) {
			for (int startY = 0; startY < boardLength; startY += subArrayLength) {

				int newStartX = boardLength - subArrayLength - startX;
				int newStartY = startY;

				// 배열 붙여넣기
				for (int x = 0; x < subArrayLength; x++) {
					for (int y = 0; y < subArrayLength; y++) {
						board[newStartX + x][newStartY + y] = temp[startX + x][startY + y];
					}
				}
			}
		}
	}

	static void op6(int subArrayLength) {
		for (int startX = 0; startX < boardLength; startX += subArrayLength) {
			for (int startY = 0; startY < boardLength; startY += subArrayLength) {

				int newStartX = startX;
				int newStartY = boardLength - subArrayLength - startY;

				// 배열 붙여넣기
				for (int x = 0; x < subArrayLength; x++) {
					for (int y = 0; y < subArrayLength; y++) {
						board[newStartX + x][newStartY + y] = temp[startX + x][startY + y];
					}
				}
			}
		}
	}

	static void op7(int subArrayLength) {
		for (int startX = 0; startX < boardLength; startX += subArrayLength) {
			for (int startY = 0; startY < boardLength; startY += subArrayLength) {

				int newStartX = (startY / subArrayLength) * subArrayLength;
				int newStartY = (boardLength / subArrayLength - startX / subArrayLength - 1) * subArrayLength;

				// 배열 붙여넣기
				for (int x = 0; x < subArrayLength; x++) {
					for (int y = 0; y < subArrayLength; y++) {
						board[newStartX + x][newStartY + y] = temp[startX + x][startY + y];
					}
				}
			}
		}
	}

	static void op8(int subArrayLength) {
		for (int startX = 0; startX < boardLength; startX += subArrayLength) {
			for (int startY = 0; startY < boardLength; startY += subArrayLength) {

				int newStartX = (boardLength / subArrayLength - startY / subArrayLength - 1) * subArrayLength;
				int newStartY = (startX / subArrayLength) * subArrayLength;

				// 배열 붙여넣기
				for (int x = 0; x < subArrayLength; x++) {
					for (int y = 0; y < subArrayLength; y++) {
						board[newStartX + x][newStartY + y] = temp[startX + x][startY + y];
					}
				}
			}
		}
	}

	static void printBoard() {
		StringBuilder sb = new StringBuilder();

		for (int row = 0; row < boardLength; row++) {
			for (int col = 0; col < boardLength; col++) {
				sb.append(board[row][col]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void copyOriginalBoardToTemp() {
		for (int row = 0; row < boardLength; row++) {
			for (int col = 0; col < boardLength; col++) {
				temp[row][col] = board[row][col];
			}
		}
	}

	static void init() {
		n = scan.nextInt();
		r = scan.nextInt();

		// 한변의 길이
		boardLength = (int)Math.pow(2, n);
		board = new int[boardLength][boardLength];
		temp = new int[boardLength][boardLength];

		for (int row = 0; row < boardLength; row++) {
			for (int col = 0; col < boardLength; col++) {
				board[row][col] = scan.nextInt();
			}
		}
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
