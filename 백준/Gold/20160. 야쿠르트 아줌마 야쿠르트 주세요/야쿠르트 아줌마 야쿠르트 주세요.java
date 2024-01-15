import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Queue;

public class Main {
    static final int VERTEX_MAX = 10001;

    static int v, e;

    static List<Edge>[] adj;
    static int[] yogurtPath = new int[10];
    static int start;

    static class Edge{
        int src;
        int dest;
        int cost;

        public Edge(int src, int dest, int cost){
            this.src = src;
            this.dest = dest;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "(u: " + src + ", v: " + dest + "), cost: " + cost;
        }
    }

    static class State{
        public int en;
        public long dist;

        public State(int en, long dist){
            this.en = en;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solve());
    }

    public static int solve(){
        // 각 지점들에서의 최단거리 배열
        long[][] dists = new long[v + 1][v + 1];

        // computed[i] := i번 정점에 대해 다익스트라가 이미 계산됨
        boolean[] computed = new boolean[v + 1];

        // 내가 도착할 수 있는 시간을 구한다.
        long[] myDist = dijkstra(start);
        computed[start] = true;
        dists[start] = myDist;

        // 아줌마가 도착할 수 있는 시간을 구한다.
        long[] yogurtTime = new long[10];
        int current = yogurtPath[0];
        yogurtTime[0] = 0;

        for (int i = 1; i < 10; i++){
            int next = yogurtPath[i];

            if (!computed[current]) { // 아직 거리를 안 구했다면?
                dists[current] = dijkstra(current);
                computed[current] = true;
            }

            // i번째 지점에서 (i + 1) 번쨰 지점으로 이동 가능한 경로가 없다면 i + 2 번으로 이동한다.
            // 이동 가능한 경로가 있다면 업데이트
            if (dists[current][next] != Long.MAX_VALUE){
                yogurtTime[i] = yogurtTime[i - 1] + dists[current][next];
                current = next;
            } else {  // 없다면 i + 2로 이동한다.
                yogurtTime[i] = -1; // 도달할 수 없음을 표시
            }
        }

        int answer = VERTEX_MAX;

        // TODO: 내가 각 정점에 도달하는 시간과 아줌마가 도착하는 시간들을 비교한다.
        for (int i = 0; i < 10; i++){
            // 아줌마의 시간 = yogurtTime
            // 현재 아줌마의 위치에서 내 시간
            if (myDist[yogurtPath[i]] <= yogurtTime[i]){
                answer = Math.min(answer, yogurtPath[i]);
            }
        }

        return answer == VERTEX_MAX ? -1 : answer;
    }

    static public long[] dijkstra(int st){
        long[] dist = new long[v + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[st] = 0;

        Queue<State> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o.dist));
        pq.offer(new State(st, 0));

        while (!pq.isEmpty()){
            State current = pq.poll();

            if (dist[current.en] < current.dist){
                continue;
            }

            for (Edge edge: adj[current.en]){
                long tempDist = current.dist + edge.cost;

                if (tempDist < dist[edge.dest]){
                    dist[edge.dest] = tempDist;
                    pq.offer(new State(edge.dest, tempDist));
                }
            }
        }

        return dist;
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        OneLineParser lineParser = new OneLineParser(br.readLine());
        v = lineParser.nextInt();
        e = lineParser.nextInt();

        adj = new ArrayList[v + 1];
        for (int i = 1; i  <= v; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < e; i++){
            lineParser.setNewLine(br.readLine());
            int u = lineParser.nextInt();
            int v = lineParser.nextInt();
            int w = lineParser.nextInt();

            adj[u].add(new Edge(u, v, w));
            adj[v].add(new Edge(v, u, w));
        }

        lineParser.setNewLine(br.readLine());

        for (int i = 0; i < 10; i++){
            yogurtPath[i] = lineParser.nextInt();
        }

        start = Integer.parseInt(br.readLine());
    }

    public static class OneLineParser{
        private StringTokenizer st;

        public OneLineParser(String line){
            st = new StringTokenizer(line);
        }

        public int nextInt(){
            return Integer.parseInt(st.nextToken());
        }

        public long nextLong(StringTokenizer st) {
            return Long.parseLong(st.nextToken());
        }

        public void setNewLine(String line){
            st = new StringTokenizer(line);
        }
    }
}
