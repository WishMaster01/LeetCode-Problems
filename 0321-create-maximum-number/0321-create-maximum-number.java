class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];
        
        int start = Math.max(0, k - nums2.length);
        int end = Math.min(k, nums1.length);

        for(int i = start; i <= end; i++) {
            int[] part1 = maxSubsequence(nums1, i);
            int[] part2 = maxSubsequence(nums2, k - i);

            int[] merged = merge(part1, part2);
            if(greater(merged, 0, result, 0)) {
                result = merged;
            }
        }

        return result;
    }

    private int[] maxSubsequence(int[] nums, int k) {
        int[] stack = new int[k];
        int top = 0;
        int remove = nums.length - k;

        for(int num : nums) {
            while(top > 0 && remove > 0 && stack[top - 1] < num) {
                top--;
                remove--;
            }  

            if(top < k) {
                stack[top++] = num;
            }
            else {
                remove--;
            }
        }

        return stack;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];

        int i = 0;
        int j = 0;
        int index = 0;

        while(i < nums1.length || j < nums2.length) {
            if(greater(nums1, i, nums2, j)) {
                result[index++] = nums1[i++];
            }
            else {
                result[index++] = nums2[j++];
            }
        }

        return result;
    }

    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while(i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }

        if(j == nums2.length) {
            return true;
        }

        if(i == nums1.length) {
            return false;
        }

        return nums1[i] > nums2[j];
    }
}