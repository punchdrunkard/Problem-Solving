class Solution {
    
    int[] nums;
    List<List<Integer>> ans = new LinkedList();
    boolean[] selected;

    public List<List<Integer>> permute(int[] nums) {
        this.nums = nums;
        selected = new boolean[nums.length];

        dfs(-1, new LinkedList());
        return ans; 
    }

    void dfs(int pre, List<Integer> current) {
        if (current.size() == nums.length) {
            // System.out.println(current);
            ans.add(new LinkedList(current));
            return;
        }


        for (int i = 0; i < nums.length; i++) {
            // prevent duplicate selection
            if (i == pre) { 
                continue;
            }

            if (selected[i]) {
                continue;
            }

            current.add(nums[i]);
            selected[i] = true;
            dfs(i, current);
            current.remove(current.size() - 1);
            selected[i] = false;
        }
    }
}