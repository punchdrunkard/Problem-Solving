class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int n = digits.length;
        Set<Integer> set = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            if (digits[i] == 0) { // The integer does not have leading zeros.
                continue;
            }

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (i == j || k == j || i == k) {
                        continue;
                    }

                    int num = 100 * digits[i] + 10 * digits[j] + digits[k];
                    if (num % 2 == 0) {
                        if (!set.contains(num)) {
                            set.add(num);
                        }
                    }
                }
            }
        }

        int[] answer = set.stream().mapToInt(Integer::intValue).toArray();
        return answer;
    }
}