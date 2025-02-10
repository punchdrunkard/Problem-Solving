class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new LinkedList();

        Arrays.sort(nums);

        // select i, j
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; 
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                long other = target - nums[i] - nums[j];

                int lo = j + 1;
                int hi = nums.length - 1;

                while (lo < hi) {
                    int left = nums[lo];
                    int right = nums[hi];
                    long sum = left + right;

                    if (sum == other) {
                        quadruplets.add(List.of(nums[i], nums[j], left, right));

                        while (lo < hi && left == nums[lo]) {
                            lo++;
                        }

                        while (lo < hi && right == nums[hi]) {
                            hi--;
                        }
                    } else if (left + right < other) {
                        lo++;
                    } else {
                        hi--;
                    }
                } // end of while
            } // end of j-loop
        } // end of i-loop

        return quadruplets;
    }
}