class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        Map<Integer, Integer> sumFrequency = new HashMap<>();

        // compute all possible sums of pairs (nums1[i] + nums2[j])
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                int sum = num1 + num2;
                sumFrequency.put(sum, sumFrequency.getOrDefault(sum, 0) + 1);
            }

        }

        // find complementary pairs from (nums3[k] + num4[l])
        for (int num3 : nums3) {
            for (int num4 : nums4) {
                int complement = -(num3 + num4);
                count += sumFrequency.getOrDefault(complement, 0);
            }
        }

        return count;
    }
}