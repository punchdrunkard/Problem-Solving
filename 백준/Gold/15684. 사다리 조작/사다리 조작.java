import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n, m, h;
    static boolean[][] lines; // lines[i][j] := i번째 위치에 j와 j + 1 을 연결하는 가로선이 존재한다.

    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        init();
        dfs(0, 1, 1);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    static void dfs(int count, int currentRow, int currentCol) {
        if (count >= answer || count > 3) {
            return;
        }

        if (matched()) {
            answer = count;
            return;
        }

        for (int r = currentRow; r <= h; r++) {
            for (int c = currentCol; c < n; c++) {
                if (lines[r][c] || lines[r][c - 1] || lines[r][c + 1]) continue;
                lines[r][c] = true;
                dfs(count + 1, r, c);
                lines[r][c] = false;
            }

            currentCol = 1; // 다음 행으로 갈 떄, 맨 앞 col 부터 선을 그어줘야 하기 떄문!
        }
    }

    static boolean matched() {
        // 사다리 결과 테스트용 코드
        // int[] temp = new int[n + 1];

        for (int c = 1; c <= n; c++) {
            int currentCol = c;
            int currentRow = 1;

            while (currentRow <= h) {
                if (lines[currentRow][currentCol]) { // -> 로 이동하는 경우
                    currentCol++;
                    currentRow++;
                } else if (lines[currentRow][currentCol - 1]) { // <- 로 이동하는 경우
                    currentCol--;
                    currentRow++;
                } else {
                    currentRow++;
                }
            }

            if (currentCol != c) return false;
            // temp[c] = currentCol;
        }

//        StringBuilder sb = new StringBuilder();
//        for (int i = 1; i <= n; i++) {
//            sb.append(i).append(" 번 사다리의 결과 : ").append(temp[i]).append('\n');
//        }
//
//        System.out.println(sb);

        return true;
    }


    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        h = scan.nextInt();

        lines = new boolean[h + 1][n + 1];

        for (int i = 0; i < m; i++) {
            int a, b;
            a = scan.nextInt();
            b = scan.nextInt();

            lines[a][b] = true;
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

