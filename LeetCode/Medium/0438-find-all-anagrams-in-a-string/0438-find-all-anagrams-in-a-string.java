class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> answer = new ArrayList<>();

        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> need = initNeedMap(p);

        int lo = 0;
        int hi = 0;
        int validCount = 0;

        while (hi < s.length()) {
            char willAdd = s.charAt(hi);
            hi++;

            if (need.containsKey(willAdd)) {
                window.put(willAdd, window.getOrDefault(willAdd, 0) + 1);

                if (window.get(willAdd).equals(need.get(willAdd))) {
                    validCount++;
                }
            }

            while (hi - lo >= p.length()) {
                if (validCount == need.size()) {
                    answer.add(lo);
                }

                char willDelete = s.charAt(lo);
                lo++;

                if (need.containsKey(willDelete)) {
                    if (window.getOrDefault(willDelete, 0).equals(need.get(willDelete))) {
                        validCount--;
                    }
                    
                    window.put(willDelete, window.getOrDefault(willDelete, 0) - 1);
                }
            }
        }

        return answer;
    }

    Map<Character, Integer> initNeedMap(String p) {
        Map<Character, Integer> need = new HashMap<>();

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);

            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        return need;
    }
}