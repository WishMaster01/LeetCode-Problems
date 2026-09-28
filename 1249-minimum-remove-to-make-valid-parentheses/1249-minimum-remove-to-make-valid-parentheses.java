class Solution {
    public String minRemoveToMakeValid(String s) {
        StringBuilder first = new StringBuilder();
        int open = 0;

        for(char ch : s.toCharArray()) {
            if(ch == '(') {
                open++;
                first.append(ch);
            }
            else if(ch == ')') {
                if(open > 0) {
                    open--;
                    first.append(ch);
                }
            }
            else {
                first.append(ch);
            }
        }

        StringBuilder ans = new StringBuilder();
        int close = 0;

        for(int i = first.length() - 1; i >= 0; i--) {
            char ch = first.charAt(i);
            if(ch == ')') {
                close++;
                ans.append(ch);
            }
            else if(ch == '(') {
                if(close > 0) {
                    close--;
                    ans.append(ch);
                }
            }
            else {
                ans.append(ch);
            }
        }

        return ans.reverse().toString();
    }
}