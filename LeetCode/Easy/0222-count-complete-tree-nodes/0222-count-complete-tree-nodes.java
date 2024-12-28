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
    public int countNodes(TreeNode root) {
        int lh = findLeftHeight(root);
        int rh = findRightHeight(root);

        if (lh == rh) {
            return countNodesInPerfectBinaryTree(lh);
        }

        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private int countNodesInPerfectBinaryTree(int h) {
        return (1 << h) - 1;
    }

    private int findLeftHeight(TreeNode root) {
        int h = 0;

        while (root != null) {
            root = root.left;
            h++;
        }

        return h;
    }

    private int findRightHeight(TreeNode root) {
        int h = 0;

        while (root != null) {
            root = root.right;
            h++;
        }

        return h;
    }
}