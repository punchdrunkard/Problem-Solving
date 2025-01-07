class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] nextGreaterElementsIn = new int[n];

        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 2 * n - 1; i >= 0; i--) { 
            while (!stk.isEmpty() && nums[i % n] >= stk.peekLast()) {
                stk.pollLast();
            }

            nextGreaterElementsIn[i % n] = stk.isEmpty() ? -1 : stk.peekLast();
            stk.offer(nums[i % n]);
        }

        return nextGreaterElementsIn;
    }
}