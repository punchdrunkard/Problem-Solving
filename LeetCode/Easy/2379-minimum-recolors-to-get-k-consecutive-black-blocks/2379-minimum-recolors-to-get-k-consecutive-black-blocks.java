class Solution {
    static final char WHITE = 'W';
    static final char BLACK = 'B';

    // n = 100 -> brute force
    public int minimumRecolors(String blocks, int k) {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < blocks.length(); i++) {
            int blackCount = 0; // number of consecutive black blocks
            int recolored = 0;

            if (i + k > blocks.length()) {
                continue;
            }

            for (int j = i; j < i + k; j++) {
                if (blocks.charAt(j) == WHITE) {
                    recolored++;
                }
                blackCount++;
            }

            if (blackCount == k) {
                min = Math.min(min, recolored);
            }
        }

        return min;
    }
}