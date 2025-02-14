import java.util.*;

class Solution {
    public int calculateHelper(String s, int[] pos) {
        Deque<Integer> stk = new ArrayDeque<>();
        char sign = '+';
        int num = 0;

        while (pos[0] < s.length()) {
            char c = s.charAt(pos[0]);

            if (Character.isDigit(c)) {
                num = (num * 10) + (c - '0'); 
            }

            if (c == '(') {
                pos[0]++; 
                num = calculateHelper(s, pos); 
            }

            if (isSign(c) || c == ')' || pos[0] == s.length() - 1) {
                if (sign == '+') {
                    stk.offerLast(num);
                } else if (sign == '-') {
                    stk.offerLast(-num);
                }

                sign = c; 
                num = 0;  

                if (c == ')') { 
                    break;
                }
            }
            
            pos[0]++; 
        }

        return stk.stream().mapToInt(Integer::intValue).sum();
    }

    public int calculate(String s) {
        return calculateHelper(s, new int[]{0});
    }

    public boolean isSign(char c) {
        return c == '+' || c == '-';
    }
}
