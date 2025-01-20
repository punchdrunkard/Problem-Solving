class Solution {

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        Map<Integer, Integer> numToIndex = createIndexMap(arr);

        int minIndexForRow = findMinCompleteIndex(mat, numToIndex, true); // 행 기준 탐색
        int minIndexForCol = findMinCompleteIndex(mat, numToIndex, false); // 열 기준 탐색

        // 두 값 중 더 작은 값 반환
        return Math.min(minIndexForRow, minIndexForCol);
    }

    // 숫자와 인덱스 매핑을 생성하는 메서드
    private Map<Integer, Integer> createIndexMap(int[] arr) {
        Map<Integer, Integer> numToIndex = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            numToIndex.put(arr[i], i);
        }
        return numToIndex;
    }

    // 행 또는 열을 기준으로 최소 완성 인덱스를 찾는 메서드
    private int findMinCompleteIndex(int[][] mat, Map<Integer, Integer> numToIndex, boolean isRow) {
        int minIndex = Integer.MAX_VALUE;

        int outerLoop = isRow ? mat.length : mat[0].length; // 행 또는 열 개수
        int innerLoop = isRow ? mat[0].length : mat.length; // 열 또는 행 개수

        for (int outer = 0; outer < outerLoop; outer++) {
            int maxIndexInLine = Integer.MIN_VALUE;

            for (int inner = 0; inner < innerLoop; inner++) {
                int value = isRow ? mat[outer][inner] : mat[inner][outer];
                maxIndexInLine = Math.max(maxIndexInLine, numToIndex.get(value));
            }

            minIndex = Math.min(minIndex, maxIndexInLine);
        }

        return minIndex;
    }
}
