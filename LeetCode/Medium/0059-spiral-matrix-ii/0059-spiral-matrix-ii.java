class Solution {

    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {1, 0, -1, 0};

    public int[][] generateMatrix(int n) {
        
        int[][] matrix = new int[n][n];

        int num = 1;   
        int cx = 0;
        int cy = 0;
        int cd = 0;
        matrix[cx][cy] = num;
        num++;
        
        while (num <= n * n) {
            int nx = cx + DX[cd];
            int ny = cy + DY[cd];
            int nd = cd;

            if (isOutOfBound(nx, ny, n) || matrix[nx][ny] != 0) {
                nd = (cd + 1) % 4;
                nx = cx + DX[nd];
                ny = cy + DY[nd];
            }

            cx = nx;
            cy = ny;
            cd = nd;
            matrix[cx][cy] = num;
            num++;
        }

        return matrix;
    }

    boolean isOutOfBound(int x, int y, int limit) {
        return x < 0 || x >= limit || y < 0 || y >= limit;
    }
}