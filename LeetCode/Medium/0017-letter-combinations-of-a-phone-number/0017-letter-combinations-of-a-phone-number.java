class Solution {
    List<String> answer = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        List<String>[] mapping = mapDigits();
        Map<String, Boolean> visited = new HashMap<>();
        findLetterCombinations(0, digits, mapping, "", visited);

        // System.out.print("answer: " + answer);
        return answer;
    }

    void findLetterCombinations(int idx, String currentSeq, List<String>[] mapping, String current,
            Map<String, Boolean> visited) {
        // base case
        if (idx >= currentSeq.length()) {
            if (!current.isEmpty()) {
                answer.add(current);
            }

            return;
        }

        for (int i = idx; i < currentSeq.length(); i++) {
            int currentDigit = currentSeq.charAt(idx) - '0';

            // 현재 idx 에 해당되는 letter 들을 모두 포함함
            for (int charIdx = 0; charIdx < mapping[currentDigit].size(); charIdx++) {
                String currentLetter = mapping[currentDigit].get(charIdx);
                String next = current + currentLetter;
                if (visited.getOrDefault(next, false)) {
                    continue;
                }

                visited.put(next, true);
                findLetterCombinations(idx + 1, currentSeq, mapping, next, visited);
            }
        }
    }

    List<String>[] mapDigits() {
        List<String>[] mapping = new List[10];

        mapping[2] = List.of("a", "b", "c");
        mapping[3] = List.of("d", "e", "f");
        mapping[4] = List.of("g", "h", "i");
        mapping[5] = List.of("j", "k", "l");
        mapping[6] = List.of("m", "n", "o");
        mapping[7] = List.of("p", "q", "r", "s");
        mapping[8] = List.of("t", "u", "v");
        mapping[9] = List.of("w", "x", "y", "z");

        return mapping;
    }

}