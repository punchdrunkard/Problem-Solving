class Solution {

    record Pair(int x, int y) {
    };

    Map<Integer, Pair> position;
    List<Set<Integer>> rowSets, colSets;
    int m, n;

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        init(mat);

        for (int i = 0; i < arr.length; i++) {
            Pair pos = position.get(arr[i]);
            rowSets.get(pos.x).remove(arr[i]);
            colSets.get(pos.y).remove(arr[i]);

            if (rowSets.get(pos.x).isEmpty() || colSets.get(pos.y).isEmpty()) {
                return i;
            }
        }

        return -1;
    }

    void init(int[][] mat) {
        position = new HashMap<>();
        rowSets = new ArrayList<>();
        colSets = new ArrayList<>();

        m = mat.length;
        n = mat[0].length;

        for (int i = 0; i < m; i++) {
            rowSets.add(new HashSet<>());
        }

        for (int i = 0; i < n; i++) {
            colSets.add(new HashSet<>());
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                position.put(mat[i][j], new Pair(i, j));
                rowSets.get(i).add(mat[i][j]);
                colSets.get(j).add(mat[i][j]);
            }
        }
    }
}