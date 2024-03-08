import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n, m;
    static List<Integer>[] adj;
    static int[] counts;
    static Deque<Integer> q = new LinkedList<>();

    public static void main(String[] args) {
        input();
        solve();
    }

    static void solve(){
        for (int i = 1; i <= n; i++){
            bfs(i);
        }

        int maxCount = -1;
        for (int i = 1; i <=n; i++){
            maxCount = Math.max(maxCount, counts[i]);
        }

        StringBuilder sb = new StringBuilder();


        for (int i = 1; i <= n; i++){
            if (counts[i] == maxCount) {
                sb.append(i).append(' ');
            }
        }

        System.out.println(sb);
    }

    static void bfs(int st){
        q.offer(st);
        boolean[] visited = new boolean[n + 1];
        visited[st] = true;

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : adj[current]) {
                if (visited[next]) {
                    continue;
                }

                visited[next] = true;
                q.offer(next);
                counts[next]++;
            }
        }
    }

    static void input() {
        n = scan.nextInt();
        m = scan.nextInt();

        adj = new ArrayList[n + 1];
        counts = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            adj[a].add(b);
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

