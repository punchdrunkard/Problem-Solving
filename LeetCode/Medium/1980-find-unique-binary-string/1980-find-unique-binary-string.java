class Solution {
    public String findDifferentBinaryString(String[] nums) {
        int n = nums[0].length();
        StringBuilder answer = new StringBuilder();
        
        for (int i = 0; i < nums.length; i++) {
            answer.append(nums[i].charAt(i) == '0' ? '1' : '0');
        }

        return answer.toString();
    }
}