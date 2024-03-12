import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n, m, r;
    static int[] controls;

    static int[][] board;

    public static void main(String[] args) {
        input();
        solve();
        print(board);
    }

    static void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void solve() {
        for (int i = 0; i < r; i++) {
            int control = controls[i];

            switch (control) {
                case 1:
                    flipUpDown();
                    break;
                case 2:
                    flipLeftRight();
                    break;
                case 3:
                    rotate();
                    break;
                case 4:
                    rotate();
                    rotate();
                    rotate();
                    break;
                case 5:
                    moveGroups();
                    break;
                case 6:
                    moveGroups();
                    moveGroups();
                    moveGroups();
                    break;

            }
        }
    }

    static void moveGroups() {
        int n = board.length;
        int m = board[0].length;

        int[][] temp = new int[n][m];

        // 1번 -> 2번
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < m / 2; j++) {
                temp[i][j + m / 2] = board[i][j];
            }
        }

        // 2번 -> 3번
        for (int i = 0; i < n / 2; i++) {
            for (int j = m / 2; j < m; j++) {
                temp[i + n / 2][j] = board[i][j];
            }
        }

        // 3번 -> 4번
        for (int i = n / 2; i < n; i++){
            for (int j = m / 2; j < m; j++){
                temp[i][j - m / 2] = board[i][j];
            }
        }

        // 4번 -> 1번
        for (int i = n / 2; i < n; i++){
            for (int j = 0; j < m/ 2; j++){
                temp[i - n / 2][j] = board[i][j];
            }
        }

        board = temp;
    }

    static void flipUpDown() {
        int n = board.length;
        int m = board[0].length;

        int[][] temp = new int[n][m];

        for (int col = 0; col < m; col++) {
            for (int row = 0; row < n; row++) {
                temp[n - row - 1][col] = board[row][col];
            }
        }

        board = temp;
    }

    static void flipLeftRight() {
        int n = board.length;
        int m = board[0].length;

        int[][] temp = new int[n][m];

        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                temp[row][m - col - 1] = board[row][col];
            }
        }

        board = temp;
    }

    static void rotate() {
        int n = board.length;
        int m = board[0].length;

        int[][] temp = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                temp[j][n - i - 1] = board[i][j];
            }
        }

        board = temp;
    }

    static void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        r = scan.nextInt();

        board = new int[n][m];
        controls = new int[r];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scan.nextInt();
            }
        }

        for (int i = 0; i < r; i++) {
            controls[i] = scan.nextInt();
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

