
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Long> indices = new LinkedList<>();

        queue.offer(root);
        indices.offer(0L);

        long maxWidth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            long minIndex = indices.peek();
            long first = 0, last = 0;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                long index = indices.poll() - minIndex;

                if (i == 0) first = index;
                if (i == size - 1) last = index;

                if (node.left != null) {
                    queue.offer(node.left);
                    indices.offer(2 * index + 1);
                }

                if (node.right != null) {
                    queue.offer(node.right);
                    indices.offer(2 * index + 2);
                }
            }

            maxWidth = Math.max(maxWidth, last - first + 1);
        }

        return (int) maxWidth;
    }
}
