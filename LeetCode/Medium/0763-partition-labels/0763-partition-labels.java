class Solution {

    // 각 문자의 첫 등장 idnex 와 마지막 등장 index 를 기록 
    // => 이를 "구간" 처럼 생각해서, 겹치는 "구간" 이 있다면 merge 
    public List<Integer> partitionLabels(String s) {

        // occurence[i][0] := i의 첫 등장 index
        // occurence[i][1] := i의 마지막 등장 index 
        int[][] occurrence = new int[26][2];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(occurrence[i], -1);
        }

        for (int i = 0; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 'a';
            if (occurrence[charIndex][0] == -1) {
                occurrence[charIndex][0] = i;
                occurrence[charIndex][1] = i;
            } else {
                occurrence[charIndex][1] = i;
            }
        }

        // merge intervals
        Arrays.sort(occurrence, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> intervals = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if (occurrence[i][0] == -1) {
                continue;
            }

            // 새로운 구간을 시작한다.
            if (intervals.isEmpty()) {
                intervals.add(occurrence[i]);
                continue;
            }

            int lastIntervalLastIndex = intervals.getLast()[1];
            int currentRangeFirstIndex = occurrence[i][0];

            if (currentRangeFirstIndex < lastIntervalLastIndex) {
                // merge
                intervals.getLast()[1] = Math.max(lastIntervalLastIndex, occurrence[i][1]);
            } else {
                intervals.add(occurrence[i]);
            }
        }

        return intervals.stream()
                .map(interval -> interval[1] - interval[0] + 1)
                .collect(Collectors.toList());
    }
}