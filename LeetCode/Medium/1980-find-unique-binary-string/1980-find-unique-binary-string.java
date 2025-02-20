class Solution {
    Set<String> numSet;
    String answer;

    public String findDifferentBinaryString(String[] nums) {
        numSet = new HashSet<>(Arrays.asList(nums));
        int n = nums[0].length();
        backtracking(new StringBuilder(), n);
        return answer;
    }

    // if it finds answer return true.
    boolean backtracking(StringBuilder currentStr, int n) {
        if (currentStr.length() == n) {
            if (!numSet.contains(currentStr.toString())) {
                answer = currentStr.toString();
                return true;
            } else {
                return false;
            }
        }

        for (char c : new char[] { '0', '1' }) {
            currentStr.append(c);
            if (backtracking(currentStr, n)) {
                return true;
            }
            currentStr.setLength(currentStr.length() - 1);
        }

        return false;
    }
}