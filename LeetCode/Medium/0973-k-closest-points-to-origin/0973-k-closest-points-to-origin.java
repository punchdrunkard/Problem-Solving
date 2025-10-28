class Solution {
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> {
            long aDist = (long) a[0] * a[0] + (long) a[1] * a[1];
            long bDist = (long) b[0] * b[0] + (long) b[1] * b[1];
            return Long.compare(aDist, bDist);
        });
        
        return Arrays.copyOfRange(points, 0, k);
    }
}