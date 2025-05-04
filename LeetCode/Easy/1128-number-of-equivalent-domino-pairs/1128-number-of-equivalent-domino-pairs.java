class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int answer = 0;

        int[] counter = new int[101]; // 1 <= dominoes[i][j] <= 9
        for (int[] domino : dominoes) {
            int number = 10 * Math.min(domino[0], domino[1])
                    + Math.max(domino[0], domino[1]);

            counter[number]++;
        }

        for (int i = 1; i < 100; i++) {
            if (counter[i] > 1) {
                if (counter[i] == 2) {
                    answer++;
                } else {
                    answer += counter[i];
                }
            }
        }

        return answer;
    }
}