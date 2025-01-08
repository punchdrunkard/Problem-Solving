class Solution {

    List<List<Integer>> ans = new LinkedList();

    public List<List<Integer>> combine(int n, int k) {
        dfs(1, n, k, new LinkedList());
        return ans;
    }

    void dfs(int idx, int n, int k, List<Integer> current) {
        if (current.size() == k) {
            // System.out.println(current);
            ans.add(new LinkedList(current));
            return;
        }

        for (int i = idx; i <= n; i++) {
            current.add(i);
            dfs(i + 1, n, k, current);
            current.remove(current.size() - 1);
        }
    }
}