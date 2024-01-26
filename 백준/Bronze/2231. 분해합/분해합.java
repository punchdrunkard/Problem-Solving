import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		int answer = 0;
		// 최악의 경우가 100만이라서 brute force 가능
		for (int i = 1; i <= (n - 1); i++){
			if (i + findDigitSum(i) == n) {
				answer = i;
				break;
			}
		}

		System.out.println(answer);
	}

	// 자릿수 합 구하는 함수
	public static int findDigitSum(int num){
		int sum = 0;

		while (num > 0){
			sum += (num % 10);
			num /= 10;
		}

		return sum;
	}
}
