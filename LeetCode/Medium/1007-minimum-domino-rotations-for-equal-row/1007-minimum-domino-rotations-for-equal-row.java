class Solution {
    public int minDominoRotations(int[] tops, int[] bottoms) {
        int n = tops.length;

        // tops[0]을 후보로 사용
        int topCandidate = tops[0];
        int topToTop = countRotations(tops, bottoms, topCandidate, true);
        int topToBottom = countRotations(tops, bottoms, topCandidate, false);

        // bottoms[0]을 후보로 사용
        int bottomCandidate = bottoms[0];
        int bottomToTop = countRotations(tops, bottoms, bottomCandidate, true);
        int bottomToBottom = countRotations(tops, bottoms, bottomCandidate, false);

        // 최소값 계산
        int min = Math.min(Math.min(topToTop, topToBottom),
                Math.min(bottomToTop, bottomToBottom));

        return min == Integer.MAX_VALUE ? -1 : min;
    }

    private int countRotations(int[] tops, int[] bottoms, int candidate, boolean makeTop) {
        int count = 0;

        for (int i = 0; i < tops.length; i++) {
            if (makeTop) {
                // tops를 모두 candidate로 만들기
                if (tops[i] != candidate) {
                    if (bottoms[i] == candidate) {
                        count++;
                    } else {
                        return Integer.MAX_VALUE; // 불가능
                    }
                }
            } else {
                // bottoms를 모두 candidate로 만들기
                if (bottoms[i] != candidate) {
                    if (tops[i] == candidate) {
                        count++;
                    } else {
                        return Integer.MAX_VALUE; // 불가능
                    }
                }
            }
        }

        return count;
    }
}