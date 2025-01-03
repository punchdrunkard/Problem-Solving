class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int[] prefixSum = calcuatePrefixSum(words);
        int[] answer = findAnswer(prefixSum, queries);
        return answer;
    }

    int[] findAnswer(int[] prefixSum, int[][] queries) {
        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int leftBound = queries[i][0];
            int rightBound = queries[i][1];

            ans[i] = prefixSum[rightBound + 1] - prefixSum[leftBound];
        }

        return ans;
    }

    int[] calcuatePrefixSum(String[] words) {
        int[] prefixSum = new int[words.length + 1];

        for (int i = 1; i <= words.length; i++) {
            prefixSum[i] = prefixSum[i - 1]
                    + (isVowelWords(words[i - 1]) ? 1 : 0);
        }

        return prefixSum;
    }

    boolean isVowelWords(String word) {
        return isVowel(word.charAt(0)) && isVowel(word.charAt(word.length() - 1));
    }

    boolean isVowel(char ch) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        return vowels.contains(ch);
    }
}