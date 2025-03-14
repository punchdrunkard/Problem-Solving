class Solution {
    private static final int MAX_POSSIBLE_CANDIES = 10_000_001;

    public int maximumCandies(int[] candies, long k) {

        int lo = 0;
        int hi = MAX_POSSIBLE_CANDIES;
        
        // Binary search for the maximum possible candies per child
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            
            if (canDistributeCandies(candies, mid, k)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        
        return lo;
    }

    private boolean canDistributeCandies(int[] candies, int candiesPerChild, long k) {
        // Edge case: can't distribute zero candies
        if (candiesPerChild == 0) {
            return true;  // Actually, giving 0 candies is always possible
        }
        
        // Count how many children we can serve with 'candiesPerChild' each
        long childrenServed = 0;
        
        for (int pile : candies) {
            // How many children can get 'candiesPerChild' from this pile
            childrenServed += pile / candiesPerChild;
            
            // Early return if we've already reached our target
            if (childrenServed >= k) {
                return true;
            }
        }
        
        return childrenServed >= k;
    }
}