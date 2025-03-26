class Solution {
    public int minOperations(int[][] grid, int x) {
        int[] arr = Arrays.stream(grid).flatMapToInt(Arrays::stream).toArray();
        Arrays.sort(arr);       

        int reminder = arr[0] % x;
        int mid = arr[arr.length / 2];
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % x != reminder) {
                return -1;
            }

            count += (Math.abs(arr[i] - mid) / x);
        }

        return count;
    }
}