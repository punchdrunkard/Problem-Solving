class Solution {

    int[] nums;
    List<List<Integer>> ans = new ArrayList();

    public List<List<Integer>> subsets(int[] nums) {
        this.nums = nums;

        for (int size = 0; size <= nums.length; size++) {
            dfs(0, size, new ArrayList());
        }

        return ans;
    }

    void dfs(int i, int size, List<Integer> current) {
        if (current.size() == size) {
            // System.out.println(current);
            ans.add(new ArrayList<>(current));
            // return current;
        }

        for (int idx = i; idx < nums.length; idx++) {
            current.add(nums[idx]);
            dfs(idx + 1, size, current);
            current.remove(current.size() - 1);
        }
    }
}