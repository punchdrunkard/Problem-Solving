class Solution {
    public int longestMonotonicSubarray(int[] nums) {
        // n <= 50 ; it is fast enough to check all possible subarrays
        int max = 0;

        for (int st = 0; st < nums.length; st++) {
            int length = 1;

            for (int i = st + 1; i < nums.length; i++) {
                if (nums[i] <= nums[i - 1]) {
                    break;
                }

                length++;
            }
            
            max = Math.max(max, length);
        }
        
        for (int st = 0; st < nums.length; st++) {
            int length = 1;

            for (int i = st + 1; i < nums.length; i++) {
                if (nums[i] >= nums[i - 1]) {
                    break;
                }
                length++;
            }

            max = Math.max(max, length);
        }

        
        return max;
    }

}