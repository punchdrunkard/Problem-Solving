class Solution {
    public int maxAscendingSum(int[] nums) {
        // nums.length <= 100 -> brute force available

        int max = -1;
        for (int st = 0; st < nums.length; st++) {
            int sum = nums[st];
            int prev = nums[st];
            
            for (int i = st + 1; i < nums.length; i++) {
                int curr = nums[i];

                if (prev >= curr) {
                    break;
                }

                sum += curr;
                prev = curr;
            }

            max = Math.max(max, sum);
        }

        return max;
    }
}