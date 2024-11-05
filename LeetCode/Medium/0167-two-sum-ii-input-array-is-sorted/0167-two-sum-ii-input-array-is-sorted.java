class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int lo = 0;
        int hi = numbers.length - 1;

        while (lo < hi) {
            int sum = numbers[lo] + numbers[hi];

            if (sum == target) {
                break;
            }
            
            if (sum > target) {
                hi--;
                continue;
            }

            if (sum < target) {
                lo++;
                continue;
            }
        }

        return new int[]{lo + 1, hi + 1};
    }
}