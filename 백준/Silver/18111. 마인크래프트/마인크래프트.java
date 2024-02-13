import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int n, m, b;
    static int[][] board;


    public static void main(String[] args) {
        input();
        solve();
    }

    public static void solve() {
        int minTime = Integer.MAX_VALUE;
        int maxHeight = -1;

        // 현재 땅의 높이 height 에 대해서
        for (int height = 0; height <= 256; height++) {
            int currentTime = findTime(height, b);

            if (currentTime <= minTime) {
                if (maxHeight < height) {
                    maxHeight = height;
                    minTime = currentTime;
                }
            }
        }

        System.out.println(minTime + " " + maxHeight);
    }

    // 땅을 height 높이로 고르는데 필요한 최소 시간을 구한다.
    public static int findTime(int height, int blockCount) {
        int time = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] > height) { // 블록 제거
                    int removedBlock = board[i][j] - height;
                    time += 2 * removedBlock;
                    blockCount += removedBlock;
                } else if (board[i][j] < height) {
                    int addedBlock = height - board[i][j];
                    blockCount -= addedBlock;
                    time += addedBlock;
                }
            }
        }

        if (blockCount < 0) {
            time = Integer.MAX_VALUE;
        }

        return time;
    }

    public static void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        b = scan.nextInt();
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = scan.nextInt();
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
