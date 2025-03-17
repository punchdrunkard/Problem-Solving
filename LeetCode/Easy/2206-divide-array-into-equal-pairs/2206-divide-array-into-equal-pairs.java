class Solution {
    public boolean divideArray(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        
        for (int num: nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        
        for (int value: countMap.values()) {
            if (value % 2 != 0) {
                return false;
            }
        }

        return true;
    }
}