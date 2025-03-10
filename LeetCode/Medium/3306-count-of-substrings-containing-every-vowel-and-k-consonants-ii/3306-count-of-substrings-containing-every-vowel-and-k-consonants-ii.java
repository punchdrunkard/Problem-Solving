class Solution {
    public long countOfSubstrings(String word, int k) {
        long answer = 0;

        // two pointers of sliding window
        int start = 0;
        int end = 0;

        // for tracking
        Set<Character> vowels = new HashSet<>();
        int consonantCount = 0;

        int[] nextConsonant = new int[word.length()];
        int nextConsonantIndex = word.length();
        for (int i = word.length() - 1; i >= 0; i--) {
            nextConsonant[i] = nextConsonantIndex;
            if (!isVowel(word.charAt(i))) {
                nextConsonantIndex = i;
            }
        }

        // start sliding window
        while (end < word.length()) {
            // insert new letter
            char newLetter = word.charAt(end);

            // update counts
            if (isVowel(newLetter)) {
                vowels.add(newLetter);
            } else {
                consonantCount++;
            }

            // need to shrink
            while (consonantCount > k) {
                char startLetter = word.charAt(start);
                if (isVowel(startLetter)) {
                    vowels.remove(startLetter);
                } else {
                    consonantCount--;
                }

                start++;
            }

            // while we have valid window
            while (start < word.length() &&
                    vowels.size() == 5 &&
                    consonantCount == k) {
                answer += nextConsonant[end] - end;

                char startLetter = word.charAt(start);
                if (isVowel(startLetter)) {
                    vowels.remove(startLetter);
                } else {
                    consonantCount--;
                }

                start++;
            }

            end++;
        }

        return answer;
    }

    boolean isVowel(char c) {
        return c == 'a' || c == 'o' || c == 'u' || c == 'e' || c == 'i';
    }
}