class Solution {
    public int[][] merge(int[][] intervals) {
        // Sort array by interval's start value
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        List<int[]> result = new ArrayList<>();

        // 어짜피 가장 최근 result 에 추가될 듯

        for (int[] interval: intervals) {
            if (result.isEmpty()) {
                result.add(interval);
                continue;
            }

            // 현재 interval 과 result 의 interval 을 비교한다.
            int nextIntervalStart = interval[0];
            int resultIntervalEnd = result.get(result.size() - 1)[1];
            
            // overlapping - merge
            if (nextIntervalStart <= resultIntervalEnd) {
                result.get(result.size() - 1)[1] = Math.max(resultIntervalEnd, interval[1]);
            } else {
                result.add(interval);
            }
        }

        return result.toArray(new int[0][]);
    }
}