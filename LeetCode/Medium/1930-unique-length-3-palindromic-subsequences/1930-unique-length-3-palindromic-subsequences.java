class Solution {
    public int countPalindromicSubsequence(String s) {
        // 3-palindrom string -> start == end => find distinct middle letter

        // compute each letter's first and last indices => O(n)
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';

            if (first[index] == -1) {
                first[index] = i;
            }

            last[index] = i;
        }

        // count letters in between => O(26N)
        int answer = 0;

        for (int i = 0; i < 26; i++) {
            if (first[i] == -1) { // 등장하지 않은 알파벳 
                continue;
            }

            Set<Character> between = new HashSet<>();
            for (int j = first[i] + 1; j < last[i]; j++) {
                between.add(s.charAt(j));
            }

            answer += between.size();
        }

        return answer;
    }
}