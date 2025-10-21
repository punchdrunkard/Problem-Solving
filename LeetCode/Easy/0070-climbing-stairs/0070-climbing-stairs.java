class Solution {
    int[] cache;

    public int climbStairs(int n) {
        init(n);
        return climbStairsHelper(n);
    }

    int climbStairsHelper(int n) {
        // base case
        if (n == 0) return 1;  // 0계단: 1가지 (아무것도 안함)
        if (n == 1) return 1;  // 1계단: 1가지 (1칸)

        if (cache[n] != -1) {
            return cache[n];
        }

        // 점화식: f(n) = f(n-1) + f(n-2)
        cache[n] = climbStairsHelper(n - 1) + climbStairsHelper(n - 2);

        return cache[n];
    }

    void init(int n) {
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
    }
}