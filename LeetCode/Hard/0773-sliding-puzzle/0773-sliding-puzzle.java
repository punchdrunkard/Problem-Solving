class Solution {

    static final int X_LEN = 2;
    static final int Y_LEN = 3;

    static final int[] DX = { -1, 1, 0, 0 };
    static final int[] DY = { 0, 0, -1, 1 };
    static final String TARGET = "123450";

    class BoardState {
        String boardStr;
        int zx, zy;

        BoardState(String boardStr, int zx, int zy) {
            this.boardStr = boardStr;
            this.zx = zx;
            this.zy = zy;
        }
    }

    public int slidingPuzzle(int[][] board) {
        return bfs(board);
    }

    int bfs(int[][] board) {

        String initialState = getFlattenArrayString(board);
        if (TARGET.equals(initialState)) {
            return 0;
        }

        Queue<BoardState> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        Pair zeroPos = getZeroPosition(board);
        q.offer(new BoardState(initialState, zeroPos.x, zeroPos.y));
        visited.add(initialState);

        int moveCount = 0;

        while (!q.isEmpty()) {
            int sz = q.size();

            for (int i = 0; i < sz; i++) {
                BoardState current = q.poll();

                if (TARGET.equals(current.boardStr)) {
                    return moveCount;
                }

                for (int dir = 0; dir < 4; dir++) {
                    int nx = current.zx + DX[dir];
                    int ny = current.zy + DY[dir];

                    if (isOutOfRange(nx, ny)) {
                        continue;
                    }

                    String nextState = swapAndGetNewState(current.boardStr, current.zx, current.zy, nx, ny);

                    if (visited.contains(nextState)) {
                        continue;
                    }

                    visited.add(nextState);
                    q.offer(new BoardState(nextState, nx, ny));
                }
            }

            moveCount++;
        }

        return -1;
    }

    String swapAndGetNewState(String state, int zx, int zy, int nx, int ny) {
        char[] chars = state.toCharArray();
        int fromIndex = zx * Y_LEN + zy;
        int toIndex = nx * Y_LEN + ny;

        char temp = chars[fromIndex];
        chars[fromIndex] = chars[toIndex];
        chars[toIndex] = temp;

        return new String(chars);
    }

    Pair getZeroPosition(int[][] arr) {
        int zx = -1;
        int zy = -1;

        for (int x = 0; x < arr.length; x++) {
            for (int y = 0; y < arr[0].length; y++) {
                if (arr[x][y] == 0) {
                    zx = x;
                    zy = y;
                    break;
                }
            }
        }

        return new Pair(zx, zy);
    }

    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= X_LEN || y < 0 || y >= Y_LEN;
    }

    String getFlattenArrayString(int[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : arr) {
            for (int num : row) {
                sb.append(num);
            }
        }
        return sb.toString();
    }

    class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}