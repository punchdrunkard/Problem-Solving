class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int hi = 0;
        long sum = 0;
        int answer = Integer.MAX_VALUE;

        for (int lo = 0; lo < nums.length; lo++) {
            // expand - expand until we reach or exceed the target sum
            while (hi < nums.length && sum < target) {
                sum += nums[hi];
                hi++;
            }

            // If we reached the target sum, update the answer
            if (sum >= target) {
                answer = Math.min(answer, (hi - lo));
            }

            // shrink - remove the element at lo before moving to the next iteration
            sum -= nums[lo];
        }

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
}