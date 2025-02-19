class Solution {

    List<String> happyStrings = new ArrayList<>();
    char[] letters = new char[] { 'a', 'b', 'c' };

    public String getHappyString(int n, int k) {
        dp(new StringBuilder(), n);
        return happyStrings.size() < k ? "" : happyStrings.get(k - 1);
    }

    void dp(StringBuilder current, int n) {
        if (current.length() == n) {
            happyStrings.add(current.toString());
            return;
        }

        for (int i = 0; i < 3; i++) {
            if (current.length() == 0) {
                current.append(letters[i]);
                dp(current, n);
                current.setLength(current.length() - 1);
            } else {
                char lastChar = current.charAt(current.length() - 1);

                if (letters[i] == lastChar) {
                    continue;
                } else {
                    current.append(letters[i]);
                    dp(current, n);
                    current.setLength(current.length() - 1);
                }
            }
        }
    }
}