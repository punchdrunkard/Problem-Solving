import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static List<Integer>[] adj;

    // memo[i] := i번 정점에 도달할 수 있는 양의 수
    // count[i] := i번 정점에 살고 있는 동물의 개체 수
    static long[] count, memo;
    static String[] kind; // kind[i] := i번에 살고 있는 동물의 종류

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static long solve() {
        memo = new long[n + 1];

        memo[1] = 0L;
        kind[1] = "S";

        dfs(1, -1);
        return memo[1];
    }

    public static void dfs(int current, int par) {
        memo[current] = kind[current].equals("S") ? count[current] : -count[current];

        for (int child : adj[current]) {
            if (child == par) {
                continue;
            }

            dfs(child, current);

            if (memo[child] > 0) memo[current] += memo[child];
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        kind = new String[n + 1];
        count = new long[n + 1];

        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String t = st.nextToken();
            Long a = nextLong(st);
            int p = nextInt(st);

            kind[i] = t;
            count[i] = a;

            adj[p].add(i);
            adj[i].add(p);
        }
    }

    public static int nextInt(StringTokenizer st) {
        return Integer.parseInt(st.nextToken());
    }

    public static Long nextLong(StringTokenizer st) {
        return Long.parseLong(st.nextToken());
    }
}
