import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int n, k, kIndex;
    static int[] parent, nodes;

    // childrenCount[i] := i 번 노드의 자식 갯수
    static int[] childCount;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        while (true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // 입력의 마지막 줄에는 0이 두 개 주어진다.
            if (n == 0 && k == 0) {
                break;
            }

            init();
            sb.append(countCousins()).append("\n");
        }

        System.out.print(sb);
    }

    // 사촌 수 : 부모 그룹에 있는 노드들의 자식 수에서 부모 제외하기
    public static int countCousins(){
        int par = parent[kIndex]; // 부모 노드

        if (par == -1) { // 부모가 루트라면?
            return 0;
        }

        int answer = 0;

        int grand = parent[par]; // 그 조상노드

        for (int i = 0; i < n; i++) {
            // 할아버지 노드의 자식이면서 내 부모가 아니라면
            if (parent[i] == grand && i != par) {
                answer += childCount[i];
            }
        }

        return answer;
    }

    // 정보를 입력받고, 트리를 구성함
    public static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        parent = new int[n];
        nodes = new int[n];
        childCount = new int[n];

        // 노드 입력 받기
        for (int i = 0; i < n; i++) {
            nodes[i] = Integer.parseInt(st.nextToken());
            if (nodes[i] == k) {
                kIndex = i;
            }
        }

        // 부모 설정하기
        parent[0] = -1;

        // 연속된 부분으로 나눠가면서...
        int prev = nodes[0];
        int groupIndex = -1;

        for (int i = 1; i < n; i++) {
            int current = nodes[i];

            // 연속된 부분이 아님 -> 그룹이 나뉜다.
            if (current - prev != 1) {
                groupIndex++;
            }

            parent[i] = groupIndex;
            childCount[groupIndex]++;
            prev = current;
        }
    }
}
