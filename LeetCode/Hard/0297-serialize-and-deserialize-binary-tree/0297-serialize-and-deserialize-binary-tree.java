/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    private final String EMPTY_DATA = "[]";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<Integer> res = bfs(root);
        return res.toString().replace(" ", "");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals(EMPTY_DATA)) {
            return null;
        }

        List<Integer> values = parseToList(data);

        Queue<TreeNode> q = new LinkedList<>();
        TreeNode root = new TreeNode(values.getFirst());
        q.add(root);

        for (int i = 1; i < values.size(); i += 2) {
            TreeNode parent = q.poll();

            int left = i;
            int right = i + 1;

            // left
            if (isValidRange(left, values.size()) && values.get(left) != null) {
                TreeNode leftChild = new TreeNode(values.get(left));
                parent.left = leftChild;
                q.add(leftChild);
            }

            if (isValidRange(right, values.size()) && values.get(right) != null) {
                TreeNode rightChild = new TreeNode(values.get(right));
                parent.right = rightChild;
                q.add(rightChild);
            }
        }

        return root;
    }

    private boolean isValidRange(int i, int limit) {
        return i < limit;
    }

    private static List<Integer> bfs(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }

        List<Integer> result = new LinkedList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                if (node == null) {
                    result.add(null);
                    continue;
                }

                result.add(node.val);
                q.offer(node.left);
                q.offer(node.right);
            }
        } // end of while

        // remove last null node
        while (result.get(result.size() - 1) == null && !result.isEmpty()) {
            result.removeLast();
        }

        return result;
    }

    private static List<Integer> parseToList(String data) {
        // 1. 문자열 전처리: 대괄호 제거 및 공백 제거
        data = data.replaceAll("[\\[\\]]", "").trim(); // "[" 및 "]" 제거

        // 2. 쉼표 기준으로 나누기
        String[] values = data.split(",");

        // 3. 리스트 변환
        List<Integer> list = new ArrayList<>();
        for (String value : values) {
            if (value.equals("null")) { // "null" 문자열 처리
                list.add(null);
            } else { // 숫자 변환
                list.add(Integer.parseInt(value));
            }
        }
        return list;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));