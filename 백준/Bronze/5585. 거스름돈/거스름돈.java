import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// static FastReader scan = new FastReader("src/input/2.inp");
	static FastReader scan = new FastReader();

	// 단위가 큰 동전이 큰 우선 순위를 가짐
	// -> 다른 동전들을 합쳐서 큰 동전으로 모두 만들 수 있기 때문에
	static int price;

	public static void main(String[] args) {
		price = scan.nextInt();
		System.out.println(solve());
	}

	static int solve() {
		int remain = 1000 - price;

		int count500 = 0;
		int count100 = 0;
		int count50 = 0;
		int count10 = 0;
		int count5 = 0;
		int count1 = 0;

		count500 += (remain / 500);
		remain %= 500;
		count100 += (remain / 100);
		remain %= 100;
		count50 += (remain / 50);
		remain %= 50;
		count10 += (remain / 10);
		remain %= 10;
		count5 += (remain / 5);
		remain %= 5;
		count5 += remain;

		return count500 + count100 + count50 + count10 + count5 + count1;
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public FastReader(String fileName) {
			try {
				br = new BufferedReader(new FileReader(fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		boolean hasNext() {
			while (st == null || !st.hasMoreTokens()) {
				try {
					String line = br.readLine();
					if (line == null) { // EOF에 도달하면 false 반환
						return false;
					}
					st = new StringTokenizer(line);
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			return true;
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
