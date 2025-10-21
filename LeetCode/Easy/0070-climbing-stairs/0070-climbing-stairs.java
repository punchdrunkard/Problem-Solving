class Solution {

    int[] cache;

    // TODO: 1 또는 2를 이용해서 n을 만드는 경우의 수 
    public int climbStairs(int n) {
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
        return climbStairsHelper(n);
    }

    int climbStairsHelper(int n) {
        // base cae
        if (n == 1) {
            return 1; // 1
        }

        if (n == 2) {
            return 2; // 1 + 1, 2 
        }

        // memoization
        if (cache[n] != -1) {
            return cache[n];
        }

        // recursive case
        cache[n] = climbStairsHelper(n - 1) + climbStairsHelper(n - 2);
        return cache[n];
    }
}