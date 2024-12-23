class Solution {
    
    static int[] nums, cache;

    public int rob(int[] nums) {
        init(nums);

        if (nums.length == 1) {
            return nums[0];
        }

        int withoutFirstHouse = dp(1, nums.length - 1);
        Arrays.fill(cache, -1); // cache 초기화
        int withoutLastHouse = dp(0, nums.length - 2);
  
        return Math.max(withoutFirstHouse, withoutLastHouse);
    }
    
    int dp(int start, int end) {
        // base case
        if (start > end) {
            return 0;
        }

        if (cache[start] != -1) {
            return cache[start];
        }

        int selected = dp(start + 2, end) + nums[start];
        int notSelected = dp(start + 1, end);

        cache[start] = Math.max(selected, notSelected);
        return cache[start];
    }

    void init(int[] nums) {
        this.nums = nums;
        cache = new int[nums.length];
        Arrays.fill(cache, -1);
    }
}