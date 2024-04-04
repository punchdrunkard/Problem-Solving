import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n, m;
    static PriorityQueue<Long> pq = new PriorityQueue<>(); // 최소힙

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    static long solve() {
        long score = 0;

        for (int i = 0; i < m; i++) {
            long first = pq.poll();
            long second = pq.poll();
            long sum = first + second;

            pq.offer(sum);
            pq.offer(sum);
        }

        for (long num : pq) {
            score += num;
        }

        return score;
    }

    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();

        for (int i = 0; i < n; i++) {
            pq.offer(scan.nextLong());
        }
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
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
