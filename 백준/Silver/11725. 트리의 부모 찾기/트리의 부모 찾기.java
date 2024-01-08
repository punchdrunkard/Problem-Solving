import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static List<Integer>[] adj;


    public static void main(String[] args) throws IOException {
        input();
        int[] parent = solve();
        printAnswer(parent);
    }

    public static int[] solve() {
        int[] parent = new int[n + 1];
        boolean[] visited = new boolean[n + 1];

        dfs(1, parent, visited);
        return parent;
    }

    public static void printAnswer(int[] answer) {
        StringBuilder sb = new StringBuilder();

        for (int i = 2; i < answer.length; i++) {
            sb.append(answer[i]).append('\n');
        }

        System.out.print(sb);
    }

    // tree를 dfs로 탐색하면서 정답을 기록한다.
    public static void dfs(int node, int[] parent, boolean[] visited) {
        // leaf node인 경우
        if (adj[node].isEmpty()) {
            return;
        }

        // 이미 방문했다면 return
        if (visited[node]) {
            return;
        }

        // 해당 노드를 방문한다.
        visited[node] = true;

        // 다음 노드로 탐색
        for (int child : adj[node]) {
            if (!visited[child]) {
                parent[child] = node;
                dfs(child, parent, visited);
            }
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }
    }
}
