import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();

    static int[] DX = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] DY = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int n, m, k;
    static int[][] a; // 각 칸에 추가되는 양분의 양

    static int[][] nutrients; // 현재 양분의 양
    static List<Tree>[][] trees; // 각 칸의 나무 상태

    static class Tree implements Comparable<Tree> {
        int age;
        boolean alive;

        Tree(int age) {
            this.age = age;
            alive = true;
        }


        @Override
        public int compareTo(Tree o) {
            return Integer.compare(this.age, o.age);
        }
    }

    public static void main(String[] args) {
        init();
        simulate();
        System.out.println(getAliveTreeCount());

    }

    static int getAliveTreeCount() {
        int count = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Tree tree : trees[i][j]) {
                    if (tree.alive) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    static void simulate() {
        for (int year = 0; year < k; year++) {
            spring();
            summer();
            autumn();
            winter();
        }
    }

    static void winter() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                nutrients[i][j] += a[i][j];
            }
        }
    }

    static void autumn() {
        List<Tree>[][] temp = new ArrayList[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                temp[i][j] = new ArrayList<>();

                for (Tree tree : trees[i][j]) {
                    temp[i][j].add(tree);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (Tree tree : trees[i][j]) {
                    if (tree.age % 5 == 0) { // 번식하는 나무는 나이가 5의 배수
                        for (int dir = 0; dir < 8; dir++) {
                            int nx = i + DX[dir];
                            int ny = j + DY[dir];

                            if (!isValidRange(nx, ny)) {
                                continue;
                            }

                            temp[nx][ny].add(new Tree(1));
                        }
                    }
                }

            }
        }

        trees = temp;
    }

    static void spring() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                Collections.sort(trees[i][j]);

                for (Tree tree : trees[i][j]) {
                    if (!tree.alive) {
                        continue;
                    }

                    // 나무가 자신의 나이만큼 양분을 먹고, 나이가 증가
                    // 양분을 먹을 수 없다면 죽는다.
                    if (tree.age > nutrients[i][j]) {
                        tree.alive = false;
                    } else {
                        nutrients[i][j] -= tree.age;
                        tree.age += 1;
                    }
                }
            }
        }
    }

    static void summer() {
        // 봄에 죽은 나무가 양분으로 변한다.
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                List<Tree> aliveTrees = new ArrayList<>();

                for (Tree tree : trees[i][j]) {
                    if (tree.alive) {
                        aliveTrees.add(tree);
                    } else {
                        nutrients[i][j] += (tree.age / 2);
                    }
                }

                trees[i][j] = aliveTrees;
            }
        }
    }

    static boolean isValidRange(int x, int y) {
        return 1 <= x && x <= n && 1 <= y && y <= n;
    }

    static void printTrees() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append("[ ");
                for (Tree tree : trees[i][j]) {
                    sb.append(tree.age).append(' ');
                }
                sb.append(" ]\t");
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void printNutrient() {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                sb.append(nutrients[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        k = scan.nextInt();

        a = new int[n + 1][n + 1];
        nutrients = new int[n + 1][n + 1];
        trees = new ArrayList[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                a[i][j] = scan.nextInt();
                nutrients[i][j] = 5; // 가장 처음에 양분은 모든 칸에 5만큼 들어 있다.
                trees[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int z = scan.nextInt(); // 나무의 나이

            trees[x][y].add(new Tree(z));
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

