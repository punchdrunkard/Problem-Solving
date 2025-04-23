import java.util.*;

class Solution {

    final int EMPTY = 0;
    final int FILLED = 1;
    final int[] DX = {-1, 1, 0, 0};
    final int[] DY = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        List<List<Pair>> emptySpaces = extractBlocks(game_board, EMPTY);
        List<List<Pair>> puzzlePieces = extractBlocks(table, FILLED);
        boolean[] used = new boolean[puzzlePieces.size()];

        int total = 0;
        for (List<Pair> space : emptySpaces) {
            for (int i = 0; i < puzzlePieces.size(); i++) {
                if (used[i]) continue;

                List<Pair> piece = puzzlePieces.get(i);
                boolean matched = false;
                for (int rot = 0; rot < 4; rot++) {
                    piece = rotate(piece);
                    normalize(piece);
                    if (isSameShape(space, piece)) {
                        used[i] = true;
                        total += space.size();
                        matched = true;
                        break;
                    }
                }

                if (matched) break;
            }
        }

        return total;
    }

    boolean isSameShape(List<Pair> a, List<Pair> b) {
        if (a.size() != b.size()) return false;
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).x != b.get(i).x || a.get(i).y != b.get(i).y) return false;
        }
        return true;
    }

    List<Pair> rotate(List<Pair> block) {
        int maxX = 0;
        for (Pair p : block) maxX = Math.max(maxX, p.x);
        List<Pair> result = new ArrayList<>();
        for (Pair p : block) {
            result.add(new Pair(p.y, maxX - p.x));
        }
        return result;
    }

    void normalize(List<Pair> block) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for (Pair p : block) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }
        for (Pair p : block) {
            p.x -= minX;
            p.y -= minY;
        }
        block.sort(Comparator.comparingInt((Pair p) -> p.x).thenComparingInt(p -> p.y));
    }

    List<List<Pair>> extractBlocks(int[][] board, int target) {
        int n = board.length;
        boolean[][] visited = new boolean[n][n];
        List<List<Pair>> result = new ArrayList<>();

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (!visited[x][y] && board[x][y] == target) {
                    List<Pair> shape = bfs(x, y, board, visited, target);
                    normalize(shape);
                    result.add(shape);
                }
            }
        }

        return result;
    }

    List<Pair> bfs(int startX, int startY, int[][] board, boolean[][] visited, int target) {
        int n = board.length;
        Queue<Pair> q = new LinkedList<>();
        List<Pair> result = new ArrayList<>();
        q.add(new Pair(startX, startY));
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            Pair curr = q.poll();
            result.add(curr);

            for (int dir = 0; dir < 4; dir++) {
                int nx = curr.x + DX[dir];
                int ny = curr.y + DY[dir];

                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                if (visited[nx][ny] || board[nx][ny] != target) continue;

                visited[nx][ny] = true;
                q.add(new Pair(nx, ny));
            }
        }

        return result;
    }

    class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
