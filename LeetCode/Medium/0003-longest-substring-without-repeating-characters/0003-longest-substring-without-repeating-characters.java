class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> lastSeenAt = new HashMap<>();
        int windowStart = 0;
        int maxLength = 0;

        for (int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            char currentChar = s.charAt(windowEnd);
            
            // 현재 문자가 윈도우 내에 이미 존재하는 경우, 윈도우 시작점 갱신
            if (lastSeenAt.containsKey(currentChar) && lastSeenAt.get(currentChar) >= windowStart) {
                windowStart = lastSeenAt.get(currentChar) + 1;
            }
            
            // 현재 문자의 위치 저장
            lastSeenAt.put(currentChar, windowEnd);
            
            // 최대 길이 갱신
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        
        return maxLength;
    }
}