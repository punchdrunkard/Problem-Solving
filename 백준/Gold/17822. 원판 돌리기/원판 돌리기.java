import java.io.*;
import java.util.*;

public class Main {
    static FastReader scan = new FastReader();
    static final int CW = 0;
    static final int CCW = 1;

    static int n, m, t;
    static int[][] flatters;

    static class Pair {
        int x, y; // 플래터 인덱스,  내부 인덱스

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        init();
        System.out.println(solve());
    }

    static int solve() {
        for (int i = 0; i < t; i++) {
            int x = scan.nextInt();
            int d = scan.nextInt();
            int k = scan.nextInt();

            int rotateCount = k % m;

            for (int j = 0; j <= n; j += x) {
                if (j == 0) continue;

                for (int r = 0; r < rotateCount; r++) {
                    rotate(j, d);
                }
            }

            // 먼저 다른 플래터에 대해서 인접한 것들을 모두 찾고
            Map<Integer, List<Pair>> sameValueMap = findSameInOtherFlatters();

            // 한 플래터 내부에서 찾아서 지운 후
            boolean erasedInOneFlatter = false;

            for (int j = 1; j <= n; j++) {
                erasedInOneFlatter |= eraseInOneFlatter(j);
            }

            boolean erasedInOtherFlatter = false;

            // 다른 플래터 찾아놓은것들을 지운다.
            for (int key : sameValueMap.keySet()) {
                if (key == 0) continue;

                for (Pair pair : sameValueMap.get(key)) {
                    flatters[pair.x][pair.y] = 0;
                    erasedInOtherFlatter = true;
                }
            }

            if (!erasedInOneFlatter && !erasedInOtherFlatter) {
                maintainFlatter();
            }
        }

        return getTotalSum();
    }

    static void maintainFlatter() {
        // 1. 원판에 적힌 수의 평균을 구한다.
        int count = 0;
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (flatters[i][j] == 0) continue; // 수가 적혀있지 않은 경우
                count++;
                sum += flatters[i][j];
            }
        }

        if (count == 0) return;

        double avg = (double) sum / count;

        // 2. 평균 보다 큰 수에서 1을 빼고, 작은 수에는 1을 더한다.
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                if (flatters[i][j] == 0) continue; // 수가 적혀있지 않은 경우

                if (flatters[i][j] > avg) {
                    flatters[i][j]--;
                } else if (flatters[i][j] < avg) {
                    flatters[i][j]++;
                }
            }
        }
    }

    static Map<Integer, List<Pair>> findSameInOtherFlatters() {
        Map<Integer, List<Pair>> sameValueMap = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            // 이전거랑 같은 위치의 그거 찾기
            if (i != 1) {
                for (int j = 0; j < m; j++) {
                    if (flatters[i][j] == flatters[i - 1][j]) {
                        sameValueMap.computeIfAbsent(flatters[i][j], k -> new ArrayList<>()).add(new Pair(i - 1, j));
                        sameValueMap.computeIfAbsent(flatters[i][j], k -> new ArrayList<>()).add(new Pair(i, j));
                    }
                }
            }

            if (i != n) {
                for (int j = 0; j < m; j++) {
                    if (flatters[i][j] == flatters[i + 1][j]) {
                        sameValueMap.computeIfAbsent(flatters[i][j], k -> new ArrayList<>()).add(new Pair(i + 1, j));
                        sameValueMap.computeIfAbsent(flatters[i][j], k -> new ArrayList<>()).add(new Pair(i, j));
                    }
                }
            }
        }

        return sameValueMap;
    }

    // idx 번쨰 원판에서 인접하면서 수가 같은 것을 모두 찾기
    static boolean eraseInOneFlatter(int idx) {
        boolean erased = false;

        Map<Integer, Set<Integer>> sameValueMap = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int prev = i == 0 ? m - 1 : i - 1;
            int next = i == m - 1 ? 0 : i + 1;

            int prevValue = flatters[idx][prev];
            int nextValue = flatters[idx][next];
            int currentValue = flatters[idx][i];

            if (prevValue == currentValue) {
                sameValueMap.computeIfAbsent(currentValue, k -> new HashSet<>()).add(prev);
                sameValueMap.computeIfAbsent(currentValue, k -> new HashSet<>()).add(i);
            }

            if (currentValue == nextValue) {
                sameValueMap.computeIfAbsent(currentValue, k -> new HashSet<>()).add(next);
                sameValueMap.computeIfAbsent(currentValue, k -> new HashSet<>()).add(i);
            }
        }


        for (int i : sameValueMap.keySet()) {
            if (i == 0) continue;

            for (int j : sameValueMap.get(i)) {
                flatters[idx][j] = 0;
                erased = true;
            }
        }

        return erased;
    }


    // 번호가 idx인 원판을 dir 방향으로 k 칸 회전시킨다.
    static void rotate(int idx, int dir) {
        if (dir == CW) { // 시계
            int temp = flatters[idx][m - 1];

            for (int i = m - 1; i >= 1; i--) {
                flatters[idx][i] = flatters[idx][i - 1];
            }

            flatters[idx][0] = temp;
        } else { // 반시계
            int temp = flatters[idx][0];

            for (int i = 0; i < m - 1; i++) {
                flatters[idx][i] = flatters[idx][i + 1];
            }

            flatters[idx][m - 1] = temp;
        }
    }

    static int getTotalSum() {
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                sum += flatters[i][j];
            }
        }

        return sum;
    }

    static void print2DArray(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < arr.length; i++) {
            sb.append("[ ");

            for (int j = 0; j < m; j++) {
                sb.append(arr[i][j]).append(' ');
            }

            sb.append("]\n");
        }

        System.out.println(sb);
    }

    static void init() {
        n = scan.nextInt();
        m = scan.nextInt();
        t = scan.nextInt();

        flatters = new int[n + 1][m];

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                flatters[i][j] = scan.nextInt();
            }
        }
    }

    static class FastReader {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public String next() {
            try {
                while (st == null || !st.hasMoreElements()) {
                    st = new StringTokenizer(br.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return st.nextToken();
        }
    }
}
