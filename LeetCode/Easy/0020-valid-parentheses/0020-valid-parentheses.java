class Solution {
    public boolean isValid(String s) {
        Deque<Character> stk = new ArrayDeque<>();
        
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put('}', '{');
        pairs.put(']', '[');

        for (char c: s.toCharArray()) {
             if (pairs.containsKey(c)) { // 문자가 닫는 괄호인 경우
                if (stk.isEmpty()) {
                    return false;
                }

                char bracket = stk.pop();

                if (bracket != pairs.get(c)) {
                    return false;
                }
             } else { // 문자가 여는 괄호인 경우
                stk.push(c);
             }
        }

        return stk.isEmpty();
    }
}