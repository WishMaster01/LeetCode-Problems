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
    public TreeNode canMerge(List<TreeNode> trees) {
        Map<Integer, TreeNode> roots = new HashMap<>();
        Set<Integer> leaves = new HashSet<>();

        for (TreeNode tree : trees) {
            roots.put(tree.val, tree);
            if (tree.left != null) {
                leaves.add(tree.left.val);
            }
            if (tree.right != null) {
                leaves.add(tree.right.val);
            }
        }

        TreeNode root = null;

        for (TreeNode tree : trees) {
            if (!leaves.contains(tree.val)) {
                if (root != null) {
                    return null;
                }
                root = tree;
            }
        }

        if (root == null) {
            return null;
        }

        roots.remove(root.val);
        if (!merge(root, Long.MIN_VALUE, Long.MAX_VALUE, roots)) {
            return null;
        }

        if (!roots.isEmpty()) {
            return null;
        }

        return root;
    }

    private boolean merge(TreeNode node, long min, long max, Map<Integer, TreeNode> roots) {
        if (node == null) {
            return true;
        }

        if (node.val <= min || node.val >= max) {
            return false;
        }

        if (node.left == null && node.right == null) {
            TreeNode tree = roots.remove(node.val);
            if (tree != null) {
                node.left = tree.left;
                node.right = tree.right;
            }
        }

        return merge(node.left, min, node.val, roots) 
            && merge(node.right, node.val, max, roots);
    }
}