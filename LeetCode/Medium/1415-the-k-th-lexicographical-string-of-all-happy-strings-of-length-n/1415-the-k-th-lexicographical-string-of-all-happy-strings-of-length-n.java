class Solution {

    int count = 0;
    String result = "";

    public String getHappyString(int n, int k) {
        backtrack(new StringBuilder(), n, k);
        return result;
    }

    void backtrack(StringBuilder current, int n, int k) {
        if (current.length() == n) {
            count++;

            if (count == k) {
                result = current.toString();
            }

            return;
        }

        for (char letter : new char[] { 'a', 'b', 'c' }) {
            if (current.isEmpty() || current.charAt(current.length() - 1) != letter) {
                backtrack(current.append(letter), n, k);
                current.setLength(current.length() - 1);
                if (!result.isEmpty()) {
                    // terminate if result is found.
                    return;
                }
            }
        }
    }
}