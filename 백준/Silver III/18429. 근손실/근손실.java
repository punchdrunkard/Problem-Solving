import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int n, k;
    static int[] kit;

    static List<List<Integer>> sequences = new ArrayList<>(); // 모든 키트에 대한 경우의 수
    static boolean[] visited;

    // brute force!
    public static void main(String[] args) {
        input();
        dfs(new LinkedList<>(), visited); // 1. 모든 키트에 대한 경우의 수를 구하기
        System.out.println(countAnswer());
    }

    public static int countAnswer(){
        // 가능한 갯수 세기
        int count = 0;

        for (var sequence : sequences) {
            // 각 순서의 중량 계산하기
            int weight = 500;
            boolean flag = true;

            for (int kitIdx: sequence) {
                weight -= k;
                weight += kit[kitIdx];

                if (weight < 500) {
                    flag = false;
                    break;
                }
            }

            if (flag){
                count++;
            }
        }

        return count;
    }

    public static void dfs(List<Integer> current, boolean[] visited){
        if (current.size() == n) {
            sequences.add(new LinkedList<>(current));
            return;
        }

        for (int i = 0; i < n; i++){
            if (visited[i]){
                continue;
            }

            current.add(i);
            visited[i] = true;
            dfs(current, visited);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    public static void input() {
        n = scan.nextInt();
        k = scan.nextInt();
        kit = new int[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i++){
            kit[i] = scan.nextInt();
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public FastReader(String s) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(new File(s)));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }
}