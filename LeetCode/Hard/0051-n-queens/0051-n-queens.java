class Solution {

    int n;
    char[][] board;

    List<List<String>> answers = new ArrayList<>();

    static final char QUEEN = 'Q';
    static final char EMPTY = '.';

    static final int[] DX = {-1, -1};
    static final int[] DY = {-1, 1};

    public List<List<String>> solveNQueens(int _n) {
        init(_n);
        backtrack(0);

        return answers;
    }

    void backtrack(int row) {
        // 종료 조건
        if (row == n) {
            List<String> temp = new ArrayList<>();

            for (char[] r: board) {
                temp.add(new String(r));
            }

            answers.add(temp);
            return;
        }

        // board[row][col]에 퀸을 놓기
        for (int col = 0; col < n; col++) {
            if (cannotPutQueen(row, col)) {
                continue;
            }

            board[row][col] = QUEEN;
            backtrack(row + 1);
            board[row][col] = EMPTY;
        }
    }

    boolean cannotPutQueen(int cx, int cy) {
        return existQueenInCol(cy) || existQueenInDigonal(cx, cy);
    }

    boolean existQueenInCol(int cy){
        for (int x = 0; x < n; x++) {
            if (board[x][cy] == QUEEN) {
                return true;
            }
        }

        return false;
    }

    boolean existQueenInDigonal(int cx, int cy) {
        for (int dir = 0; dir < 2; dir++) {
            int nx = cx;
            int ny = cy;

            while (isValidRange(nx, ny)){
                if (board[nx][ny] == QUEEN) {
                    return true;
                }

                nx += DX[dir];
                ny += DY[dir];
            }
        }

        return false;
    }

    boolean isValidRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }

    boolean isOutOfRange(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= n;
    }

    void init(int _n) {
        n = _n;
        board = new char[n][n];

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                board[x][y] = EMPTY;
            }
        }
    }
}