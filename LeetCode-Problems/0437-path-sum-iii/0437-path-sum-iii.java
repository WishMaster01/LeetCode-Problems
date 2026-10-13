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
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, 1);

        return dfs(root, 0L, targetSum, map);
    }

    private int dfs(TreeNode root, long prefixSum, int targetSum, Map<Long, Integer> map) {
        if (root == null) 
            return 0;
        
        prefixSum += root.val;

        int count = map.getOrDefault(prefixSum - targetSum, 0);
        map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);

        count += dfs(root.left, prefixSum, targetSum, map);
        count += dfs(root.right, prefixSum, targetSum, map);

        map.put(prefixSum, map.get(prefixSum) - 1);

        return count;
    }
}