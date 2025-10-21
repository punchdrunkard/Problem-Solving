/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

// FFFFTTTT 형태의 분포 -> parametric search 사용 가능 
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int lo = 0;
        int hi = n;

        while (lo + 1 < hi) {
            int mid = lo + (hi  - lo) / 2;

            if (!isBadVersion(mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

    
}