class Solution {
    public boolean checkInclusion(String s1, String s2) {
        
        int lo = 0;
        int hi = 0;

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> target = initTargetMap(s1);

        int validCount = 0; // valid한 알파벳 갯수 

        while (hi < s2.length()) {
            // expand window size
            char c = s2.charAt(hi);
            hi++;

            // 정보 업데이트
            if (target.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);

                if (window.get(c).equals(target.get(c))) {
                    validCount++;
                }
            }
               
            while (hi - lo >= s1.length()) {
               if (validCount == s1.length()) {
                    return true;
               }

               char willDeleted = s2.charAt(lo);
               lo++;

               if (target.containsKey(willDeleted)) {
                
                if (window.get(willDeleted).equals(target.get(willDeleted))) {
                    validCount--;
                }

                window.put(willDeleted, window.get(willDeleted) - 1);
               }
            }
        }

        return false;
    }

    Map<Character, Integer> initTargetMap(String s) {
        Map<Character, Integer> target = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        return target;
    }
}