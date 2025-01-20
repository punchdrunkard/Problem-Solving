class Solution {

    static int[] DX = { 0, 1, 0, -1 };
    static int[] DY = { 1, 0, -1, 0 };
    static int m, n;

    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> answer = new LinkedList<>();
        m = matrix.length;
        n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];

        int cx = 0;
        int cy = 0;
        int cd = 0;
        visited[cx][cy] = true;
        answer.add(matrix[cx][cy]);

        while (answer.size() < m * n) {
            int nx = cx + DX[cd];
            int ny = cy + DY[cd];

            if (isOutOfBound(nx, ny) || visited[nx][ny]) {
                cd = (cd + 1) % 4;
                nx = cx + DX[cd];
                ny = cy + DY[cd];
            }

            cx = nx;
            cy = ny;
            visited[cx][cy] = true;
            answer.add(matrix[cx][cy]);
        }

        return answer;
    }

    boolean isOutOfBound(int x, int y) {
        return x < 0 || x >= m || y < 0 || y >= n;
    }
}