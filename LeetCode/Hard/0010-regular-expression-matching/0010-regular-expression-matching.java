enum Result {
    TRUE,
    FALSE
}

class Solution {
    Result[][] memo;
    String text, pattern;

    public boolean isMatch(String s, String p) {
        init(s, p);
        return dp(0, 0);
    }

    // dp(i ,j) := does text[i:] and pattern[j:] match?
    boolean dp(int i, int j) {

        if (i > text.length() || j > pattern.length()) {
            return false;
        }
        
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }

        boolean result = false;

        // base case
        if (j == pattern.length()) {
            result = (i == text.length());
        } else {
            boolean firstMatch = isFirstMatch(i, j);

            if (isNextElementStar(j)) {
                result = dp(i, j + 2) || (firstMatch && dp(i + 1, j));
            } else {
                result = firstMatch && dp(i + 1, j + 1);
            }
        }

        memo[i][j] = result ? Result.TRUE : Result.FALSE;
        return result;
    }

    boolean isNextElementStar(int j) {
        return j + 1 < pattern.length() && pattern.charAt(j + 1) == '*';
    }

    boolean isFirstMatch(int i, int j) {
        return j < pattern.length() && i < text.length()
                && pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.';
    }

    void init(String s, String p) {
        text = s;
        pattern = p;
        memo = new Result[text.length() + 1][pattern.length() + 1];
    }
}