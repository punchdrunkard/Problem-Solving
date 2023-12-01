import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[][] board = new int[10][10]; // 게임 판
    static int[][] blockSize = new int[][]{{0, 0}, {1, 1}, {1, 2}, {2, 1}};

    static int score = 0;

    static void initBoard() {
        for (int x = 4; x < 10; x++) {
            for (int y = 4; y < 10; y++) {
                board[x][y] = -1;
            }
        }
    }

    static int t; // 블록을 놓은 횟수
    static int[][] gameInfo; // 게임 진행 정보 ( [종류, 위치] )

    // 유효성 검사
    static boolean isValidRange(int x, int y) {
        return 0 <= x && x < 10 && 0 <= y && y < 10 && !(4 <= x && 4 <= y);
    }

    static int countBlueBoard() {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 4; j < 10; j++) {
                if (board[i][j] != 0) {
                    count++;
                }
            }
        }

        return count;
    }

    static int countGreenBoard() {
        int count = 0;

        for (int i = 4; i < 10; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) throws IOException {
        initBoard();
        input();
        solve();

        System.out.println(score);
        System.out.println(countBlueBoard() + countGreenBoard());
    }

    static void solve() {
        for (int i = 1; i <= t; i++) {
            int[] info = gameInfo[i - 1];

            moveBlock(i, info[0], info[1], info[2]);
            removeFullLines();
            processSpecialArea();
        }
    }

    static void processSpecialArea() {
        int specialRow = countSpecialRow(); // 초

        for (int j = 0; j < specialRow; j++) {
            moveRowDown(9);
        }

        int specialCol = countSpecialCol(); // 파

        for (int j = 0; j < specialCol; j++) {
            moveColRight(9);
        }
    }

    static int countSpecialRow() {
        int count = 0;

        for (int r = 4; r <= 5; r++) {
            for (int c = 0; c < 4; c++) {
                if (board[r][c] != 0) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    static int countSpecialCol() {
        int count = 0;

        for (int c = 4; c <= 5; c++) {
            for (int r = 0; r < 4; r++) {
                if (board[r][c] != 0) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    // 사라지고 점수를 얻음
    static void removeFullLines() {
        removeFullGreenLines();
        removeFullBlueLines();
    }

    static void removeFullBlueLines() {
        for (int c = 6; c < 10; c++) {
            boolean canBombCol = true;

            for (int r = 0; r < 4; r++) {
                if (board[r][c] == 0) {
                    canBombCol = false;
                    break;
                }
            }

            if (canBombCol) {
                moveColRight(c);
                score++;
            }
        }
    }

    static void removeFullGreenLines() {
        for (int r = 6; r < 10; r++) {
            boolean canBombRow = true;

            for (int c = 0; c < 4; c++) {
                if (board[r][c] == 0) {
                    canBombRow = false;
                    break;
                }
            }

            if (canBombRow) {
                moveRowDown(r);
                score++;
            }
        }
    }

    // r행 위에 있는 행들을 한 칸씩 아래로 이동시킨다.
    static void moveRowDown(int r) {
        for (int moveRow = r; moveRow >= 4; moveRow--) {
            for (int c = 0; c < 4; c++) {
                board[moveRow][c] = board[moveRow - 1][c];
            }
        }
    }

    // c행 왼쪽에 있는 열들을 한 칸씩 오른쪽으로 이동시킨다.
    static void moveColRight(int c) {
        for (int moveCol = c; moveCol >= 4; moveCol--) {
            for (int r = 0; r < 4; r++) {
                board[r][moveCol] = board[r][moveCol - 1];
            }
        }
    }

    static void moveBlock(int index, int type, int x, int y) {
        int[][] temp = Arrays.stream(board).map(el -> el.clone()).toArray($ -> board.clone());
        int[] size = blockSize[type];

        // 끝 점
        int endX = x + size[0] - 1; // X 방향으로 이동 가능한 마지막 위치
        int endY = y + size[1] - 1; // Y 방향으로 이동 가능한 마지막 위치

        // green으로 이동 (X 방향 이동)
        while (canMove(endX + 1, y, size[0], size[1])) {
            endX++;
        }
        placeBlock(temp, index, endX, y, size[0], size[1]);

        // blue로 이동 (Y 방향이동)
        while (canMove(x, endY + 1, size[0], size[1])) {
            endY++;
        }
        placeBlock(temp, index, x, endY, size[0], size[1]);

        board = temp;
    }

    // (x, y) 부터 블럭을 배치한다.
    static void placeBlock(int[][] temp, int index, int x, int y, int height, int width) {
        for (int i = x; i < x + height; i++) {
            for (int j = y; j < y + width; j++) {
                temp[i][j] = index;
            }
        }
    }

    static void printBoard(int[][] arr) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (arr[i][j] == -1) System.out.print("  ");
                else System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    static boolean canMove(int x, int y, int height, int width) {
        if (!isValidRange(x, y)) return false;

        for (int i = x; i < x + height; i++) {
            for (int j = y; j < y + width; j++) {
                if (!isValidRange(i, j)) return false;
                if (board[i][j] != 0) return false;
            }
        }

        return true; // 이동 가능한 경우
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        t = Integer.parseInt(br.readLine());
        gameInfo = new int[t][];

        for (int i = 0; i < t; i++) {
            gameInfo[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

    }
}
