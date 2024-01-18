import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int n;
    static Matrix[] matries;
    public static class Matrix {
        int r, c;

        Matrix(int r, int c){
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void solve(){
        // dp[i][j] := i번 부터 j번까지 행렬을 연산했을 때 최소곱셈횟수
        long dp[][] = new long[n][n];
        // result[i][j] := i번 부터 j번까지 행렬을 연산했을 때 행렬의 상태
        Matrix[][] result = new Matrix[n][n];

        // 초기값 지정
        for (int i = 0; i < n; i++){
            dp[i][i] = 0;
            result[i][i] = matries[i];
        }

        for (int len = 2; len <= n; len++){ // 현재 구간의 길이
            for (int st = 0; st <= n - len; st++) { // 시작점
                int en = st + len - 1; // 끝 점

                // 현재 구간에서의 값 초기화
                dp[st][en] = Long.MAX_VALUE;

                // 중간 지점
                for (int mid = st; mid < en; mid++){
                    long current = dp[st][mid]+ dp[mid + 1][en] +result[st][mid].r * result[mid + 1][en].r * result[mid + 1][en].c;

                    if (current < dp[st][en]){
                        dp[st][en] = current;
                        result[st][en] = new Matrix(matries[st].r, matries[en].c);
                    }
                }
            }
        }
        System.out.println(dp[0][n - 1]);
    }

    static void input() throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine()); 

        matries = new Matrix[n];

        for (int i = 0; i < n; i++){
            int[] matrix = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            matries[i] = new Matrix(matrix[0], matrix[1]);
        }
    }
}

