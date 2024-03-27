import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();

    static final int[] DX = {-1, 1, 0, 0};
    static final int[] DY = {0, 0, -1, 1};

    static int n, m, t;
    static int[][] flatters;

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    static int solve() {
        for (int i = 0; i < t; i++) {
            int x = scan.nextInt(); // 원판의 번호
            int d = scan.nextInt(); // 방향
            int k = scan.nextInt(); // 회전 횟수

            int rotateCount = k % m;

            for (int j = 0; j <= n; j += x) {
                if (j == 0) continue;

                int idx = j - 1;

                for (int r = 0; r < rotateCount; r++) {
                    if (d == 0) {
                        rotateCW(idx);
                    } else {
                        rotateCCW(idx);
                    }
                }
            }

            if (!erase()) {
                makeEqual();
            }
        }

        return getTotalSum();
    }

    static void makeEqual() {
        int count = 0;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (flatters[i][j] == 0) continue;
                count++;
                sum += flatters[i][j];
            }
        }

        if (count == 0) return;
        double avg = (double) sum / count;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (flatters[i][j] == 0) continue;

                if (flatters[i][j] < avg) {
                    flatters[i][j]++;
                } else if (flatters[i][j] > avg) {
                    flatters[i][j]--;
                }
            }
        }
    }

    // 지우기 작업이 일어났으면 true, 일어나지 않으면 false
    static boolean erase() {
        boolean erased = false;
        boolean[][] checked = new boolean[n][m]; // 지워야 하는 부분 체크

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int dir = 0; dir < 4; dir++) {
                    int nx = i + DX[dir];
                    int ny = j + DY[dir];

                    if (ny < 0) ny = m - 1;
                    if (ny >= m) ny = 0;

                    if (nx < 0 || nx >= n || flatters[nx][ny] == 0 || (i == nx && j == ny)) {
                        continue;
                    }

                    if (flatters[i][j] == flatters[nx][ny]) {
                        checked[i][j] = true;
                        checked[nx][ny] = true;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (checked[i][j]) {
                    erased = true;
                    flatters[i][j] = 0;
                }
            }
        }

        return erased;
    }

    static void rotateCW(int idx) {
        int last = flatters[idx][m - 1];

        for (int i = m - 1; i > 0; i--) {
            flatters[idx][i] = flatters[idx][i - 1];
        }

        flatters[idx][0] = last;
    }

    static void rotateCCW(int idx) {
        int first = flatters[idx][0];

        for (int i = 0; i < m - 1; i++) {
            flatters[idx][i] = flatters[idx][i + 1];
        }

        flatters[idx][m - 1] = first;

    }

    static void printArr(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append("[ ");

            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append("]\n");
        }

        System.out.println(sb);
    }


    // 원판의 적힌 수의 합을 리턴
    static int getTotalSum() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count += flatters[i][j];
            }
        }

        return count;
    }

    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        t = scan.nextInt();

        flatters = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flatters[i][j] = scan.nextInt();
            }
        }
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreElements()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }
    }
}
