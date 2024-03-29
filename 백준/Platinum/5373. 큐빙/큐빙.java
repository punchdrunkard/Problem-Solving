import java.util.*;
import java.io.*;

public class Main {
	static FastReader scan = new FastReader();

	static char[][][] cube, temp; // temp : 결과를 저장할 복사본

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) {
		int tc = scan.nextInt();

		for (int t = 0; t < tc; t++) {
			init();

			int n = scan.nextInt();

			for (int i = 0; i < n; i++) {
				String action = scan.next();
				char face = action.charAt(0);
				char dir = action.charAt(1);
				int spinCount = dir == '+' ? 1 : 3;

				for (int cnt = 0; cnt < spinCount; cnt++) {
					spin(face);
				}
			}

			recordCubeState(0);
		}

		System.out.println(sb);
	}

	static void rotateCW(int idx) {
		char[][] copied = copySide(idx);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[idx][j][2 - i] = copied[i][j];
			}
		}
	}

	static void spin(int face) {
		temp = copyCube();

		if (face == 'U') {
			for (int i = 0; i < 3; i++) {
				cube[3][2][i] = temp[4][2 - i][2];
				cube[5][i][0] = temp[3][2][i];
				cube[2][0][2 - i] = temp[5][i][0];
				cube[4][2 - i][2] = temp[2][0][2 - i];
			}

			rotateCW(0);
		} else if (face == 'D') {
			for (int i = 0; i < 3; i++) {
				cube[2][2][i] = temp[4][i][0];
				cube[5][2 - i][2] = temp[2][2][i];
				cube[3][0][2 - i] = temp[5][2 - i][2];
				cube[4][i][0] = temp[3][0][2 - i];
			}

			rotateCW(1);
		} else if (face == 'F') {
			for (int i = 0; i < 3; i++) {
				cube[0][2][i] = temp[4][2][i];
				cube[5][2][i] = temp[0][2][i];
				cube[1][0][2 - i] = temp[5][2][i];
				cube[4][2][i] = temp[1][0][2 - i];
			}

			rotateCW(2);
		} else if (face == 'B') {
			for (int i = 0; i < 3; i++) {
				cube[1][2][i] = temp[4][0][2 - i];
				cube[5][0][2 - i] = temp[1][2][i];
				cube[0][0][2 - i] = temp[5][0][2 - i];
				cube[4][0][2 - i] = temp[0][0][2 - i];
			}

			rotateCW(3);
		} else if (face == 'L') {
			for (int i = 0; i < 3; i++) {
				cube[3][i][0] = temp[1][i][0];
				cube[0][i][0] = temp[3][i][0];
				cube[2][i][0] = temp[0][i][0];
				cube[1][i][0] = temp[2][i][0];
			}

			rotateCW(4);
		} else { // 'R'
			for (int i = 0; i < 3; i++) {
				cube[3][2 - i][2] = temp[0][2 - i][2];
				cube[1][2 - i][2] = temp[3][2 - i][2];
				cube[2][2 - i][2] = temp[1][2 - i][2];
				cube[0][2 - i][2] = temp[2][2 - i][2];
			}

			rotateCW(5);
		}
	}

	static char[][][] copyCube() {
		char[][][] temp = new char[6][3][3];

		for (int i = 0; i < 6; i++) {
			temp[i] = copySide(i);
		}

		return temp;
	}

	static char[][] copySide(int idx) {
		char[][] temp = new char[3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				temp[i][j] = cube[idx][i][j];
			}
		}

		return temp;
	}

	static void recordCubeState(int idx) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				sb.append(cube[idx][i][j]);
			}
			sb.append('\n');
		}
	}

	static void init() {
		cube = new char[6][3][3];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cube[0][i][j] = 'w'; // 위
				cube[1][i][j] = 'y'; // 아래
				cube[2][i][j] = 'r'; // 앞
				cube[3][i][j] = 'o'; // 뒤
				cube[4][i][j] = 'g'; // 왼
				cube[5][i][j] = 'b'; // 파
			}
		}
	}

	static class FastReader {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public String next() {
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

