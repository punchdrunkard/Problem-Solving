import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n, k;
    static List<Integer>[] adj;

    static int[] dist;

    public static void main(String[] args) {
        init();
        System.out.println(solve() ? "Small World!" : "Big World!");
    }

    static boolean solve() {
        for (int i = 1; i <= n; i++) {
            if (!bfs(i)) return false;
        }

        return true;
    }

    static boolean bfs(int st) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(dist, -1);

        dist[st] = 0;
        q.offer(st);

        while (!q.isEmpty()) {
            int current = q.poll();

            for (int next : adj[current]) {
                if (dist[next] != -1) {
                    continue;
                }

                int nextDist = dist[current] + 1;
                if (nextDist > 6) {
                    return false;
                }

                dist[next] = nextDist;
                q.offer(next);
            }
        }

        // 모든 사람이 연결되지 않는 경우
        for (int i = 1; i <= n; i++) {
            if (dist[i] == -1) return false;
        }

        return true;
    }

    static void init() {
        n = scan.nextInt();
        k = scan.nextInt();
        adj = new ArrayList[n + 1];
        dist = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < k; i++) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            adj[a].add(b);
            adj[b].add(a);
        }
    }


    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public int nextInt() {
            return Integer.parseInt(next());
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
