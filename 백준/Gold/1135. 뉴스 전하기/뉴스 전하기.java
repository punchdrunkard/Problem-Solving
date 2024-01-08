import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int n;
    static int[] parent;
    static List<Integer>[] adj;

    public static void main(String[] args) throws IOException {
        input();
        makeTree();
        System.out.println(calculateMinTime(0));
    }

    // findMinTime(int node) : node를 root로 하는 서브트리에서 걸리는 시간의 최솟값
    public static int calculateMinTime(int node){
        // 리프 노드인 경우
        if (adj[node].isEmpty()) {
            return 0;
        }
        
        // 리프노드가 아닌 경우,
        // 해당 노드의 자식 중 전파 시간이 가장 오래걸리는 자식부터 탐색한다.
        List<Integer> childTimes = new ArrayList<>();

        for (int i = 0; i < adj[node].size(); i++) {
            childTimes.add(calculateMinTime(adj[node].get(i)) + 1);
        }

        childTimes.sort((a, b) -> b - a);

        // 해당 노드에서 걸리는 시간을 구한다.
        int maxTime = -1;

        for (int i = 0; i < childTimes.size(); i++){
            maxTime = Math.max(maxTime, childTimes.get(i) + i); // 소요 시간 + 전파 시키는 시간
        }

        return maxTime;
    }

    public static void makeTree() {
        for (int node = 1; node < n; node++){
            adj[parent[node]].add(node);
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++){
            adj[i] = new ArrayList<>();
        }

        parent = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}
