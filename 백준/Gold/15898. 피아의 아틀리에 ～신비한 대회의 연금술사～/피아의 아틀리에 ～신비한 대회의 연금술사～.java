import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static final int[][] LOCATIONS = {{0, 0}, {0, 1}, {1, 0}, {1, 1}}; // 재료가 들어갈 수 있는 위치들

	static FastReader scan = new FastReader();

	static int answer = Integer.MIN_VALUE;

	static int n;

	static State[][][][] materials;

	static class State {
		int value;
		char color;

		State(int value, char color) {
			this.value = value;
			this.color = color;
		}
	}

	static boolean[] visited;

	static class MaterialState {
		int idx;
		int dir;
		int location;

		MaterialState(int idx, int dir, int location) {
			this.idx = idx;
			this.dir = dir;
			this.location = location;
		}
	}

	public static void main(String[] args) {
		input();
		solve(0, new LinkedList<>());
		System.out.println(answer);
	}

	static void solve(int cnt, List<MaterialState> currentMaterials) {
		if (cnt == 3) {
			State[][] current = init();

			for (var material : currentMaterials) {
				mix(materials[material.idx][material.dir], current, LOCATIONS[material.location]);
			}

			answer = Math.max(answer, getTotalQuality(current));
			return;
		}

		// 재료를 넣는다. -> 고려해야 하는 것 : 어떤 재료를 선택 / 재료의 위치 / 재료의 회전
		for (int material = 0; material < n; material++) { // material 번 재료에 대해서
			if (visited[material])
				continue;

			visited[material] = true;

			for (int dir = 0; dir < 4; dir++) { // rotate 로 회전 시킨 후
				for (int location = 0; location < 4; location++) { // 위치에 배치
					currentMaterials.add(new MaterialState(material, dir, location));
					solve(cnt + 1, currentMaterials);
					currentMaterials.remove(currentMaterials.size() - 1);
				}
			}

			visited[material] = false;
		}
	}

	static void mix(State[][] material, State[][] current, int[] location) {
		// State[][] temp = new State[5][5];
		//
		// for (int i = 0; i < 5; i++) {
		// 	for (int j = 0; j < 5; j++) {
		// 		temp[i][j] = new State(current[i][j].value, current[i][j].color);
		// 	}
		// }

		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				// 변하는 지점 -> material[i + location[0]][j + location[1]]
				int nx = x + location[0];
				int ny = y + location[1];

				// 격자의 색은 재료의 원소가 흰색인 경우 그대로, 아닌 경우 재료의 원소와 같은 색으로 칠해진다.
				if (material[x][y].color != 'W') {
					current[nx][ny].color = material[x][y].color;
				}

				// 격자의 품질은 재료의 효능이 더해진다.
				current[nx][ny].value += material[x][y].value;

				// 더한 뒤의 값이 음수인 경우 0으로, 9 초과인 경우 9로 바뀐다.
				if (current[nx][ny].value < 0) {
					current[nx][ny].value = 0;
				}

				if (current[nx][ny].value > 9) {
					current[nx][ny].value = 9;
				}
			}
		}
	}

	static int getTotalQuality(State[][] current) {
		int rCount = 0;
		int bCount = 0;
		int gCount = 0;
		int yCount = 0;

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (current[i][j].color == 'R') {
					rCount += current[i][j].value;
				}

				if (current[i][j].color == 'B') {
					bCount += current[i][j].value;
				}

				if (current[i][j].color == 'G') {
					gCount += current[i][j].value;
				}

				if (current[i][j].color == 'Y') {
					yCount += current[i][j].value;
				}
			}
		}

		return 7 * rCount + 5 * bCount + 3 * gCount + 2 * yCount;
	}

	static State[][] init() {
		State[][] initialState = new State[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				initialState[i][j] = new State(0, 'W');
			}
		}

		return initialState;
	}

	static void input() {
		n = scan.nextInt();
		materials = new State[n][4][4][4]; // materials[i][dir] := i번째 배열을 dir 로 회전
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			int[][] value = new int[4][4];
			char[][] color = new char[4][4];

			// 효능
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					value[j][k] = scan.nextInt();
				}
			}

			// 삭깔
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					color[j][k] = scan.next().charAt(0);
				}
			}

			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					materials[i][0][j][k] = new State(value[j][k], color[j][k]);
					materials[i][1][k][4 - j - 1] = new State(value[j][k], color[j][k]);
					materials[i][2][4 - j - 1][4 - k - 1] = new State(value[j][k], color[j][k]);
					materials[i][3][4 - k - 1][j] = new State(value[j][k], color[j][k]);
				}
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
