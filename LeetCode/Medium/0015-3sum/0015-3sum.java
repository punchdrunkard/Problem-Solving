class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        
        List<List<Integer>> result = new LinkedList();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int target = -nums[i];
            Set<Integer> set = new HashSet();

            for (int j = i + 1; j < nums.length; j++) {
                int other = target - nums[j];
                
                if (set.contains(other)) {
                    result.add(List.of(-target, nums[j], other));

                    while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                        j++;
                    }
                } else {
                    set.add(nums[j]);
                }
            }

        } // end of for

        return result;
    }
}