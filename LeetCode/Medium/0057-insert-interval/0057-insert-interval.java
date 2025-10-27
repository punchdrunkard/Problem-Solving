class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int n = intervals.length;
        int i = 0;

        // 1. newIntveral 이전에 끝나는 구간을 처리한다.
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        // 2. 겹쳐지는 구간을 처리한다.
        while (i < n && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        result.add(new int[]{newInterval[0], newInterval[1]});

        // 3. 나머지 부분을 처리한다 
        while (i < n) {
            result.add(new int[]{intervals[i][0], intervals[i][1]});
            i++;
        }

        for (int j = 0; j < result.size(); j++) {
            sout(Arrays.toString(result.get(j)));
        }
        return result.stream().toArray(int[][]::new);
    }

    void sout(Object o) {
        System.out.println(o);
    } 
}