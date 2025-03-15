class Solution {
    // TODO: k개 이상의 집을 털 수 있는 최소 능력 찾기 
    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        int lo = 0;
        int hi = Arrays.stream(nums).max().getAsInt();
        
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            int robbedCount = 0;

            // 집을 털어 보자 
            // 최소 능력이 mid 인거임
            for (int i = 0; i < n; ++i) {
                if (nums[i] <= mid) {
                    robbedCount++;
                    i++; // skip next house 
                }
            }

            if (!(robbedCount >= k)) {
                lo = mid;
            } else {
                hi = mid;
            }
        }

        return hi;
    }
}