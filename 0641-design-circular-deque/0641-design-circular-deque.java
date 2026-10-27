class MyCircularDeque {
    private int[] arr;
    private int front;
    private int size;
    private int capacity;

    public MyCircularDeque(int k) {
        arr = new int[k];
        capacity = k;
        size = 0;
        front = 0;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }

        front = (front - 1 + capacity) % capacity;
        arr[front] = value;
        size++;

        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        int rear = (front + size) % capacity;
        arr[rear] = value;
        size++;

        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % capacity;
        size--;

        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        size--;

        return true;
    }
    
    public int getFront() {
        return isEmpty() ? -1 : arr[front];
    }
    
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }

        int rear = (front + size - 1) % capacity;

        return arr[rear];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */