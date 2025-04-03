class Solution {
    public long maximumTripletValue(int[] nums) {
        // (nums[i] - nums[j]) * nums[k]
        // 둘 다 최대면 되는거 아님?
        int n = nums.length;
        long res = 0;
        long imax = 0;
        long dmax = 0; // nums[i] - nums[j] 의 최댓값

        for (int k = 0; k < n; k++) {
            res = Math.max(res, nums[k] * dmax);
            dmax = Math.max(dmax, imax - nums[k]);
            imax = Math.max(imax, nums[k]);
        }

        return res;
        
    }
}