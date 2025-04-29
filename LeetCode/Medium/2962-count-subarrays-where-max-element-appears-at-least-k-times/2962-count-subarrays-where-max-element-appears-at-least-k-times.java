class Solution {
    public long countSubarrays(int[] nums, int k) {
        int max = Arrays.stream(nums).max().getAsInt();
    
        long answer = 0;
        int maxCount = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == max) {
                maxCount++;
            }
            
            while (maxCount >= k && left <= right) {
                if (nums[left] == max) {
                    maxCount--;
                }
                left++;
            }

            answer += left;
        }
        
        return answer;
    }
}