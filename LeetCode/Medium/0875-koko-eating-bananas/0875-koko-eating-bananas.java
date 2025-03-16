class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int lo = 0;
        int hi = Integer.MAX_VALUE;

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            if (!check(mid, piles, h)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

    boolean check(int speed, int[] piles, int h) {
        // speed 의 속도로 바나나를 먹었을 때,
        // 모든 바나나를 먹기 위해 걸리는 시간을 계산한다.
        long hoursNeeded = 0;

        for (int pile : piles) {
            hoursNeeded += Math.ceil((double)pile / speed);

            if (hoursNeeded > h) {
                return false;
            }
        }

        return hoursNeeded <= h;
    }
}