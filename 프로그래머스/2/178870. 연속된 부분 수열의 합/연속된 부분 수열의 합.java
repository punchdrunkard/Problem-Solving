import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        // sliding window!
        int[] answer = new int[2];

        int minLength = Integer.MAX_VALUE;

        int start = 0;
        int sum = 0;

        for (int end = 0; end < sequence.length; end++) {
            // expand window 
            sum += sequence[end];

            // shrink window
            while (start <= end && sum >= k) {
                // 정답 업데이트
                if (sum == k) {
                    if (end - start + 1 < minLength) {
                        answer[0] = start;
                        answer[1] = end;
                        minLength = end - start + 1;
                    }
                }

                sum -= sequence[start];
                start++;
            }
        }

        return answer;
    }

    void sout(Object o) {
        System.out.println(o);
    }
}