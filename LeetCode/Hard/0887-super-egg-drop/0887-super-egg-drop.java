class Solution {
    static final int INF = Integer.MAX_VALUE;
    int[][] cache;
    int k, n;

    public int superEggDrop(int _k, int _n) {
        init(_k, _n);
        return superEggDropHelper(k, n);
    }

    int superEggDropHelper(int eggCount, int f) {
        // Base Case
        if (eggCount == 1) {
            return f; // 1개의 계란으로는 1층부터 차례로 탐색해야 함
        }

        if (f == 0 || f == 1) {
            return f; // 0층은 0회, 1층은 1회 필요
        }

        // Memoization
        if (cache[eggCount][f] != -1) {
            return cache[eggCount][f];
        }

        cache[eggCount][f] = INF;

        // Optimal `f`를 찾아가며 탐색
        int lo = 0, hi = f + 1;
        
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            int crashed = superEggDropHelper(eggCount - 1, mid - 1);
            int survived = superEggDropHelper(eggCount, f - mid);
            int worstCase = 1 + Math.max(crashed, survived);

            cache[eggCount][f] = Math.min(cache[eggCount][f], worstCase);

            if (crashed < survived) {
                lo = mid; 
            } else {
                hi = mid;
            }
        }

        return cache[eggCount][f];
    }

    void init(int _k, int _n) {
        k = _k;
        n = _n;
        cache = new int[k + 1][n + 1];
        for (int i = 0; i < cache.length; i++) {
            Arrays.fill(cache[i], -1);
        }
    }
}
