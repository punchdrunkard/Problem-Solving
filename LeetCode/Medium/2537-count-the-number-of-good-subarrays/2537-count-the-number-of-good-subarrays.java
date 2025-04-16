class Solution {
    public long countGood(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        long pairCount = 0;
        long result = 0;

        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            int val = nums[right];
            int freq = counter.getOrDefault(val, 0);
            pairCount += freq; // 새로 들어온 애와 기존 애들과의 쌍을 더함
            counter.put(val, freq + 1);

            // 윈도우 내 pairCount >= k 가 될 때까지 left 증가
            while (pairCount >= k) {
                result += nums.length - right; // 현재 right로 끝나는 모든 서브배열이 유효
                int leftVal = nums[left];
                int leftFreq = counter.get(leftVal);
                counter.put(leftVal, leftFreq - 1);
                pairCount -= (leftFreq - 1); // 제거된 애가 기여한 쌍만큼 빼줌
                left++;
            }
        }

        return result;
    }
}
