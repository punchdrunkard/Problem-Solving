class Solution {
    int[][] memo;
    char[] s1, s2;

    public int longestCommonSubsequence(String text1, String text2) {
        init(text1, text2);
        preprocessDp();
        return dp(s1.length, s2.length);
    }

    int dp(int i, int j) {
        // base case
        if (i == 0 || j == 0) {
            memo[i][j] = 0;
            return memo[i][j];
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // 문자가 같을 때
        if (s1[i - 1] == s2[j - 1]) {
            memo[i][j] = dp(i - 1, j - 1) + 1;
        } else { // 문자가 다를 때
            memo[i][j] = Math.max(dp(i - 1, j), dp(i, j - 1));
        }

        return memo[i][j];
    }

    void preprocessDp() {
        memo = new int[s1.length + 1][s2.length + 1];
        for (int i = 0; i <= s1.length; i++) {
            for (int j = 0; j <= s2.length; j++) {
                memo[i][j] = -1;
            }
        }
    }

    void init(String text1, String text2) {
        s1 = text1.toCharArray();
        s2 = text2.toCharArray();
    }
}