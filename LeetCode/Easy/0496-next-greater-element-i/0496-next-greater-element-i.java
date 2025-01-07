class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> valueToIndexInNums2 = getValueToIndexIn(nums2);
        int[] nextGreaterInNums2 = findNextGreaterIn(nums2);

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterInNums2[valueToIndexInNums2.get(nums1[i])];
        }

        return result;
    }

    int[] findNextGreaterIn(int[] nums) {
        int[] nextGreaterInNums = new int[nums.length];
        Deque<Integer> stk = new ArrayDeque<>();

        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peekLast() <= nums[i]) {
                stk.pollLast();
            }

            nextGreaterInNums[i] = stk.isEmpty() ? -1 : stk.peekLast();
            stk.offer(nums[i]);
        }
        return nextGreaterInNums;
    }

    Map<Integer, Integer> getValueToIndexIn(int[] nums) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            valueToIndex.put(nums[i], i);
        }
        return valueToIndex;
    }
}