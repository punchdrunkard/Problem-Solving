import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n;
    // 인접해 있다 = 두 마을 사이에 직접 잇는 길이 있다 = 부모 자식 관계 이다.
    static List<Integer>[] adj;
    static int[] costs;

    // memo[i][0] := i번 정점을 루트로 하는 서브트리에서, i번 마을을 선택했을 때, 주민 수의 합
    // memo[i][1] := i번 정점을 루트로 하는 서브트리에서, i번 마을을 선택하지 않았을 때, 주민 수의 합
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static int solve(){
        memo = new int[n + 1][2];
        dfs(1, -1); // 1번 노드를 루트라고 가정
        return Math.max(memo[1][0], memo[1][1]);
    }

    public static void dfs(int node, int par){
        // 자기 자신을 선택한 경우
        memo[node][1] = costs[node];

        // 자기 자신의 자식들에 대해서
        for (int child: adj[node]) {
            if (child == par) continue;
            dfs(child, node);

            memo[node][1] += memo[child][0];
            memo[node][0] += Math.max(memo[child][0], memo[child][1]);
        }
    }

    public static void input() throws IOException {
        n = Integer.parseInt(br.readLine());

        costs = new int[n + 1];
        adj = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++){
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
    }
}
