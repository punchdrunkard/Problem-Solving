class Solution {

    int[] nums;
    int[][] cache;

    public int findTargetSumWays(int[] nums, int target) {
        int totalSum = Arrays.stream(nums).sum();
        int finalTarget = (target + totalSum) / 2;
        if ((target + totalSum) % 2 != 0 || (target + totalSum) < 0) {
            return 0;
        }

        this.nums = nums;
        cache = new int[nums.length + 1][1001];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }

        return dp(nums.length, finalTarget);
    }

    int dp(int i, int target) {
        // base case
        if (i == 0) {
            return target == 0 ? 1 : 0;
        }

        if (target < 0) {
            return 0;
        }

        if (cache[i][target] != -1) {
            return cache[i][target];
        }

        int selected = dp(i - 1, target - nums[i - 1]);
        int notSelected = dp(i - 1, target);

        cache[i][target] = selected + notSelected;
        return cache[i][target];
    }
}