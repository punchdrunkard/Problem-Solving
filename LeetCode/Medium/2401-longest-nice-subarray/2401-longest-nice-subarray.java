class Solution {
    public int longestNiceSubarray(int[] nums) {
        // representing all bits that are set in the current window
        int bitmask = 0; 
        int windowStart = 0;
        int maxLength = 0;

        for (int windowEnd = 0; windowEnd < nums.length; ++windowEnd){ 
            // shrink window (not "nice")
            while ((bitmask & nums[windowEnd]) != 0) {
                bitmask ^= nums[windowStart]; 
                windowStart++;
            }

            // expand window 
            bitmask |= nums[windowEnd];
            
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }

        return maxLength;
    }
}