/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    // val이 들어간 노드가 들어갈 위치 TreeNode를 찾는다. 
    public TreeNode insertIntoBST(TreeNode root, int val) {

        if (root == null) {
            return new TreeNode(val);
        }

        if (root.val == val) {
            return root;
        }

        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }

        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }

        return root;
    }
}