class Solution {

    public int minimumIndex(List<Integer> nums) {
        int result = -1;
        Map<Integer, int[]> frequencyMap = createFrequencyMap(nums);

        for (int idx = 0; idx < nums.size() - 1; idx++) {
            int dominant = findDominant(nums.subList(0, idx + 1), frequencyMap);
            if (dominant == -1) {
                continue;
            }

            int frequency = frequencyMap.get(dominant)[nums.size() - 1]
                    - frequencyMap.get(dominant)[idx];

            if (2 * frequency > nums.size() - 1 - idx) {
                return idx;
            }
        }

        return result;
    }

    int findDominant(List<Integer> subList, Map<Integer, int[]> frequencyMap) {
        for (int num : subList) {
            int frequency = frequencyMap.get(num)[subList.size() - 1];

            if (2 * frequency > subList.size()) {
                return num;
            }
        }
        return -1;
    }

    Map<Integer, int[]> createFrequencyMap(List<Integer> nums) {
    Map<Integer, int[]> frequencyMap = new HashMap<>();
    
    // 처음부터 모든 고유 숫자에 대해 배열 생성
    for (int num : nums) {
        frequencyMap.putIfAbsent(num, new int[nums.size()]);
    }
    
    // 첫 번째 요소 처리
    int firstNum = nums.get(0);
    frequencyMap.get(firstNum)[0] = 1;
    
    // 나머지 요소들에 대해 한 번의 반복으로 처리
    for (int i = 1; i < nums.size(); i++) {
        int num = nums.get(i);
        
        // 모든 키에 대해 이전 값 복사
        for (int key : frequencyMap.keySet()) {
            frequencyMap.get(key)[i] = frequencyMap.get(key)[i-1];
        }
        
        // 현재 숫자의 빈도 증가
        frequencyMap.get(num)[i]++;
    }
    
    return frequencyMap;
}

    void printFrequencyMap(Map<Integer, int[]> frequencyMap) {
        StringBuilder sb = new StringBuilder();
        for (int key : frequencyMap.keySet()) {
            sb.append("key: ").append(key).append(": ")
                    .append(Arrays.toString(frequencyMap.get(key)))
                    .append('\n');
        }

        System.out.println(sb);
    }
}