class Solution {
    public int longestPalindrome(String s) {
        int answer = 0;

        int[] map = new int['z' - 'A' + 1];
        for (char c : s.toCharArray()) {
            map[c - 'A']++;
        }

        for (int i = 0; i <= 'z' - 'A'; i++) {
            if (map[i] <= 0) {
                continue;
            }

            if (map[i] % 2 == 0) {
                answer += map[i];
                map[i] = 0;
            } else {
                answer += (map[i] - 1);
                map[i] = 1;
            }
        }


        for (int i = 0; i <= 'z' - 'A'; i++) {
            if (map[i] == 1) {
                answer++;
                break;
            }
        }
        
        return answer;
    }
}