class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return backtracking(0, 0, nums, target);
    }

    int backtracking(int idx, int current, int[] nums, int target) {
        if (idx == nums.length) {
            return current == target ? 1 : 0;
        }

        int add = backtracking(idx + 1, current + nums[idx], nums, target);
        int substract = backtracking(idx + 1, current - nums[idx], nums, target);

        return add + substract;
    }
}