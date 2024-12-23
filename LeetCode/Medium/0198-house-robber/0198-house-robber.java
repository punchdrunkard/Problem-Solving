class Solution {

    static int[] nums, cache;

    public int rob(int[] nums) {
        init(nums);
        return dp(0);
    }


    public int dp(int i) {
        if (isOutOfBound(i)) {
            return 0;
        }

        if (cache[i] != -1) {
            return cache[i];
        }

        int selected = dp(i + 2) + nums[i];
        int notSelected = dp(i + 1);

        cache[i] = Math.max(selected, notSelected);
        return cache[i];
    }

    boolean isOutOfBound(int x) {
        return x < 0 || x >= nums.length;
    }

    void init(int[] nums) {
        this.nums = nums;
        cache = new int[nums.length];
        Arrays.fill(cache, -1);
    }
}