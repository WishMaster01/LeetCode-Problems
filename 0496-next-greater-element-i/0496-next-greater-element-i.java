class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        for(int i = nums2.length - 1; i >= 0; i--) {
            int curr = nums2[i];

            while(!stack.isEmpty() && stack.peek() <= curr) {
                stack.pop();
            }

            if(stack.isEmpty()) {
                map.put(curr, -1);
            } 
            else {
                map.put(curr, stack.peek());
            }

            stack.push(curr);
        }

        int[] res = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }

        return res;
    }
}