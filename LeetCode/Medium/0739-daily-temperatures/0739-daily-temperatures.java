class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length]; 
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty()
                    && temperatures[i] >= temperatures[stack.peekLast()]) {
                stack.pollLast();
            }

            answer[i] = stack.isEmpty() ? 0 : Math.abs(i - stack.peekLast());
            stack.offer(i);
        }

        return answer;
    }
}