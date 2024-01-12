import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] adj;

    static int[] parent;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());

    }

    public static String solve() {
        long d_count = 0;
        long g_count = 0;

        // 1번이 루트라고 가정
        bfs();

        // 트리의 각 노드를을 순회하면서 갯수 세기
        for (int node = 1; node <= n; node++) {
            g_count += threeCombination(adj[node].size());

            if (node != 1) {
                long currentCount = adj[node].size();
                long parentCount = adj[parent[node]].size(); // 부모와 연결된 정점의 갯수

                if (parent[node] != node) {
                    d_count += (currentCount - 1) * (parentCount - 1);
                }
            }
        }

        return getTreeKind(d_count, g_count);
    }

    public static void bfs() {
        parent[1] = -1;

        Queue<Integer> q = new ArrayDeque();
        boolean[] visited = new boolean[n + 1];

        q.offer(1);
        visited[1] = true;

        while (!q.isEmpty()){
            int current = q.poll();

            for (int child: adj[current]) {
                if (visited[child]) continue;
                parent[child] = current;
                q.offer(child);
                visited[child] = true;
            }
        }
    }

    // nC3
    public static long threeCombination(long n) {
        if (n < 3) return 0;
        return (n) * (n - 1) * (n - 2) / (3 * 2 * 1);
    }

    public static String getTreeKind(long d_count, long g_count) {
        if (d_count == g_count * 3) return "DUDUDUNGA";
        else if (d_count > g_count * 3) return "D";
        else return "G";
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        parent = new int[n + 1];

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = nextInt(st);
            int b = nextInt(st);
            adj[a].add(b);
            adj[b].add(a);
        }
    }

    public static int nextInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
