class Solution {
    List<String> answer = new ArrayList<>();
    int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        generateParenthesisHelper(0, 0, new StringBuilder());
        return answer;
    }
    
    // well-formed parentheses : always open >= close
    void generateParenthesisHelper(int openCount, int closeCount, StringBuilder current) {
        if (openCount < closeCount) {
            return;
        }

        if (openCount == n && closeCount == n) {
            answer.add(current.toString());
            return;    
        }
        
        if (openCount < n) {
            current.append('(');
            generateParenthesisHelper(openCount + 1, closeCount, current);
            current.setLength(current.length() - 1);
        }

        if (openCount > closeCount) {
            current.append(')');
            generateParenthesisHelper(openCount, closeCount + 1, current);
            current.setLength(current.length() - 1);
        }
    }
}