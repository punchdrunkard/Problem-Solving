class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        
        Map<Integer, Integer> counter = new HashMap<>();
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                counter.put(grid[x][y], counter.getOrDefault(grid[x][y], 0) + 1);
            }
        }

        int a = -1; 
        int b = -1;
        for (int i = 1; i <= n * n; i++) {
            int appearNum = counter.getOrDefault(i, 0);
            if (appearNum == 0) {
                b = i;
                continue;
            }

            if (appearNum == 2) {
                a = i;
                continue;
            }

            if (a != -1 && b != -1) {
                break;
            }
        }

        return new int[]{a, b};
    }
}