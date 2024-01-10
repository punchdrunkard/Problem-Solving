import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static int n, r, q;
    static List<Integer>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        input();

        int[] memo = new int[n + 1];
        dfs(r, -1, memo);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++){
            int u = Integer.parseInt(br.readLine());
            sb.append(memo[u]).append("\n");
        }

        System.out.print(sb);
    }
    
    public static void dfs(int node, int par, int[] memo){
        memo[node] = 1;

        for (int child: adj[node]){
            if (child == par) continue;
            dfs(child, node, memo);
            memo[node] += memo[child];
        }
    }


    public static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = nextInt(st);
        r = nextInt(st);
        q = nextInt(st);

        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++){
            st = new StringTokenizer(br.readLine());
            int u = nextInt(st), v = nextInt(st);
            adj[u].add(v);
            adj[v].add(u);
        }
    }

    public static int nextInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }
}
