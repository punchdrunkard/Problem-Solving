class Solution {
    // 어떤 원소 k에 대해서 배열의 원소 중 target - k가 존재하는 지 판단해야 한다. 
    public int[] twoSum(int[] nums, int target) {
        // 배열을 순회하지 않아도 원소가 존재했는지 파악하기 위함
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{i, map.get(complement)};
            }
            
            map.put(nums[i], i);
        }

        return null;
    }
}