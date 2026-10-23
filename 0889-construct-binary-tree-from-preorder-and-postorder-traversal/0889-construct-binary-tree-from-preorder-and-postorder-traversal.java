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

    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            map.put(postorder[i], i);
        }    

        return build(preorder, 0, preorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd, int[] postorder, int postStart, int postEnd) {
        if (preStart > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);

        if (preStart == preEnd) {
            return root;
        }

        int leftRoot = preorder[preStart + 1];
        int idx = map.get(leftRoot);
        int leftSize = idx - postStart + 1;

        root.left = build(preorder, preStart + 1, preStart + leftSize, postorder, postStart, idx);
        root.right = build(preorder, preStart + leftSize + 1, preEnd, postorder, idx + 1, postEnd - 1);

        return root;
    }
}