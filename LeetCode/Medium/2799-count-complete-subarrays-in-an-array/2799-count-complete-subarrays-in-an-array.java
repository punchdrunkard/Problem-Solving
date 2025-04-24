class Solution {
    public int countCompleteSubarrays(int[] nums) {
        // Calculate the number of distinct elements in the whole array
        Set<Integer> distinctSet = new HashSet<>();
        for (int num : nums) {
            distinctSet.add(num);
        }
        int totalDistinct = distinctSet.size();

        // Sliding window approach
        int answer = 0;
        Map<Integer, Integer> windowFrequency = new HashMap<>();
        int right = 0;

        for (int left = 0; left < nums.length; left++) {
            // Expand window until we have all distinct elements or reach the end
            while (right < nums.length && windowFrequency.size() < totalDistinct) {
                int currentElement = nums[right];
                windowFrequency.put(currentElement, windowFrequency.getOrDefault(currentElement, 0) + 1);
                right++;
            }

            // If window has all distinct elements, count valid subarrays
            if (windowFrequency.size() == totalDistinct) {
                answer += nums.length - (right - 1);
            }

            // Shrink window from the left
            int removedElement = nums[left];
            windowFrequency.put(removedElement, windowFrequency.get(removedElement) - 1);
            if (windowFrequency.get(removedElement) == 0) {
                windowFrequency.remove(removedElement);
            }
        }

        return answer;
    }
}