class Solution {

    String s;
    int[][] memo;

    public int longestPalindromeSubseq(String _s) {
        init(_s);
        int length = dp(0, s.length() - 1);
        return length;
    }

    int dp(int lo, int hi) {
        // base case
        if (lo > hi) {
            return 0;
        }
        if (lo == hi) {
            return 1;
        }

        if (memo[lo][hi] != -1) {
            return memo[lo][hi];
        }

        if (s.charAt(lo) == s.charAt(hi)) {
            memo[lo][hi] = 2 + dp(lo + 1, hi - 1);
        } else {
            memo[lo][hi] = Math.max(dp(lo + 1, hi), dp(lo, hi - 1));
        }

        return memo[lo][hi];
    }

    void init(String _s) {
        s = _s;
        memo = new int[s.length()][s.length()];

        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
    }
}
