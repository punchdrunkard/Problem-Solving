class Solution {

    int[] nums;
    int target;
    // cache[idx][i] := the number of different expressions of target i
    Map<String, Integer> cache;

    public int findTargetSumWays(int[] nums, int target) {
        this.nums = nums;
        this.target = target;
        cache = new HashMap<>();

        return dp(0, 0);
    }

    int dp(int idx, int current) {
        // base case
        if (idx == nums.length) {
            return current == target ? 1 : 0;
        }

        String key = idx + ", " + current;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int add = dp(idx + 1, current + nums[idx]);
        int substract = dp(idx + 1, current - nums[idx]);

        cache.put(key, add + substract);
        return cache.get(key);
    }
}