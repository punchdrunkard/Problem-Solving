import java.io.*;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();
	static final int[] DX = {-1, 1, 0, 0};
	static final int[] DY = {0, 0, -1, 1};
	static final int UNUSED = -1; // 사용하지 않는 칸 표시

	static int n, k;
	static int[][] fishBall, temp;
	static int bottom, left; // 가장 아래쪽 row 번호, 가장 왼쪽 col 번호

	static int[] floor; // floor[i] := i행에 쌓여있는 어항의 갯수

	static StringBuilder answer = new StringBuilder();

	public static void main(String[] args) {
		init();
		solve();
		System.out.println(answer);
	}

	static void solve() {
		int count = 0;

		while (!isEnd()) { // 물고기가 가장 적게 들어있는 어항의 물고기 수
			count++;

			// 1. 물고기 수가 가장 작은 어항들을 찾는다.
			List<Integer> minFishBalls = findMinFishBalls();
			for (int f : minFishBalls) {
				fishBall[bottom][f]++;
			}

			// 2. 어항을 쌓는다.
			stackFishBall();
			// print2D(fishBall);

			// 3. 물고기의 수를 조절한다.
			moveFish();

			// 4. 어항을 바닥에 일렬로 놓는다.
			align();

			// 5. 다시 공중부양 작업을 한다.
			reLevitation(left, n / 2);
			reLevitation(left, n / 4);

			// 6. 물고기의 수를 조절한다. (3과 구현이 같음)
			moveFish();

			// 7. 어항을 바닥에 일렬로 놓는다. (4와 구현이 같음)
			align();
		}

		// System.out.println(Arrays.toString(fishBall[0]));
		answer.append(count);
	}

	static void reLevitation(int start, int w) {
		int h = floor[start];

		int[][] target = new int[h][w];

		for (int i = 0; i < h; i++) {
			for (int j = start; j < start + w; j++) {
				target[i][j - start] = fishBall[i][j];
			}
		}

		// 180도 회전
		target = rotateEachArr(target);
		target = rotateEachArr(target);

		int y2 = start + w;

		for (int y = y2; y < y2 + w; y++) {
			for (int x = floor[y]; x < floor[y] + h; x++) {
				fishBall[x][y] = target[x - floor[y]][y - y2];
			}
		}

		for (int y = start; y < y2; y++) {
			for (int x = 0; x < floor[y]; x++) {
				fishBall[x][y] = UNUSED;
			}

			floor[y] = 0;
		}

		for (int y = y2; y < y2 + w; y++) {
			floor[y] += h;
		}

		left = y2;
	}

	static int[][] rotateEachArr(int[][] target) {
		int h = target.length;
		int w = target[0].length;

		int[][] temp = new int[w][h];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				temp[w - j - 1][i] = target[i][j];
			}
		}

		return temp;
	}

	static void align() {
		List<Integer> temp = new ArrayList<>();

		for (int c = left; c < n; c++) {
			for (int r = 0; r < floor[c]; r++) {
				temp.add(fishBall[r][c]);
			}
		}

		left = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				fishBall[i][j] = UNUSED;
			}
		}

		for (int i = 0; i < n; i++) {
			fishBall[0][i] = temp.get(i);
			floor[i] = 1;
		}
	}

	static void moveFish() {
		copyArr(fishBall, temp);

		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (fishBall[x][y] == UNUSED) {
					continue;
				}

				int current = fishBall[x][y];

				for (int dir = 0; dir < 4; dir++) {
					int nx = x + DX[dir];
					int ny = y + DY[dir];

					if (!isValidRange(nx, ny) || fishBall[nx][ny] == UNUSED || current < fishBall[nx][ny]) {
						continue;
					}

					int next = fishBall[nx][ny];
					int d = Math.abs(current - next) / 5;

					temp[x][y] -= d;
					temp[nx][ny] += d;
				}
			}
		}

		copyArr(temp, fishBall);
	}

	static boolean isValidRange(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < n;
	}

	static void copyArr(int[][] from, int[][] to) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				to[i][j] = from[i][j];
			}
		}
	}

	static void stackFishBall() {
		// 가장 왼쪽의 어항을 그 오른쪽에 올린다.
		fishBall[bottom + 1][1] = fishBall[bottom][0];
		fishBall[bottom][0] = UNUSED;
		floor[0]--;
		floor[1]++;

		int w = 1; // 현재 너비
		int h = 2; // 현재 높이

		// 2개 이상 쌓여있는 어항들을 공중부양
		left = 1;
		int y1 = left; // 공중부양 시작 좌표
		int y2 = left + w;// 공중부양된 어항을 쌓기 시작하는 좌표

		while (y2 + h <= n) {
			// 회전된 배열 얻기
			int[][] rotated = rotate(w, h, y1, y2);

			// 회전된 부분 붙이기
			for (int i = 0; i < w; i++) { // 열
				for (int j = y2; j < y2 + h; j++) {
					fishBall[i + floor[j]][j] = rotated[i][j - y2];
				}
			}

			for (int y = y1; y < y2; y++) {
				for (int x = 0; x < h; x++) {
					fishBall[x][y] = UNUSED;
					floor[y] = 0;
				}
			}

			for (int y = y2; y < y2 + h; y++) {
				floor[y] += w;
			}

			// 다음 값 업데이트 : h, w, y1, y2
			w = h;
			h = floor[y2];
			left = y2;
			y1 = left;
			y2 = y1 + w;
		}
	}

	static int[][] rotate(int w, int h, int y1, int y2) {
		int[][] temp = new int[w][h];

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				temp[w - j - 1][i] = fishBall[i][j + y1];
			}
		}

		return temp;
	}

	static List<Integer> findMinFishBalls() {
		int min = Integer.MAX_VALUE;

		// 이미 어항이 바닥에 놓여져 있는 상태이므로
		for (int i = 0; i < n; i++) {
			if (fishBall[bottom][i] == -1) {
				continue;
			}

			min = Math.min(fishBall[bottom][i], min);
		}

		List<Integer> fishballs = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (fishBall[bottom][i] == -1) {
				continue;
			}

			if (fishBall[bottom][i] == min) {
				fishballs.add(i);
			}
		}

		return fishballs;
	}

	static boolean isEnd() {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int i = 0; i < n; i++) {
			if (fishBall[bottom][i] == UNUSED) {
				continue;
			}

			max = Math.max(fishBall[bottom][i], max);
			min = Math.min(fishBall[bottom][i], min);
		}

		return max - min <= k;
	}

	static void print2D(int[][] arr) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j] == UNUSED ? " " : arr[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static void init() {
		n = scan.nextInt();
		k = scan.nextInt();
		fishBall = new int[n][n];
		temp = new int[n][n];
		floor = new int[n];
		bottom = 0;
		left = 0;

		for (int i = 0; i < n; i++) {
			fishBall[bottom][i] = scan.nextInt();
			floor[i] = 1;
		}

		// 나머지 부분은 unused 로 초기화
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n; j++) {
				fishBall[i][j] = UNUSED;
			}
		}
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
