class Solution {

    static int[] nums;
    static int[][] cache;

    public int maxCoins(int[] input) {
        init(input);
        return dp(1, nums.length - 2);
    }

    int dp(int i, int j) {
        if (i > j) {
            return 0;
        }

        if (cache[i][j] != -1) {
            return cache[i][j];
        }

        cache[i][j] = 0;

        // i ~ j 구간에서 mid를 마지막으로 터뜨리는 경우 고려
        for (int mid = i; mid <= j; mid++) {
            int coins = nums[i - 1] * nums[mid] * nums[j + 1]
                    + dp(i, mid - 1)
                    + dp(mid + 1, j);

            cache[i][j] = Math.max(cache[i][j], coins);
        }

        return cache[i][j];
    }

    void init(int[] input) {
        nums = new int[input.length + 2];
        nums[0] = 1; // 왼쪽 경계
        nums[nums.length - 1] = 1; // 오른쪽 경계
        for (int i = 0; i < input.length; i++) {
            nums[i + 1] = input[i]; // 입력값 복사
        }

        int n = nums.length;
        cache = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(cache[i], -1);
        }
    }
}
