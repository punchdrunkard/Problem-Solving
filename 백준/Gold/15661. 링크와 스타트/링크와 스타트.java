import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n;
    static int[][] s;
    static int answer = Integer.MAX_VALUE;

    static boolean[] start;

    public static void main(String[] args) {
        init();
        dfs(0, 0);
        System.out.println(answer);
    }

    static void dfs(int idx, int sCount) {
        // 답을 찾은 경우
        if (idx == n) {
            if (sCount == n) return; // 팀에 한 명 이상 존재해야 한다.
            int diff = getStatusDiff();
            answer = Math.min(diff, answer);
            return;
        }

        start[idx] = true;
        dfs(idx + 1, sCount + 1); // s팀에 배정
        start[idx] = false;
        dfs(idx + 1, sCount); // l팀에 배정
    }

    static int getStatusDiff() {
        int sStatus = 0;
        int lStatus = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                if (start[i] && start[j]) {
                    sStatus += s[i][j];
                }

                if (!start[i] && !start[j]) {
                    lStatus += s[i][j];
                }
            }
        }

        return Math.abs(sStatus - lStatus);
    }

    static void init() {
        n = scan.nextInt();
        s = new int[n][n];
        start = new boolean[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                s[i][j] = scan.nextInt();
            }
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}

