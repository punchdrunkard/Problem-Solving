class Solution {

    int[] cache;

    public int tribonacci(int n) {
        init(n);
        return tribonacciHelper(n);
    }

    int tribonacciHelper(int n) {
        // base case (T0 = 0, T1 = 1, T2 = 1)
        if (n == 0) {
            return 0;
        }

        if (n <= 2) {
            return 1;
        }

        if (cache[n] != -1) {
            return cache[n];
        }

        cache[n] = tribonacciHelper(n - 3) + tribonacciHelper(n - 2) + tribonacciHelper(n - 1);
        return cache[n];
    }

    void init(int n) {
        cache = new int[n + 1];
        Arrays.fill(cache, -1);
    }
}