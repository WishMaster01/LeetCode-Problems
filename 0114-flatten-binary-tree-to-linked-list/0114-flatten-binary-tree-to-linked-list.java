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
    public void flatten(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left != null) {
                TreeNode temp = curr.right;
                curr.right = curr.left;
                curr.left = null;

                TreeNode rightMost = getRightMost(curr.right);
                rightMost.right = temp;
            }

            curr = curr.right;
        }
    }

    private TreeNode getRightMost(TreeNode node) {
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }
}