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

    Map<TreeNode, Integer> heightMap = new HashMap<>();

    // 모든 노드를 거치며 찾은 '지름의 최댓값'을 저장할 변수
    int maxDiameter = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        getHeight(root);
        return maxDiameter;
    }

    int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }

        if (heightMap.containsKey(node)) {
            return heightMap.get(node);
        }

        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);

        int currentDiameter = leftHeight + rightHeight;
        maxDiameter = Math.max(maxDiameter, currentDiameter);

        int height = 1 + Math.max(leftHeight, rightHeight);
        heightMap.put(node, height);
        return height;
    }
}