/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    // 주어지는 root 안에 p와 q가 존재해야 하고, root를 좁혀나가는 것 
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // base case
        if (root == null || p == root || q == root) {
            return root;
        }

        // 왼쪽 서브트리와 오른쪽 서브트리에 p, q가 존재하나?
        TreeNode left = lowestCommonAncestor(root.left, p, q); 
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left == null && right == null) {
            return null;
        }
        
        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }
}