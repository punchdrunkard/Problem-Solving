class Solution {

    // meme[i][j] := s[i:j]를 palindrom 으로 만들기 위한 ~ 
    int[][] memo;
    String s;

    public int minInsertions(String _s) {
        init(_s);
        return solve(0, s.length() - 1);
    }

    public int solve(int lo, int hi) {

        if (lo > hi) {
            return 0;
        }

        // base case
        if (lo == hi) {
            return 0;
        }

        if (memo[lo][hi] != -1) {
            return memo[lo][hi];
        }
        
        if (s.charAt(lo) == s.charAt(hi)) {
            memo[lo][hi] = solve(lo + 1, hi - 1);
        } else {
            memo[lo][hi] = Math.min(solve(lo + 1, hi), solve(lo, hi - 1)) + 1;
        }

        return memo[lo][hi];
    }

    public void init(String _s) {
        s = _s;
        memo = new int[s.length()][s.length()];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }
    }
}