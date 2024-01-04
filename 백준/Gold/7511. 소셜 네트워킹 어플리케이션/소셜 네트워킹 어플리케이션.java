import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(bufferedReader.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("Scenario ").append(tc).append(":").append('\n');

            int n = Integer.parseInt(bufferedReader.readLine());
            parent = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }

            int k = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                union(a, b);
            }

            int m = Integer.parseInt(bufferedReader.readLine());

            for (int i = 0; i < m; i++){
                StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sb.append(find(a) == find(b) ? 1 : 0).append('\n');
            }

            sb.append('\n');
        }

        System.out.println(sb);
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
}
