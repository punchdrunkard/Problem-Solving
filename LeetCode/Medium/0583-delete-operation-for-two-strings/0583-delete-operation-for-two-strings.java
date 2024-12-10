class Solution {
    String word1, word2;
    int[][] memo;

    public int minDistance(String _word1, String _word2) {
        init(_word1, _word2);
        return dp(word1.length() - 1, word2.length() - 1);
    }

    int dp(int i, int j) {
        // base case
        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dp(i - 1, j - 1);
        } else {
            int word1Delete = dp(i - 1, j) + 1;
            int word2Delete = dp(i, j - 1) + 1;
            memo[i][j] = Math.min(word1Delete, word2Delete);
        }

        return memo[i][j];
    }

    void init(String _word1, String _word2) {
        word1 = _word1;
        word2 = _word2;
        memo = new int[word1.length()][word2.length()];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
    }
}