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

    static int n, m; // 부완의 수, 관계의 수
    static int[] indegree;

    static Set<Integer>[] reverse;

    public static void main(String[] args) {
        input();
        System.out.println(solve());
    }


    public static int solve() {
        Deque<Integer> q = new LinkedList<>();

        int count = 0;

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            count++;
            int current = q.poll();

            for (int next : reverse[current]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        return count;
    }

    public static void input() {
        n = scan.nextInt();
        indegree = new int[n + 1];

        // 입력에서 동일한 관계가 여러 번 주어질 수 있음!
        reverse = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            reverse[i] = new HashSet<>();
        }

        m = scan.nextInt();

        for (int i = 0; i < m; i++) {
            int u = scan.nextInt();
            int v = scan.nextInt();

            if (reverse[v].add(u)) { // 해당 원소가 set에 존재하지 않을 경우
                indegree[u]++;
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