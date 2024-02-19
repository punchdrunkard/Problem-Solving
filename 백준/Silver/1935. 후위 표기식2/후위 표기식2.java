import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static FastReader scan = new FastReader();
	static int n;
	static String expression;
	static double[] numbers;

	public static void main(String[] args) {
		input();
		String answer = String.format("%.2f", solve());
		System.out.println(answer);
	}

	static double solve(){
		Deque<Double> stk = new ArrayDeque<>();

		for (int i = 0; i < expression.length(); i++) {
			char current = expression.charAt(i);

			if (isOperator(current)) {
				double second = stk.pollLast();
				double first = stk.pollLast();
				stk.offerLast(calculate(current, first, second));
			} else {
				stk.offerLast(numbers[current - 'A']);
			}
		}

		return stk.getFirst();
	}

	static double calculate(char op, double num1, double num2){
		if (op == '+'){
			return num1 + num2;
		}

		if (op == '-'){
			return num1 - num2;
		}

		if (op == '*'){
			return num1 * num2;
		}

		if (op == '/') {
			return num1 / num2;
		}

		return -1;
	}

	static boolean isOperator(char c) {
		return c == '*' || c == '-' || c == '+' || c == '/';
	}

	static void input(){
		n = scan.nextInt(); // 피연산자의 갯수
		expression = scan.nextLine();
		numbers = new double[n];

		for (int i = 0; i < n; i++) {
			numbers[i] = scan.nextInt();
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
