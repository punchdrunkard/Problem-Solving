import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static String a, b;

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(canTransformToTarget(a, b) ? 1 : 0);
	}

	public static boolean canTransformToTarget(String start, String target) {
		while (target.length() >= start.length()) {
			if (target.endsWith("A")) {
				target = removeLastCharacter(target);
			} else if (target.endsWith("B")) {
				target = reverse(removeLastCharacter(target));
			}

			if (target.equals(start)) {
				return true;
			}
		}

		return false;
	}

	public static String removeLastCharacter(String str) {
		return str.substring(0, str.length() - 1);
	}

	public static String reverse(String str) {
		return new StringBuilder(str).reverse().toString();
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		a = br.readLine();
		b = br.readLine();
	}
}

