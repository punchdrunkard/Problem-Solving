class Solution {
    public int maxAscendingSum(int[] nums) {
        int maxSum = 0;
        int current = nums[0];

        for (int i = 1; i < nums.length; ++i) {
            
            if (nums[i] > nums[i - 1]) {
                current += nums[i];
            } else {
                maxSum = Math.max(maxSum, current);
                current = nums[i];
            }
        }
        
        maxSum = Math.max(maxSum, current);

        return maxSum;
    }
}