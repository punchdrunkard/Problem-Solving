import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();

    static int n;
    static int[][] result; // result[i][j] := i 이닝에서 j 번 선수의 결과
    static int[] base = new int[4]; // 진루 상태
    static int score = Integer.MIN_VALUE;
    static int[] seq = new int[9];

    public static void main(String[] args) {
        init();

        Arrays.fill(seq, -1);
        Arrays.fill(base, -1);

        seq[3] = 0;
        dfs(0, 1);

        System.out.println(score);
    }

    static void dfs(int prev, int count) {
        if (prev > 8) {
            return;
        }

        if (count == 9) {
            int currentScore = game();
            score = Math.max(score, currentScore);
            return;
        }

        for (int i = 0; i < 9; i++) { // 타순에 타자를 배치
            if (i == 3) { // 4번 타자는 0번 선수이다.
                continue;
            }

            if (seq[i] != -1) { // i번은 이미 배정되어 있는 타순이다.
                continue;
            }

            seq[i] = prev + 1;
            dfs(prev + 1, count + 1);
            seq[i] = -1;
        }
    }

    static int game() {
        int currentScore = 0;
        int current = 0; // 현재 타자

        // n 이닝 동안 진행
        for (int i = 0; i < n; i++) {
            int out = 0;

            boolean[] inBase = new boolean[9];
            Arrays.fill(base, -1);

            while (out < 3) {
                current %= 9;

                int player = seq[current];

                if (inBase[player]) {
                    current++;
                    continue;
                }

                int res = result[i][player];

                if (res == 1) { // 안타
                    currentScore += hit(inBase);

                    base[0] = player;
                    inBase[player] = true;
                } else if (res == 2) { // 2루타
                    currentScore += hit(inBase);
                    currentScore += hit(inBase);

                    base[1] = player;
                    inBase[player] = true;
                } else if (res == 3) { // 3루타
                    currentScore += hit(inBase);
                    currentScore += hit(inBase);
                    currentScore += hit(inBase);

                    base[2] = player;
                    inBase[player] = true;
                } else if (res == 4) { // 홈런
                    // 전부 들어온다.
                    int cnt = 0;
                    for (int k = 0; k < 4; k++) {
                        if (base[k] != -1) {
                            cnt++;
                            inBase[base[k]] = false;
                            base[k] = -1;
                        }
                    }

                    currentScore += (cnt + 1);
                } else { // 아웃
                    out++;
                }

                // System.out.println(player + "의 " + res + " 공격 이후");
                // System.out.println(Arrays.toString(base));
                current++;
            }
        }

        return currentScore;
    }

    static int hit(boolean[] inBase) {
        int score = 0;

        int temp = base[3];

        for (int i = 3; i >= 1; i--) {
            base[i] = base[i - 1];
        }

        base[0] = temp;

        if (base[3] != -1) {
            inBase[base[3]] = false;
            score++;
            base[3] = -1;
            // System.out.println("득점");
        }

        return score;
    }


    static void init() {
        n = scan.nextInt();
        result = new int[n][9];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 9; j++) {
                result[i][j] = scan.nextInt();
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
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }
    }
}
