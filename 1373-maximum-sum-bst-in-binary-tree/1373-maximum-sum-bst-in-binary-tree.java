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
    int maxSum = 0;

    public int maxSumBST(TreeNode root) {
        solve(root);

        return maxSum;    
    }

    private int[] solve(TreeNode root) {
        if (root == null) {
            return new int[] {1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] left = solve(root.left);
        int[] right = solve(root.right);

        if (left[0] == 1 && right[0] == 1 &&
            root.val > left[2] && root.val < right[1]) {
                int sum = left[3] + right[3] + root.val;
                maxSum = Math.max(maxSum, sum);

                int min = Math.min(left[1], root.val);
                int max = Math.max(right[2], root.val);

                return new int[] {1, min, max, sum};
        }

        return new int[] {0, 0, 0, 0};
    }
}