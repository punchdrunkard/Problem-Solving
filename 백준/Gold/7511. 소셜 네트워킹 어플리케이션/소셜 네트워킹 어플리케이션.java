import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int testCase = Integer.parseInt(bufferedReader.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("Scenario ").append(tc).append(":").append('\n');
            solve();
            sb.append('\n');
        }

        System.out.println(sb);
    }

    public static void solve() throws IOException {
        int n = Integer.parseInt(bufferedReader.readLine());
        parent = IntStream.range(0, n).toArray();

        int k = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < k; i++) {
            int[] edge = readPair(bufferedReader);
            union(edge[0], edge[1]);
        }

        int m = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < m; i++){
            int[] query = readPair(bufferedReader);
            sb.append(find(query[0]) == find(query[1]) ? 1 : 0).append('\n');
        }
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;
        parent[a] = b;
    }

    public static int find(int a) {
        if (parent[a] != a){
            parent[a] = find(parent[a]);
        }
        return parent[a];
    }

    private static int[] readPair(BufferedReader bufferedReader) throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        return new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
    }
}
