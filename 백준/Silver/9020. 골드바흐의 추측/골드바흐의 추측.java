import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++){
            int n = Integer.parseInt(br.readLine());
            int[] answer = solve(n);
            System.out.println(answer[0] + " " + answer[1]);
        }
    }

    public static int[] solve(int n){
        boolean[] primes = findPrimes(n);

        int diff = 200000;
        int[] answer = new int[]{n, n};

        // 골드바흐 파티션 구하기
        // 현재 소수와 다른 수로 합을 나타냈을 때, 다른 수가 짝수인가?
        for (int i = 2; i <= n; i++){
            // 현재 수가 소수가 아닌 경우
            if (!primes[i]) continue;

            // 현재 수가 소수인 경우
            int other = n - i;
            // 다른 수가 소수가 아닌 경우
            if (!primes[other]) continue;

            if (Math.abs(diff) > Math.abs(i - other)) {
                diff = i - other;
                answer = new int[]{i, other};
            }
        }

        Arrays.sort(answer);
        return answer;
    }

    // n보다 작은 짝수를 모두 찾는다. -> 에라토스테네스의 체
    public static boolean[] findPrimes(int n){
        boolean[] primes = new boolean[n + 1];
        Arrays.fill(primes, true);
        primes[0] = false;
        primes[1] = false;

        for (int i = 2; i * i <= n; i++){
            if (!primes[i]) continue;

            for (int j = i * i; j <= n; j += i){
                primes[j] = false;
            }
        }
        return primes;
    }
}

