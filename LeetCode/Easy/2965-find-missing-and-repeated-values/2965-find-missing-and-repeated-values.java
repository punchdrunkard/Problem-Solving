class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;

        long gridSum = calculateGridSum(grid);
        long gridSquareSum = calculateGridSquareSum(grid);

        long totalSum = calculateConsectiveSum(n * n);
        long totalSquareSum = calculateConsectiveSquareSum(n * n);
        
        // gridSum = totalSum + a - b
        // a - b = gridSum - totalSum 
        long diff = gridSum - totalSum;
        // gridSquareSum = totalSquareSum + a^2 - b^2
        long squareDiff = gridSquareSum - totalSquareSum;
        // a^2 - b^2 = gridSquareSum - totalSquareSum
        // a^2 - b^2 = (a + b) * (a - b)
        // a + b = (a^2 - b^2) / (a - b) 

        long a = ((squareDiff / diff) + diff) / 2l;
        long b = ((squareDiff / diff) - diff) / 2l;

        return new int[]{(int) a, (int) b};
    }

    long calculateConsectiveSquareSum(long n) {
        return n * (n + 1) * (2 * n + 1) / 6;
    }

    long calculateConsectiveSum(long n) {
        return n * (n + 1) / 2;
    }

    long calculateGridSum(int[][] grid) {
        long sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += grid[i][j];
            }
        }

        return sum;
    }

    long calculateGridSquareSum(int[][] grid) {
        long sum = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                sum += (grid[i][j] * grid[i][j]);
            }
        }

        return sum;
    }
}