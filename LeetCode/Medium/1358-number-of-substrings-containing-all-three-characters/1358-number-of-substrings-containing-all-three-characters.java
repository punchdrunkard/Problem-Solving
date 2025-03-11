class Solution {
    public int numberOfSubstrings(String s) {
        int answer = 0;

        int left = 0;
        int right = 0;
        int[] freq = new int[3];
        
        while (right < s.length()) {
            // add new element (expand the window)
            char newCharacter = s.charAt(right);
            freq[newCharacter - 'a']++;

            // if you find the answer
            while (doesFindAnswer(freq)) {
                answer += (s.length() - right);
    
                // move window
                char removedElement = s.charAt(left);
                freq[removedElement - 'a']--;
                left++;
            }

            right++;
        }

        return answer;
    }

    boolean doesFindAnswer(int[] freq) {
        return freq[0] > 0 && freq[1] > 0 && freq[2] > 0;
    }
}