class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Set<List<Integer>> res = new HashSet<>();

        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int target = -nums[i];

            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 목표 : nums[j] + nums[k] = - nums[i] -> two pointer
            int lo = i + 1;
            int hi = n - 1;

            while (lo < hi) {
                if (lo == i) {
                    lo++;
                }

                if (hi == i) {
                    hi--;
                }

                if (nums[lo] + nums[hi] == target) {
                    res.add(List.of(-target, nums[lo], nums[hi]));
                }
                if (nums[lo] + nums[hi] < target) {
                    lo++;
                } else {
                    hi--;
                }
            }
        } 

        return new ArrayList<>(res); 
    }
}