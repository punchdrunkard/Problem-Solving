class Solution {
    public long coloredCells(int n) {
        long nl = (long) n;
        return (2 * nl - 1) * (2 * nl - 1) - (4 * ((nl - 1) * nl / 2));
    }
}