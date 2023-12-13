import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int t;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int[] inputs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            int x1 = inputs[0];
            int y1 = inputs[1];
            int r1 = inputs[2];
            int x2 = inputs[3];
            int y2 = inputs[4];
            int r2 = inputs[5];

            System.out.println(solve(x1, y1, r1, x2, y2, r2));
        }
    }

    public static int solve(int x1, int y1, int r1, int x2, int y2, int r2) {
        int distanceSquared = square(x1 - x2) + square(y1 - y2);
        int sumOfRadiiSquared = square(r1 + r2);
        int diffOfRadiiSquared = square(r1 - r2);

        // 두 원이 일치할 때
        if (x1 == x2 && y1 == y2 && r1 == r2) return -1;

        // 두 원이 외접할 때
        if (sumOfRadiiSquared == distanceSquared) return 1;

        // 두 원이 내접할 때
        if (diffOfRadiiSquared == distanceSquared) return 1;

        // 두 원이 만나지 않을 때
        if (distanceSquared > sumOfRadiiSquared || distanceSquared < diffOfRadiiSquared) return 0;

        return 2; // 두 점에서 만나는 경우
    }

    public static int square(int n) {
        return n * n;
    }
}
