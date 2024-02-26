import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static FastReader scan = new FastReader();
    static int n, m;
    static char[][] maze;

    static class State {
        int x;
        int y;
        int dist;
        int keys;

        State(int x, int y, int dist, int keys) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.keys = keys;
        }
    }

    static int startX, startY;

    public static void main(String[] args) {
        input();
        System.out.println(bfs());
    }

    static int bfs() { // 가중치 없는 최단거리 -> bfs
        int answer = Integer.MAX_VALUE;

        int[] DX = {-1, 1, 0, 0};
        int[] DY = {0, 0, -1, 1};
        boolean[][][] visited = new boolean[n][m][1 << 6];

        Deque<State> queue = new LinkedList<>();

        queue.offer(new State(startX, startY, 0, 0));
        visited[startX][startY][0] = true;

        while (!queue.isEmpty()) {
            State current = queue.poll();

            int x = current.x;
            int y = current.y;
            int keys = current.keys;
            int dist = current.dist;

            if (maze[x][y] == '1') {
                answer = Math.min(answer, current.dist);
                continue;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + DX[dir];
                int ny = y + DY[dir];

                if (!isValidRange(nx, ny) || maze[nx][ny] == '#' || visited[nx][ny][keys]) {
                    continue;
                }

                int nextKeys = keys;

                if ('A' <= maze[nx][ny] && maze[nx][ny] <= 'F') {
                    if ((keys & (1 << (maze[nx][ny] - 'A'))) == 0) {
                        continue;
                    }
                }

                if ('a' <= maze[nx][ny] && maze[nx][ny] <= 'f') {
                    nextKeys |= (1 << (maze[nx][ny] - 'a'));
                }

                visited[nx][ny][nextKeys] = true;
                queue.offer(new State(nx, ny, dist + 1, nextKeys));
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }


    static boolean isValidRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    static void input() {
        n = scan.nextInt();
        m = scan.nextInt();
        maze = new char[n][m];

        for (int i = 0; i < n; i++) {
            maze[i] = scan.nextLine().toCharArray();

            for (int j = 0; j < m; j++) {
                if (maze[i][j] == '0') {
                    startX = i;
                    startY = j;
                }
            }
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
