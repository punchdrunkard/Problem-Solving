import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n;
    static String[] words;

    public static void main(String[] args) {
        input();
        System.out.println(solve());
    }

    static int solve() {
        int count = 0;
        int[] target = new int['Z' - 'A' + 1];

        for (int i = 0; i < words[0].length(); i++) {
            target[words[0].charAt(i) - 'A'] += 1;
        }

        for (int i = 1; i < n; i++) {
            // 현재 알파벳의 구성이 어떤 유형인지 확인해야 한다.
            int[] current = new int['Z' - 'A' + 1];

            for (int j = 0; j < words[i].length(); j++) {
                current[words[i].charAt(j) - 'A'] += 1;
            }

            // 더하거나, 빼거나, 바꿀 수 있어야 함
            int missingCount = 0;
            int flowCount = 0;

            for (int j = 0; j < 'Z' - 'A'; j++) {
                int diff = current[j] - target[j];

                if (diff > 0) {
                    flowCount += diff;
                } else if (current[j] < target[j]) {
                    missingCount -= diff;
                }
            }

            if (missingCount == 0 && flowCount == 0) { // 구성이 같은 경우
                count += 1;
            } else if (missingCount == 0 && flowCount == 1) { // 하나를 빼면 되는 경우
                count += 1;
            } else if (missingCount == 1 && flowCount == 0) { // 하나를 더하면 되는 경우
                count += 1;
            } else if (missingCount == 1 && flowCount == 1) { // 하나를 바꾸면 되는 경우
                count += 1;
            }
        }

        return count;
    }

    static void input() {
        n = scan.nextInt();
        words = new String[n];

        for (int i = 0; i < n; i++) {
            words[i] = scan.nextLine();
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
