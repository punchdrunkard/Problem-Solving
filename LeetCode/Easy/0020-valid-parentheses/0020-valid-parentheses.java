class Solution {
    public boolean isValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        
        for (int i = 0; i < s.length(); i++) {
            // 여는 괄호일 경우 넣는다.
            char c = s.charAt(i);

            if (stk.isEmpty() && !isOpenParentheses(c)) {
                return false;
            }

            if (isOpenParentheses(c)) {
                stk.offerLast(c);
            } else { // 닫는 괄호인 경우
                if (c == '}' && stk.peekLast() == '{') {
                    stk.pollLast();
                    continue;
                } 

                if (c == ')' && stk.peekLast() == '(') {
                    stk.pollLast();
                    continue;
                }

                if (c == ']' && stk.peekLast() == '[') {
                    stk.pollLast();
                    continue;
                }

                stk.offerLast(c);
            }
        }

        return stk.isEmpty();
    }

    boolean isOpenParentheses(char c) {
        return c == '(' || c == '[' || c == '{';
    }
}