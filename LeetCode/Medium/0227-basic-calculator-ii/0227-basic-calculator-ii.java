class Solution {
    public int calculateHelper(Deque<Character> strDeque) {
        Deque<Integer> stk = new ArrayDeque<>();
        char sign = '+'; 
        int current = 0;

        while (!strDeque.isEmpty()) {
            char c = strDeque.poll();

            if (Character.isDigit(c)) {
                current = (current * 10) + (c - '0');
            }

            if (!Character.isDigit(c) || strDeque.isEmpty()) {
                if (sign == '+') {
                    stk.offerLast(current);
                } else if (sign == '-') {
                    stk.offerLast(-current);
                } else if (sign == '*') {
                    stk.offerLast(stk.pollLast() * current); 
                } else if (sign == '/') {
                    stk.offerLast(stk.pollLast() / current); 
                }

                sign = c; 
                current = 0; 
            }
        }

        return stk.stream().mapToInt(Integer::intValue).sum(); 
    }

    public int calculate(String s) {
        return calculateHelper(strToDeque(s));
    }

    private Deque<Character> strToDeque(String s) {
        Deque<Character> deque = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c != ' ') { 
                deque.offerLast(c);
            }
        }
        return deque;
    }
}
