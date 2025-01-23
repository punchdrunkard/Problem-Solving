class Solution {

    static final int SERVER = 1;

    record Pair(int x, int y) {
    };

    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Set<Pair> res = new HashSet<>();

        for (int i = 0; i < m; i++) {
            Set<Pair> serverInIthRow = new HashSet<>();

            for (int j = 0; j < n; j++) {
                if (grid[i][j] == SERVER) {
                    serverInIthRow.add(new Pair(i, j));
                }
            }

            if (serverInIthRow.size() > 1) {
                res.addAll(serverInIthRow);
            }
        }

        for (int i = 0; i < n; i++) {
            Set<Pair> serverInIthCol = new HashSet<>();

            for (int j = 0; j < m; j++) {
                if (grid[j][i] == SERVER) {
                    serverInIthCol.add(new Pair(j, i));
                }
            }

            if (serverInIthCol.size() > 1) {
                res.addAll(serverInIthCol);
            }
        }

        return res.size();
    }
}