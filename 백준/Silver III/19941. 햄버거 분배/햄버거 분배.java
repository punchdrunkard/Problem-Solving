import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();

    // 먹을 수 있는 햄버거 중, 가장 왼쪽에 있는 햄버거 선택
    // 가장 왼쪽에 있는 걸 선택해야, 뒤의 사람들이 영향을 받지 않음!
    static int n, k;
    static String status;
    static boolean[] isHamburger;

    public static void main(String[] args) {
        input();
        System.out.println(solve());
    }

    public static int solve() {
        int count = 0;

        for (int i = 0; i < isHamburger.length; i++) {
            if (status.charAt(i) != 'P') {
                continue;
            }

            // 먹을 수 있는 햄버거 후보
            int left = Math.max(0, i - k);
            int right = Math.min(n - 1, i + k);

            for (int j = left; j <= right; j++) {
                if (isHamburger[j]) {
                    count++;
                    isHamburger[j] = false;
                    break;
                }
            }
        }

        return count;
    }


    public static void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        status = scan.nextLine();
        isHamburger = new boolean[status.length()];

        for (int i = 0; i < status.length(); i++) {
            if (status.charAt(i) == 'H') {
                isHamburger[i] = true;
            } else {
                isHamburger[i] = false;
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
