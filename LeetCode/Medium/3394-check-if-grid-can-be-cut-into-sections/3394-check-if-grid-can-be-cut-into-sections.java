class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        int m = rectangles.length;

        int[][] xIntervals = new int[m][2];
        int[][] yIntervals = new int[m][2];
        for (int i = 0; i < m; i++) {
            xIntervals[i] = new int[] { rectangles[i][0], rectangles[i][2] };
            yIntervals[i] = new int[] { rectangles[i][1], rectangles[i][3] };
        }

        return merge(xIntervals) >= 3 || merge(yIntervals) >= 3;
    }

    // merge 한 후, 구역 갯수를 리턴함
    int merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();

        for (int[] interval : intervals) {
            if (merged.isEmpty()) {
                merged.add(interval);
                continue;
            }

            if (interval[0] < merged.getLast()[1]) {
                merged.getLast()[1] = Math.max(interval[1], merged.getLast()[1]);
            } else {
                merged.add(interval);
            }
        } // end of for

        return merged.size();
    }
}