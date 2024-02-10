import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static final int INF = Integer.MAX_VALUE;

	static int n;
	static int[] price, memo; // memo[i] := i개의 카드를 구매하기 위한 최소 금액

	public static void main(String[] args) throws IOException {
		input();
		System.out.println(getMinCost(n));
	}

	public static int getMinCost(int count) {
		if (count <= 0){
			return 0;
		}

		if (count == 1){
			return price[1];
		}

		if (memo[count] != INF) {
			return memo[count];
		}

		// 그대로 사는 것 vs 쪼개서 사는 것
		memo[count] = price[count];

		for (int i = 1; i <= n; i++) {
			if (count - i >= 0) {
				memo[count] = Math.min(memo[count], getMinCost(count - i) + getMinCost(i));
			}
		}

		return memo[count];
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());

		price = new int[n + 1];
		memo = new int[n + 1];
		Arrays.fill(memo, INF);

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= n; i++){
			price[i] = Integer.parseInt(st.nextToken());
		}
	}
}
