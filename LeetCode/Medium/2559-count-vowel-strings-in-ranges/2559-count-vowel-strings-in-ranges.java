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
        boolean[] isVowelWord = new boolean[words.length];
        int[] prefixSum = new int[words.length + 1];

        for (int i = 0; i < words.length; i++) {
            char start = words[i].charAt(0);
            char end = words[i].charAt(words[i].length() - 1);
            if (isVowel(start) && isVowel(end)) {
                isVowelWord[i] = true;
            }
        }

        for (int i = 1; i <= words.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + (isVowelWord[i - 1] ? 1 : 0);
        }

        return prefixSum;
    }

    boolean isVowel(char ch) {
        char[] vowels = new char[] { 'a', 'e', 'i', 'o', 'u' };

        for (char vowel : vowels) {
            if (ch == vowel) {
                return true;
            }
        }

        return false;
    }
}