class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new LinkedList<>();

        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            int target = -nums[i];

            if (visited.contains(target)) {
                continue;
            }

            // two pointer
            int lo = i + 1;
            int hi = nums.length - 1;
            
            while (lo < hi) {
                int left = nums[lo];
                int right = nums[hi];

                int sum = left + right;

                if (sum == target) {
                    result.add(List.of(-target, left, right));

                    while (lo < hi && left == nums[lo]) {
                        lo++;
                    }

                    while (lo < hi && right == nums[hi]) {
                        hi--;
                    }
                } else if (sum > target) {
                    hi--;
                } else {
                    lo++;
                }
            }

            visited.add(target);
        }

        return result;
    }
}