class Solution {
    public int lengthOfLongestSubstring(String s) {
        int answer = 0;

        int n = s.length();
        Set<Character> set = new HashSet<>();

        int lo = 0;
        
        // expand window 
        for (int hi = 0; hi < n; hi++) {
            char newChar = s.charAt(hi);

            // shrink window - 무조건 모든 char가 unique하게 유지 
            while (set.contains(newChar)) {
                char charToRemove = s.charAt(lo);
                set.remove(charToRemove);
                lo++;
            }

            // renew answer
            set.add(newChar);
            answer = Math.max(answer, hi - lo + 1);
        }

        return answer;
    }
}