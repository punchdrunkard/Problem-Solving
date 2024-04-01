import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static int n, s;
    static int[] stone;

    static int[] DX = {1, -1};

    public static void main(String[] args) {
        init();
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        q.add(s);
        visited[s] = true;

        while (!q.isEmpty()) {
            int cx = q.poll();

            for (int dir = 0; dir < 2; dir++) {
                int nx = cx + (stone[cx] * DX[dir]);

                if (isValidRange(nx) && !visited[nx]) {
                    visited[nx] = true;
                    q.offer(nx);
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) count++;
        }

        return count;
    }

    static boolean isValidRange(int x) {
        return 0 <= x && x < n;
    }


    static void init() {
        n = scan.nextInt();
        stone = new int[n];
        for (int i = 0; i < n; i++) {
            stone[i] = scan.nextInt();
        }

        s = scan.nextInt() - 1;
    }


    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreTokens()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }

    }
}
