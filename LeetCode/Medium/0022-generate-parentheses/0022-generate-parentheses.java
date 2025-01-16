class Solution {

    List<String> answer = new ArrayList<>();
    int n;

    public List<String> generateParenthesis(int n) {
        this.n = n;
        generateString(0, 0, "");
        return answer;
    }

    void generateString(int openCount, int closeCount, String current) {
        
        if (openCount == n && closeCount == n) {
            answer.add(current);
            System.out.println(current);
            return;
        }

        if (openCount < n) {
            generateString(openCount + 1, closeCount, current + "(");
        }

        if (openCount > closeCount) {
            generateString(openCount, closeCount +  1, current + ")");
        }
    }


}