class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int other = target - nums[i];

            if (indexMap.containsKey(other)) {
                return new int[]{indexMap.get(other), i};
            }

            indexMap.put(nums[i], i); 
        }

        return null;
    }
}
