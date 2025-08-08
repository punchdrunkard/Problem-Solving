import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static FastReader scan = new FastReader();

	// 단위가 큰 동전이 큰 우선 순위를 가짐
	// -> 다른 동전들을 합쳐서 큰 동전으로 모두 만들 수 있기 때문에
	static int price;
	static int[] coins = {500, 100, 50, 10, 5, 1};

	public static void main(String[] args) {
		price = scan.nextInt();
		System.out.println(solve());
	}

	static int solve() {
		int remain = 1000 - price;

		int count = 0;
		for (int coin : coins) {
			count += (remain / coin);
			remain %= coin;
		}

		return count;
	}

	static class FastReader {
		BufferedReader br;
		StringTokenizer st;

		public FastReader() {
			br = new BufferedReader(new InputStreamReader(System.in));
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
	}
}
