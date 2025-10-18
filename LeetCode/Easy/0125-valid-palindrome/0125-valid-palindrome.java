class Solution {
    public boolean isPalindrome(String s) {
        String processed = preprocess(s);

        // two pointer
        int lo = 0;
        int hi = processed.length() - 1;
        while (lo < hi) {
            if (processed.charAt(lo) != processed.charAt(hi)) {
                return false;
            }

            lo++;
            hi--;
        }

        return true;
    }

    String preprocess(String s) { 
        StringBuilder sb = new StringBuilder();
        
        for (char c: s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }

        return sb.toString();
    }
}