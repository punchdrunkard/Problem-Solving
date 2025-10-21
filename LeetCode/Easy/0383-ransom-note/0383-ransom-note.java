class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] ransomNoteMap = createCharMap(ransomNote);
        int[] magazineMap = createCharMap(magazine);

        for (int i = 0; i < ransomNoteMap.length; i++) {
            if (ransomNoteMap[i] > magazineMap[i]) {
                return false;
            }
        }

        return true;
    }

    int[] createCharMap(String s) {
        int[] map = new int['z' - 'a' + 1];
        for (char c: s.toCharArray()) {
            map[c - 'a']++;
        }

        return map;
    }
}