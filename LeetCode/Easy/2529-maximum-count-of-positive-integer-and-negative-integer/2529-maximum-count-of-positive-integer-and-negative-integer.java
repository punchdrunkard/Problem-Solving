class Solution {
    public int maximumCount(int[] nums) {
        int positiveCount = nums.length - upperBound(nums);
        int negativeCount = lowerBound(nums);
        return Math.max(positiveCount, negativeCount);
    }

    // strictly greater than 0
    int upperBound(int[] nums) {
        int lo = -1;
        int hi = nums.length;

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            if (!(nums[mid] > 0)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

    // greater than equal 0
    int lowerBound(int[] nums) {
        int lo = -1;
        int hi = nums.length;

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            if (!(nums[mid] >= 0)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }
}