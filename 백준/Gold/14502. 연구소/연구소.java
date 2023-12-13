import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int n, m;
    static int[][] board;

    static int[] DX = {-1, 1, 0, 0};
    static int[] DY = {0, 0, -1, 1};

    public static class Point {
        int x, y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int answer = -1; // 안전 영역의 크기

    static ArrayList<Point> emptyRooms = new ArrayList<>();  // 현재 맵에 있는 빈칸들 (벽을 세울 수 있는 곳)
    static ArrayList<ArrayList<Integer>> combinations = new ArrayList<>(); // 빈칸 조합의 인덱스를 저장
    static ArrayList<Point> viruses = new ArrayList<>(); // 바이러스의 위치

    public static void main(String[] args) throws IOException {
        input();
        makeCombination(new ArrayList<>(), 0);

        for (ArrayList<Integer> combination : combinations) {
            int i1 = combination.get(0);
            int i2 = combination.get(1);
            int i3 = combination.get(2);

            answer = Math.max(answer, solve(i1, i2, i3));
        }

        System.out.println(answer);
    }

    // i1번 빈칸, i2번 빈칸, i3번 빈칸에 벽을 세웠을 때, 안전 구역의 갯수를 리턴한다.
    public static int solve(int i1, int i2, int i3){
        Point e1 = emptyRooms.get(i1);
        Point e2 = emptyRooms.get(i2);
        Point e3 = emptyRooms.get(i3);

        int[][] currentBoard = new int[n][m];

        for (int i = 0; i < n; i++){
            if (m >= 0) System.arraycopy(board[i], 0, currentBoard[i], 0, m);
        }

        // 현재 위치에 벽을 세운다.
        currentBoard[e1.x][e1.y] = 1;
        currentBoard[e2.x][e2.y] = 1;
        currentBoard[e3.x][e3.y] = 1;

        bfs(currentBoard);
        return countEmptyRooms(currentBoard);
    }

    // currentBoard 에서 바이러스에서 부터 bfs를 돌린다.
    public static void bfs(int[][] currentBoard){
        // currentBoard 에서 바이러스 부분을 bfs 돌리기
        ArrayDeque<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        for (Point virus: viruses){
            q.offer(virus);
        }

        while (!q.isEmpty()){
            Point current = q.poll();
            int cx = current.x;
            int cy = current.y;

            visited[cx][cy] = true;

            for (int dir = 0; dir < 4; dir++){
                int nx = cx + DX[dir];
                int ny = cy + DY[dir];

                if (!isValidRange(nx, ny)) continue;
                if (visited[nx][ny]) continue;
                if (currentBoard[nx][ny] != 0) continue;

                visited[nx][ny] = true;
                currentBoard[nx][ny] = 2;
                q.offer(new Point(nx, ny));
            }
        }
    }
    public static int countEmptyRooms(int[][] board){
        int count = 0;

        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (board[i][j] == 0){
                    count++;
                }
            }
        }

        return count;
    }

    public static void printArray(int[][] arr){
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isValidRange(int x, int y){
        return 0 <= x && x < n && 0 <= y && y < m;
    }

    public static void makeCombination(ArrayList<Integer> current, int index){
        if (current.size() == 3) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (int i = index; i < emptyRooms.size(); i++){
            current.add(i);
            makeCombination(current, i + 1);
            current.remove(current.size() - 1);
        }
    }

    public static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] line = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = line[0];
        m = line[1];

        board = new int[n][m];


        for (int i = 0; i < n; i++){
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            for (int j = 0; j < m; j++){
                board[i][j] = row[j];
                if (board[i][j] == 0){
                    emptyRooms.add(new Point(i, j));
                }

                if (board[i][j] == 2){
                    viruses.add(new Point(i, j));
                }

            }
        }
    }
}
