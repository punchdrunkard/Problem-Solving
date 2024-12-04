class Solution {
    // 미리 w 를 정렬해두고, h 에서 LIS를 찾는다.
    public int maxEnvelopes(int[][] envelopes) {
        sort2DArr(envelopes);
        
        int[] heights = Arrays.stream(envelopes)
                .mapToInt(row -> row[1])
                .toArray();

        return calculateLengthOfLIS(heights);
    }

    int calculateLengthOfLIS(int[] arr) {
        System.out.println(Arrays.toString(arr));
        int[] top = new int[arr.length];
        int piles = 0;

        for (int i = 0; i < arr.length; i++) {
            int poker = arr[i];
            int target = lowerBound(top, piles, poker);

            if (target == piles) {
                top[piles] = poker;
                piles++;
            } else {
                top[target] = poker;
            }
        }

        return piles;
    }

    int lowerBound(int[] arr, int size, int value) {
        int lo = -1;
        int hi = size;

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            if (!(arr[mid] >= value)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

    void sort2DArr(int[][] arr) {
        Arrays.sort(arr, Comparator
                .comparingInt((int[] a) -> a[0])
                .thenComparingInt(a -> -a[1]));
    }
}