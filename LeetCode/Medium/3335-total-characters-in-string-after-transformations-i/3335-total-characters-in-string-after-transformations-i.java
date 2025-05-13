class Solution {
    static final int MOD = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t) {
        int[] frequencyMap = initializeFrequencyMap(s);

        for (int i = 0; i < t; i++) {
            int[] temp = new int[26];
            for (int j = 0; j < 25; j++) {
                if (frequencyMap[j] > 0) {
                    temp[j + 1] = (frequencyMap[j] % MOD);
                }
            }

            if (frequencyMap[25] > 0) {
                temp[0] += (frequencyMap[25] % MOD);
                temp[1] += (frequencyMap[25] % MOD);
            }

            frequencyMap = temp;
        }

        int answer = countLength(frequencyMap);
        return answer;
    }

    int countLength(int[] frequencyMap) {
        int count = 0;
        for (int i = 0; i < 26; i++) {
            count += (frequencyMap[i] % MOD);
            count %= MOD;
        }
        return count % MOD;
    }

    int[] initializeFrequencyMap(String s) {
        int[] frequencyMap = new int[26];
        for (int i = 0; i < s.length(); i++) {
            frequencyMap[s.charAt(i) - 'a']++;
        }
        return frequencyMap;
    }
}