class Solution {

    static int n;
    static long[] pSum, pSumReversed;

    public int waysToSplitArray(int[] nums) {
        init(nums);
        calculatePrefixSum(nums);

        int count = 0;

        for (int i = 0; i < n - 1; i++) {
            if (pSum[i] >= pSumReversed[i + 1]) {
                count++;
            }
        }

        return count;
    }

    static void calculatePrefixSum(int[] nums) {
        pSum[0] = nums[0];
        pSumReversed[n - 1] = nums[n - 1];

        for (int i = 1; i < n; i++) {
            pSum[i] = pSum[i - 1] + nums[i];
        }

        for (int i = n - 2; i >= 0; i--) {
            pSumReversed[i] = pSumReversed[i + 1] + nums[i];
        }
    }

    static void init(int[] nums) {
        n = nums.length;
        pSum = new long[n];
        pSumReversed = new long[n];
    }
}