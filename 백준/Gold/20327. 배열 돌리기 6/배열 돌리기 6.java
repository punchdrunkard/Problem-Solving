import java.io.*;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();
	static int n, r, len;
	static int[][] a, temp;

	public static void main(String[] args) {
		init();

		for (int i = 0; i < r; i++) {
			int k = scan.nextInt(); // k번 연산을
			int l = scan.nextInt(); // 단계 l로 적용
			solve(k, l);
		}

		printArr(a);
	}

	static void solve(int k, int l) {
		int size = (1 << l); // 부분 배열의 길이
		copyOriginalToTemp();

		if (k == 1) { // 각 부분 배열을 상하 반전
			q1(size);
		} else if (k == 2) {
			q2(size);
		} else if (k == 3) {
			q3(size);
		} else if (k == 4) {
			q4(size);
		} else if (k == 5) {
			q5(size);
		} else if (k == 6) {
			q6(size);
		} else if (k == 7) {
			q7(size);
		} else if (k == 8) {
			q8(size);
		}
	}

	static void q1(int size) { // 부분 배열 상하 반전
		for (int x = 0; x < len; x += size) { // 좌상단 좌표 (x, y) 에 대해
			for (int y = 0; y < len; y += size) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						a[x + i][y + j] = temp[x + (size - i - 1)][y + j];
					}
				}
			}
		}
	}

	static void q2(int size) { // 부분 배열 좌우 반전
		for (int x = 0; x < len; x += size) {
			for (int y = 0; y < len; y += size) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						a[x + i][y + j] = temp[x + i][y + (size - j - 1)];
					}
				}
			}
		}
	}

	static void q3(int size) { // 부분 배열을 반시계 90도 회전
		for (int x = 0; x < len; x += size) {
			for (int y = 0; y < len; y += size) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						a[x + i][y + j] = temp[x + (size - j - 1)][y + i];
					}
				}
			}
		}
	}

	static void q4(int size) { // 부분 배열을 시계방향 90도 회전
		for (int x = 0; x < len; x += size) {
			for (int y = 0; y < len; y += size) {
				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						a[x + i][y + j] = temp[x + j][y + (size - i - 1)];
					}
				}
			}
		}
	}

	static void q5(int size) {
		for (int x = 0; x < len; x += size) {
			for (int y = 0; y < len; y += size) {
				// 새로운 좌상단 좌표를 구한다.
				int x1 = len - size - x;
				int y1 = y;

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						a[x1 + i][y1 + j] = temp[x + i][y + j];
					}
				}
			}
		}
	}

	static void q6(int size) {
		for (int x = 0; x < len; x += size) {
			for (int y = 0; y < len; y += size) {
				// 새로운 좌상단 좌표
				int x1 = x;
				int y1 = len - size - y;

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						a[x1 + i][y1 + j] = temp[x + i][y + j];
					}
				}
			}
		}
	}

	static void q7(int size) {
		for (int x = 0; x < len; x += size) {
			for (int y = 0; y < len; y += size) {
				int x1 = (y / size) * size;
				int y1 = (len / size - 1 - x / size) * size;

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						a[x1 + i][y1 + j] = temp[x + i][y + j];
					}
				}
			}
		}
	}

	static void q8(int size) {
		for (int x = 0; x < len; x += size) {
			for (int y = 0; y < len; y += size) {
				int x1 = (len / size - 1 - y / size) * size;
				int y1 = (x / size) * size;

				for (int i = 0; i < size; i++) {
					for (int j = 0; j < size; j++) {
						a[x1 + i][y1 + j] = temp[x + i][y + j];
					}
				}
			}
		}
	}

	static void printArr(int[][] arr) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void copyOriginalToTemp() {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				temp[i][j] = a[i][j];
			}
		}
	}

	static void init() {
		n = scan.nextInt();
		r = scan.nextInt();
		len = (1 << n);
		a = new int[len][len];
		temp = new int[len][len];

		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				a[i][j] = scan.nextInt();
			}
		}

		copyOriginalToTemp();
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
