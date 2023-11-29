import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k;
    static boolean[] robots;
    static int[] belts;

    public static void main(String[] args) throws IOException {
        input();
        int answer = solve();
        System.out.println(answer);
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        belts = new int[2 * n];
        robots = new boolean[2 * n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            belts[i] = Integer.parseInt(st.nextToken());
        }
        br.close();
    }

    static int solve() {
        int time = 0;
        while (!isEnd()) {
            time++;
            rotate();
            moveRobot();
            if (belts[0] != 0 && !robots[0]) {
                belts[0]--;
                robots[0] = true;
            }
        }
        return time;
    }

    static void rotate() {
        rotateBelt();
        rotateRobot();
        if (robots[n - 1]) {
            robots[n - 1] = false;
        }
    }

    static void rotateBelt() {
        int last = belts[2 * n - 1];
        for (int i = 2 * n - 1; i > 0; i--) {
            belts[i] = belts[i - 1];
        }
        belts[0] = last;
    }

    static void rotateRobot() {
        boolean last = robots[2 * n - 1];
        for (int i = 2 * n - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }
        robots[0] = last;
        if (robots[n - 1]) {
            robots[n - 1] = false;
        }
    }

    static void moveRobot() {
        for (int i = n - 2; i >= 0; i--) {
            if (robots[i] && !robots[i + 1] && belts[i + 1] > 0) {
                robots[i] = false;
                robots[i + 1] = true;
                belts[i + 1]--;
            }
        }
        if (robots[n - 1]) {
            robots[n - 1] = false;
        }
    }

    static boolean isEnd() {
        int count = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (belts[i] == 0) {
                count++;
            }
        }
        return count >= k;
    }
}
