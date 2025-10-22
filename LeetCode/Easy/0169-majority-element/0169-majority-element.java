class Solution {
    // 정렬한다면 항상 가운데 즈음에 존재할거같은데..?
    public int majorityElement(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        return nums[n / 2];
    }
}