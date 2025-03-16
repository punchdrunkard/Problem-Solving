class Solution {

    static final long INF = Long.MAX_VALUE;

    public long repairCars(int[] ranks, int cars) {
        long lo = 0;
        long hi = INF;

        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            
            if (!check(mid, ranks, cars)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }

    boolean check(long time, int[] ranks, int cars) {
        // 주어진 time 에 몇 개의 car 를 수리할 수 있는가? 
        long totalRepairedCar = 0;

        for (int rank: ranks) {
            // time = rank * (n ^ 2)
            // n = sqrt(time / rank)
            totalRepairedCar += (long)Math.sqrt(time / rank);
        }

        return totalRepairedCar >= cars;
    }
}