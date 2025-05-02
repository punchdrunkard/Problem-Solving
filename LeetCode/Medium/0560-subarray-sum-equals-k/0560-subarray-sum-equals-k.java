class Solution {
    public int subarraySum(int[] nums, int k) {
        int[] pSum = calculatePrefixSum(nums);

        int count = 0;
        for (int i = 1; i < pSum.length; i++) {
            for (int j = i; j < pSum.length; j++) {
                int sum = pSum[j] - pSum[i - 1];
                if (sum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    int[] calculatePrefixSum(int[] nums) {
        int[] pSum = new int[nums.length + 1];
        pSum[0] = 0;

        for (int i = 1; i <= nums.length; i++) {
            pSum[i] += (pSum[i - 1] + nums[i - 1]);
        }

        return pSum;
    }
}