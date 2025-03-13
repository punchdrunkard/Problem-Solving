class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int lo = -1;
        int hi = queries.length;

        if (canMakeZeroArray(new int[nums.length], nums)) {
            return 0;
        }

        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;

            if (!check(nums, queries, mid)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        System.out.println("lo: " + lo + ", hi: " + hi);

        return hi < queries.length ? hi + 1 : -1;
    }

    // k까지 query 를 적용했을 때, zero array가 만들어지는가?
    boolean check(int[] nums, int[][] queries, int k) {
        int[] differenceArray = new int[nums.length];
        for (int i = 0; i <= k; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            int val = queries[i][2];

            differenceArray[left] += val;
            if (right + 1 < nums.length) {
                differenceArray[right + 1] -= val;
            }
        }

        return canMakeZeroArray(differenceArray, nums);
    }

    boolean canMakeZeroArray(int[] differenceArray, int[] nums) {
        int sum = 0;
        // check prefixsum
        for (int i = 0; i < nums.length; i++) {
            sum += differenceArray[i];
            if (sum < nums[i]) {
                return false;
            }
        }

        return true;
    }
}