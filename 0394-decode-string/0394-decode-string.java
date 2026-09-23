class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();

        int num = 0;

        for(char ch : s.toCharArray()) {
            if(Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }
            else if(ch == '[') {
                countStack.push(num);
                strStack.push(curr);
                num = 0;
                curr = new StringBuilder();
            }
            else if(ch == ']') {
                int time = countStack.pop();
                StringBuilder prev = strStack.pop();

                while(time-- > 0) {
                    prev.append(curr);
                }
                curr = prev;
            }
            else {
                curr.append(ch);
            }
        }

        return curr.toString();
    }
}