/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    TreeNode root;
    Map<TreeNode, Integer> cache;

    public int rob(TreeNode root) {
        this.root = root;
        cache = new HashMap<>();
        return dp(root);
    }

    int dp(TreeNode current) {
        if (current == null) {
            return 0;
        }

        if (cache.containsKey(current)) {
            return cache.get(current);
        }

        TreeNode left = current.left;
        TreeNode right = current.right;

        int selected = current.val 
            + (left == null ? 0 : dp(left.left) + dp(left.right)) 
            + (right == null ? 0 : dp(right.left) + dp(right.right));

        int notSelected = dp(current.left) + dp(current.right);

        cache.put(current, Math.max(selected, notSelected));
        return cache.get(current);
    }
}