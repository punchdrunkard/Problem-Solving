class Solution {
    // key에 해당되는 값을 삭제 후, 남은 트리를 반환
    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) {
            return null;
        }

        // 그 root를 삭제한다.
        if (root.val == key) {
            // 자식이 하나만 있는 경우 
            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            // 양쪽 자식이 있는 경우 => 해당 값을 오른쪽 트리의 최솟값으로 대체
            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        } else if (root.val > key) { // 삭제할 위치를 찾음
            root.left = deleteNode(root.left, key);
        } else { // root.val < key
            root.right = deleteNode(root.right, key);
        }

        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}
