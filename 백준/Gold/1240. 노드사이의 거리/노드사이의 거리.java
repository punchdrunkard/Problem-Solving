import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static class Edge {
        int x, cost;

        public Edge(int x, int cost) {
            this.x = x;
            this.cost = cost;
        }
    }

    static int n, m;
    static List<Edge>[] adj;

    public static void main(String[] args) throws IOException {
        input();
        StringBuilder sb = new StringBuilder();

        for (int query = 0; query < m; query++) {
            st = new StringTokenizer(br.readLine());
            int s = nextInt(st);
            int e = nextInt(st);


            sb.append(dfs(s, -1, e, 0)).append("\n");
        }

        System.out.print(sb);
    }

    // 트리에서 "두 노드 사이의 경로는 유일하다"
    public static int dfs(int current, int prev, int goal, int dist) {
        if (current == goal) {
            return dist;
        }

        for (Edge child : adj[current]) {
            if (child.x == prev) continue;
            int result = dfs(child.x, current, goal, dist + child.cost);
            if (result != -1) {
                return result;
            }
        }

        return -1;
    }

    public static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        n = nextInt(st);
        m = nextInt(st);

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = nextInt(st);
            int b = nextInt(st);
            int cost = nextInt(st);

            adj[a].add(new Edge(b, cost));
            adj[b].add(new Edge(a, cost));
        }
    }

    public static int nextInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
