class Solution {
    static Set<Integer> perfectSquare;
    static int[] cache;

    public int numSquares(int n) {
        init(n);
        return dp(n);
    }

    int dp(int n) {
        if (n == 0) {
            return 0;
        }
        
        if (perfectSquare.contains(n)) {
            return 1;
        }

        if (cache[n] != -1) {
            return cache[n];
        }

        int result = Integer.MAX_VALUE;

        for (int i = 1; i * i <= n; i++) {
            int square = i * i;
            result = Math.min(result, dp(n - square) + 1);
        }
        
        cache[n] = result;
        return result;
    }

    void init(int n) {
        perfectSquare = createPerfectSquare(n);
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
    }

    Set<Integer> createPerfectSquare(int n) {
        Set<Integer> perfectSquare = new HashSet<>();

        for (int i = 1; i * i <= n; i++) {
            perfectSquare.add(i * i);
        }

        return perfectSquare;
    }
}