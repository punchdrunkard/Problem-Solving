class Solution {
    // bags 의 총 cost := 첫 원소 + 마지막 원소 + (경계의 합)
    // 따라서 만들어질 수 있는 경계들(n - 1)을 계산 한 후, (k - 1)개를 선택하면 됨 
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        long[] boundaries = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            boundaries[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(boundaries);

        // max -> boundaries 에서 뒤에서부터 (k - 1)개 합한 값
        long max = weights[0] + weights[n - 1];
        for (int i = 0; i < k - 1; i++) {
            max += boundaries[n - 2 - i];
        }

        // min -> boundaries 에서 앞에서부터 (k - 1)개 합한 값
        long min = weights[0] + weights[n - 1];
        for (int i = 0; i < k - 1; i++) {
            min += boundaries[i];
        }

        return max - min;
    }
}