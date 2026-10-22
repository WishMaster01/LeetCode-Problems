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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        HashMap<TreeNode, TreeNode> parent = new HashMap<>();
        buildParent(root, null, parent);

        HashSet<TreeNode> ancestors = new HashSet<>();

        while (p != null) {
            ancestors.add(p);
            p = parent.get(p);
        }

        while (!ancestors.contains(q)) {
            q = parent.get(q);
        }

        return q;
    }

    private void buildParent(TreeNode root, TreeNode par, HashMap<TreeNode, TreeNode> parent) {
        if (root == null) return;

        parent.put(root, par);
        buildParent(root.left, root, parent);
        buildParent(root.right, root, parent);
    }
}