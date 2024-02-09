import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static final int MOD = 1_000_000_000;
	static int n, k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		n = inputs[0];
		k = inputs[1];

		long[][] dp = new long[n + 1][k + 1];

		for (int i = 0; i <= n; i++){
			dp[i][1] = 1;
		}

		for (int cnt = 1; cnt <= k; cnt++){
			for (int i = 0; i <= n; i++){
				for (int j = 0; j <= i; j++) {
					dp[i][cnt] += (dp[i - j][cnt - 1] % MOD);
				}
			}
		}

		System.out.println(dp[n][k] % MOD);
	}
}
