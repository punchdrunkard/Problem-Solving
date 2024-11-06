class Solution {

    static final int INF = Integer.MAX_VALUE;

    public String minWindow(String s, String t) {

        Map<Character, Integer> need = getAlphabetCounter(t);
        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int right = 0;

        // 답 업데이트를 위한 상태
        int start = 0;
        int len = INF;
        
        int valid = 0; // window 에서 조건을 만족하는 문자의 수 

        // expand window
        while (right < s.length()) {
            
            // 현재 알파벳 구성을 나타내는 맵 업데이트
            char current = s.charAt(right);

            right++;

            // 윈도우 내 데이터 업데이트
            if (need.containsKey(current)) {
                window.put(current, window.getOrDefault(current, 0) + 1);

                if (window.getOrDefault(current, 0) == need.get(current)) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                // 답 업데이트
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                // 윈도우 밖으로 이동하게 되는 문자
                char outdated = s.charAt(left);

                // window 왼쪽으로 이동
                left++;

                // 데이터 업데이트
                if (need.containsKey(outdated)) {
                    if (window.get(outdated) == need.get(outdated)) {
                        valid--;
                    }

                    window.put(outdated, window.get(outdated) - 1);
                }       
            }
        }

        return len == INF ? "" : s.substring(start, start + len);
    }

    Map<Character, Integer> getAlphabetCounter(String t) {
        Map<Character, Integer> alphaMap = new HashMap<>();
        for (char c: t.toCharArray()) {
            alphaMap.put(c, alphaMap.getOrDefault(c, 0) + 1);
        }

        return alphaMap;
    }
}