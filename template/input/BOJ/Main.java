import java.util.*;
import java.io.*;

public class Main {

	static FastReader scan = new FastReader();
	

	static class FastReader {
		BufferedReader br;
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 테스트 케이스  파일 입출력
		FastReader() {
			try {
				System.setIn(new FileInputStream("res/1.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			br = new BufferedReader(new InputStreamReader(System.in));

		}

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