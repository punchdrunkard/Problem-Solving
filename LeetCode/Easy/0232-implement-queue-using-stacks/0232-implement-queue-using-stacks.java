class MyQueue {
    
    Deque<Integer> stkIn = new ArrayDeque<>();
    Deque<Integer> stkOut = new ArrayDeque<>();

    public MyQueue() {
    }
    
    public void push(int x) {
        stkIn.push(x);
    }
    
    public int pop() {
        fillStackOut();
        return stkOut.pop();
    }
    
    public int peek() {
        fillStackOut();
        return stkOut.peek();
    }
    
    public boolean empty() {
        return stkIn.isEmpty() && stkOut.isEmpty();
    }

    public boolean fillStackOut() {
        if (!stkOut.isEmpty()) {
            return false;
        }

        if (stkOut.isEmpty()) {
            while (!stkIn.isEmpty()) {
                stkOut.push(stkIn.pop());
            }
        }

        return true;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */