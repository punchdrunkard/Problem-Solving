import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static FastReader scan = new FastReader();

	static final int CW = 1;
	static final int CCW = -1;

	static final char N = '0';
	static final char S = '1';

	static char[][] gears = new char[4][8];

	public static void main(String[] args) {
		init();
		simulate();
		System.out.println(getScore());
	}

	static int getScore(){
		int score = 0;

		for (int i = 0; i < 4; i++) {
			if (gears[i][0] == S) {
				score += (int)Math.pow(2, i);
			}
		}

		return score;
	}

	static void simulate(){
		int k = scan.nextInt();

		for (int i = 0; i < k; i++) {
			int idx = scan.nextInt() - 1;
			int dir = scan.nextInt();

			char prev = gears[idx][6];
			int currentDir = dir;

			// 해당 idx 기준으로 왼쪽은 dir 의 반대로 회전
			for (int j = idx - 1; j >= 0; j--) {
				// 맞닿은 부분의 극이 같다면
				if (prev == gears[j][2]){
					break;
				} else {
					prev = gears[j][6];
					rotate(j ,-currentDir);
					currentDir = -currentDir;
				}
			}

			// 해당 idx 기준으로 오른쪽
			prev = gears[idx][2];
			currentDir = dir;

			for (int j = idx + 1; j < 4; j++) {
				// 맞닿은 부분의 극이 같다면
				if (prev == gears[j][6]){
					break;
				} else {
					prev = gears[j][2];
					rotate(j ,-currentDir);
					currentDir = -currentDir;
				}
			}

			// 자기 자신 회전
			rotate(idx, dir);
		}


	}

	static void rotate(int idx, int dir){
		if (dir == CCW){
			char temp = gears[idx][0];

			for (int i = 0; i < 7; i++) {
				gears[idx][i] = gears[idx][i + 1];
			}

			gears[idx][7] = temp;
		} else {
			char temp = gears[idx][7];

			for (int i = 7; i > 0; i--) {
				gears[idx][i] = gears[idx][i - 1];
			}

			gears[idx][0] = temp;
		}
	}

	static void init() {
		for (int i = 0; i < 4; i++) {
			gears[i] = scan.nextLine().toCharArray();
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

		String nextLine(){
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

