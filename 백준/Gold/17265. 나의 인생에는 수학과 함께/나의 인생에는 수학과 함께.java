import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();
	static StringBuilder sb = new StringBuilder();

	static final char PLUS = '+';
	static final char MINUS = '-';
	static final char MULTIPLY = '*';

	static int n;
	static char[][] board;
	static int[][] maxMemo, minMemo;

	public static void main(String[] args) {
		input();
		sb.append(findMax(n, n)).append(' ').append(findMin(n, n));
		System.out.println(sb);
	}

	public static int findMax(int x, int y){
		if (x == 1 && y == 1) {
			maxMemo[x][y] = board[x][y] - '0';
			return maxMemo[x][y];
		}

		if (!isValidRange(x, y)){
			return Integer.MIN_VALUE;
		}

		if (maxMemo[x][y] != Integer.MIN_VALUE) {
			return maxMemo[x][y];
		}

		// 현재 위치가 연산자라면? 답이 변하지 않고,
		// 현재 위치가 연산자가 아니라면 답이 변함

		if (isOperator(board[x][y])) { // 현재 위치가 연산자 -> 이전 위치는 숫자였음
			maxMemo[x][y] = Math.max(findMax(x - 1, y), findMax(x, y - 1));
		} else { // 현재 위치가 숫자 -> 이전 위치는 연산자였음
			int up = 1 <= x - 1 && x - 1 <= n ?
				operate(board[x - 1][y], findMax(x - 1, y), board[x][y] - '0')
				: Integer.MIN_VALUE;
			int left = 1 <= y - 1 && y - 1 <= n ?
				operate(board[x][y - 1], findMax(x, y - 1), board[x][y] - '0')
				: Integer.MIN_VALUE;

			maxMemo[x][y] = Math.max(up, left);
		}

		return maxMemo[x][y];
	}

	public static int findMin(int x, int y){
		if (x == 1 && y == 1) {
			minMemo[x][y] = board[x][y] - '0';
			return minMemo[x][y];
		}

		if (!isValidRange(x, y)){
			return Integer.MAX_VALUE;
		}

		if (minMemo[x][y] != Integer.MAX_VALUE) {
			return minMemo[x][y];
		}

		if (isOperator(board[x][y])) {
			minMemo[x][y] = Math.min(findMin(x - 1, y), findMin(x, y - 1));
		} else {
			int up = 1 <= x - 1 && x - 1 <= n ?
				operate(board[x - 1][y], findMin(x - 1, y), board[x][y] - '0')
				: Integer.MAX_VALUE;

			int left = 1 <= y - 1 && y - 1 <= n ?
			operate(board[x][y - 1], findMin(x, y - 1), board[x][y] - '0')
			: Integer.MAX_VALUE;

			minMemo[x][y] = Math.min(up, left);
		}

		return minMemo[x][y];
	}

	public static void print2DArr(int[][] arr){
		StringBuilder sb = new StringBuilder();

		// 1-indexed
		for (int i = 1; i <= n; i++){
			for (int j = 1; j <= n; j++){
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}
	public static boolean isOperator(char value){
		return value == PLUS || value == MINUS || value == MULTIPLY;
	}

	public static int operate(char oper, int lvalue, int rvalue){
		switch (oper){
			case PLUS -> {
				return lvalue + rvalue;
			}
			case MINUS -> {
				return lvalue - rvalue;
			}
			case MULTIPLY -> {
				return lvalue * rvalue;
			}
		}
		return -1;
	}
	public static boolean isValidRange(int x, int y){
		return 1 <= x && x <= n && 1 <= y && y <= n;
	}

	public static void input() {
		n = scan.nextInt();
		board = new char[n + 1][n + 1];
		maxMemo = new int[n + 1][n + 1];
		minMemo = new int[n + 1][n + 1];

		for (int i = 0; i <= n; i++){
			for (int j = 0; j <=n; j++){
				maxMemo[i][j] = Integer.MIN_VALUE;
				minMemo[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				board[i][j] = scan.next().charAt(0);
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
