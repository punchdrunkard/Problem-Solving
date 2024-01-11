import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static int n, m;
    public static int[] x;
    public static final int MAX = 100_000;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static long solve() {
        int lo = -1;
        int hi = MAX + 1;

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            if (check(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        return hi;
    }

    // 가로등의 높이가 h일 때, 굴다리를 모두 비출 수 있나?
    public static boolean check(int h) {
        int current = 0; // 커버해야 하는 영역

        // 첫 번째 가로등 부터
        for (int i = 0; i < m; i++){
            int st = x[i] - h;
            int en = x[i] + h;

            if (!(st <= current && current <= en)) {
                return false;
            }

            current = en;
        }

        return current >= n;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        x = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
