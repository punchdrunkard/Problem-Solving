import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int f, s, g, u, d;

    public static void main(String[] args) {
        input();
        int answer = bfs();
        System.out.println(answer == -1 ? "use the stairs" : answer);
    }

    static int bfs(){
        int[] dist = new int[f + 1];
        Arrays.fill(dist, -1);

        Deque<Integer> q = new LinkedList<>();
        q.offer(s);
        dist[s] = 0;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == g) {
                break;
            }

            int[] nextFloors = new int[]{current + u, current - d};

            for (int next : nextFloors) {
                if (!isValidRange(next)) {
                    continue;
                }

                if (dist[next] != -1) {
                    continue;
                }

                dist[next] = dist[current] + 1;
                q.offer(next);
            }
        }

        return dist[g];
    }

    static boolean isValidRange(int x){
        return 1 <= x && x <= f;
    }


    public static void input() {
        f = scan.nextInt();
        s = scan.nextInt();
        g = scan.nextInt();
        u = scan.nextInt();
        d = scan.nextInt();
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