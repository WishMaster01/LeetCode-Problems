class MinStack {

    Stack<Long> stack;
    long min;

    public MinStack() {
        stack = new Stack<>();
    }
    
    public void push(int value) {
        if(stack.isEmpty()) {
            stack.push((long) value);
            min = value;
        }
        else if(value < min) {
            stack.push(2L * value - min);
            min = value;
        }
        else {
            stack.push((long)value);
        }
    }
    
    public void pop() {
        if(stack.isEmpty()) return;

        long top = stack.pop();
        if(top < min) {
            min = 2L * min - top;
        }
    }
    
    public int top() {
        if(stack.isEmpty()) return -1;

        long top = stack.peek();
        return (top < min) ? (int) min : (int) top;
    }
    
    public int getMin() {
        if(stack.isEmpty()) return -1;
        return (int) min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(value);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */