import java.util.*;
import java.io.*;

public class Main {

    static FastReader scan = new FastReader();

    static int[][] a;
    static int r, c, k;

    static class Pair implements Comparable<Pair> {
        int number;
        int count;

        Pair(int number, int count) {
            this.number = number;
            this.count = count;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.count != o.count) return Integer.compare(this.count, o.count);
            return Integer.compare(this.number, o.number);
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "number=" + number +
                    ", count=" + count +
                    '}';
        }
    }

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    static void printBoard() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                sb.append(a[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static int solve() {
        int time = 0;

        while (time <= 100) {
            if (isValidRange(r, c) && a[r][c] == k) {
                break;
            }

            time++;

            if (a.length >= a[0].length) {
                operation();
            } else {
                // 배열을 돌린 후 r 연산을 수행함
                rotate();
                rotate();
                rotate();
                operation();
                rotate();
            }
        }

        return time > 100 ? -1 : time;
    }

    static boolean isValidRange(int x, int y) {
        return 0 <= x && x < a.length && 0 <= y && y < a[0].length;
    }

    static void operation() {
        int maxRow = -1;

        for (int i = 0; i < a.length; i++) {
            Map<Integer, Integer> counter = new HashMap<>();

            for (int j = 0; j < a[i].length; j++) {
                int number = a[i][j];

                // 수를 정렬할 때, 0은 무시해야 한다.
                if (number == 0) continue;
                counter.put(number, counter.getOrDefault(number, 0) + 1);
            }

            List<Pair> current = new ArrayList<>();
            List<Integer> temp = new ArrayList<>();

            for (int number : counter.keySet()) {
                current.add(new Pair(number, counter.get(number)));
            }

            Collections.sort(current);

            for (Pair c : current) {
                temp.add(c.number);
                temp.add(c.count);
            }

            int rowSize = Math.min(current.size() * 2, 100);
            int[] result = new int[rowSize];

            for (int k = 0; k < Math.min(100, current.size() * 2); k++) {
                result[k] = temp.get(k);
            }

            a[i] = result;
            maxRow = Math.max(maxRow, a[i].length);
        }


        // 가장 큰 행 기준으로 크기가 변함
        for (int i = 0; i < a.length; i++) {
            int[] temp = new int[maxRow];

            for (int j = 0; j < a[i].length; j++) {
                temp[j] = a[i][j];
            }

            for (int j = a[i].length; j < maxRow; j++) {
                temp[j] = 0;
            }

            a[i] = temp;
        }
    }

    static void rotate() {
        int rSize = a.length;
        int cSize = a[0].length;

        int[][] temp = new int[cSize][rSize];

        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                temp[j][rSize - i - 1] = a[i][j];
            }
        }

        a = temp;
    }

    static void init() {
        r = scan.nextInt() - 1;
        c = scan.nextInt() - 1;
        k = scan.nextInt();

        a = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                a[i][j] = scan.nextInt();
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
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return st.nextToken();
        }
    }
}
