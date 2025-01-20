class Solution {
    // (x, y) => (y, n - x - 1)
    // (x, y) -> (y, x) -> (y, n - x - 1)

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // (x, y) -> (y, x): Transpose the matrix
        for (int x = 0; x < n; x++) {
            for (int y = x + 1; y < n; y++) {
                int temp = matrix[x][y];
                matrix[x][y] = matrix[y][x];
                matrix[y][x] = temp;
            }
        }

        // Perform horizontal reflection for each row
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n / 2; y++) { // Traverse only half of each row
                int temp = matrix[x][y];
                matrix[x][y] = matrix[x][n - y - 1];
                matrix[x][n - y - 1] = temp;
            }
        }
    }
}
