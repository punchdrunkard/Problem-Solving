class Solution {
    int[][] memo;
    String word1, word2;

    public int minDistance(String _word1, String _word2) {
        init(_word1, _word2);
        return dp(word1.length() - 1, word2.length() - 1);
    }

    // i, j => index of word1, word2
    int dp(int i, int j) {
        // base-case: 어느 한 문자열에서는 완료, 남은만큼 삽입하면 된다!
        if (i == -1) {
            return j + 1;
        }

        if (j == -1) {
            return i + 1;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        if (word1.charAt(i) == word2.charAt(j)) { // skip
            memo[i][j] = dp(i - 1, j - 1);
        } else {
            int insert = dp(i, j - 1) + 1;
            int delete = dp(i - 1, j) + 1;
            int replace = dp(i - 1, j - 1) + 1;

            memo[i][j] = Math.min(insert, Math.min(delete, replace));
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