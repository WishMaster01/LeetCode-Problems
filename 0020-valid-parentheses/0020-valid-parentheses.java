class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        for(char ch : s.toCharArray()) {
            if(map.containsKey(ch)) {
                if(stack.isEmpty() || stack.pop() != map.get(ch)) {
                    return false;
                }
            }
            else {
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}