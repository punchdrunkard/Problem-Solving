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
    public int minDepth(TreeNode root) {
        return bfs(root);
    }

    int bfs(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> q = new LinkedList<>();
        
        // 어짜피 거슬러 올라갈 수 없기 때문에, visited 생략이 가능
        q.offer(root);
        int depth = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();

                // 도착한 경우 => 현재 노드가 리프노드
                if (cur.left == null && cur.right == null) {
                    return depth;
                }

                // 다음 방향으로 탐색 => 해당 노드의 자식들에 대하여
                if (cur.left != null) {
                    q.offer(cur.left);
                }
            
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            
            depth++;
        }

        return depth;
    }
}