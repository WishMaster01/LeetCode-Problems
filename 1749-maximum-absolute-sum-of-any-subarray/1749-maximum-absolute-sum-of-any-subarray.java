class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int currMax = 0, maxSum = 0;
        int currMin = 0, minSum = 0;

        for (int num : nums) {
            currMax = Math.max(0, currMax + num);
            maxSum = Math.max(maxSum, currMax);

            currMin = Math.min(0, currMin + num);
            minSum = Math.min(minSum, currMin);
        }

        return Math.max(maxSum, -minSum);
    }
}