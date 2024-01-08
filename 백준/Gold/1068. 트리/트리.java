import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, root, erased;
    static List<Integer>[] adj;
    static int[] dp, parent;

    public static void main(String[] args) throws IOException {
        input();
        eraseNode(erased);

        int answer = dfs(root, -1);
        System.out.println(answer);
    }

    public static void eraseNode(int x) {
        // 해당 노드 아래의 node 를 모두 삭제
        adj[x].clear();

        // 해당 노드와 부모의 연결을 삭제
        if (parent[x] != -1) adj[parent[x]].remove(Integer.valueOf(x));
    }


    // dfs(x) : x를 루트로 하는 서브 트리의 leaf node의 갯수를 구한다.
    public static int dfs(int x, int par) {
        // 현재 노드가 지워진 노드인 경우
        if (x == erased) {
            dp[x] = 0;
            return 0;
        }

        // 종료 조건 : leaf 인 경우
        if (adj[x].isEmpty()) {
            dp[x] = 1;
            return 1;
        }

        if (dp[x] != -1) {
            return dp[x];
        }

        dp[x] = 0;

        // 그 노드 부터 탐색
        for (int child : adj[x]) {
            if (child == par) continue;
            dp[x] += dfs(child, x);
        }

        return dp[x];
    }


    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n];
        dp = new int[n];
        parent = new int[n];
        Arrays.fill(dp, -1);

        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

        // 0번 노드부터 n - 1 번 노드까지, 각 노드의 부모가 주어진다.
        // 부모가 없다면 (루트) -1 이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int node = 0; node < n; node++) {
            parent[node] = Integer.parseInt(st.nextToken());

            if (parent[node] == -1) { // 루트인 경우
                root = node;
            } else {
                adj[parent[node]].add(node);
            }
        }

        erased = Integer.parseInt(br.readLine());
    }
}
