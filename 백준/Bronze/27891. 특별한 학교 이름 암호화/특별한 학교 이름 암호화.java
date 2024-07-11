import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();

	static String[] tempNames; // 마지막 연산 직전까지 적용시킨 이름

	// ~ 결과가 나오면 ~ 학교 입니다.
	static Map<String, String> nameMap = new HashMap<>();

	public static void main(String[] args) {

		processName();
		String encrypted = scan.next();

		// 암호화된 이름에서 0 ~ 25까지의 n을 뒤로 이동시켜보자.
		for (int n = 0; n <= 25; n++) {
			StringBuilder temp = new StringBuilder(encrypted);

			for (int i = 0; i < 10; i++) {
				char c = temp.charAt(i);
				char decrypted = (char) (((c - 'a' - n + 26) % 26) + 'a');
				temp.setCharAt(i, decrypted);
			}

			String result = temp.toString();

			if (nameMap.containsKey(result)) {
				System.out.println(nameMap.get(result));
				break;
			}
		}
	}

	static void processName() {
		String[] fullNames = new String[] {
			"North London Collegiate School",
			"Branksome Hall Asia",
			"Korea International School",
			"St. Johnsbury Academy"
		};

		String[] shortNames = new String[] {
			"NLCS", "BHA", "KIS", "SJA"
		};

		tempNames = Arrays.stream(fullNames)
			// 공백과 문장 부호를 제거한다.
			.map(name -> name.replaceAll("[\\s\\p{Punct}]", ""))
			// 모든 문자를 소문자로 변환한다.
			.map(String::toLowerCase)
			// 첫 10글자만 유지한다.
			.map(name -> name.substring(0, 10))
			.toArray(String[]::new);

		for (int i = 0; i < 4; i++) {
			nameMap.put(tempNames[i], shortNames[i]);
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
