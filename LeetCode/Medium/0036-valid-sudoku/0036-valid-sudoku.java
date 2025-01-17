class Solution {
    public boolean isValidSudoku(char[][] board) {
        Set<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    if (!seen.add(generateKey(board[i][j], "row", i)) ||
                        !seen.add(generateKey(board[i][j], "col", j)) ||
                        !seen.add(generateKey(board[i][j], "block", i / 3, j / 3))) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private String generateKey(char value, String type, int... indices) {
        StringBuilder sb = new StringBuilder();
        sb.append(value).append(" in ").append(type);
        for (int index : indices) {
            sb.append(" ").append(index);
        }
        return sb.toString();
    }
}