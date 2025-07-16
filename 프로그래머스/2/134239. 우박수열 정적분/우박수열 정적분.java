import java.util.*;

// prefix sum 
class Solution {
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        int[] seq = getHailStoneSequence(k);
        double[] pSum = calculatePrefixSum(seq);
        // sout("pSum: " + Arrays.toString(pSum));
        
        // query 
        for (int i = 0; i < ranges.length; i++) {
            int st = ranges[i][0];
            int b = ranges[i][1];
            int en = b <= 0 ? seq.length - 1 + b : b;
            
            if (st > en) {
                answer[i] = -1;
                continue;
            }
            
            if (en > seq.length) {
                en = seq.length - 1;
            }
            
            answer[i] = pSum[en] - pSum[st];
        }
        

        return answer;
    }
    
    // 누적합 구하기 
    double[] calculatePrefixSum(int[] seq) {
        int n = seq.length;

        double[] pSum = new double[n];
        pSum[0] = 0;
        
        for (int i = 0; i < n - 1; i++) {
            pSum[i + 1] = pSum[i] + calculateArea(i, seq);
        }
        
        return pSum;
    }
    
    // [x, x + 1] 구간의 넓이를 구함
    double calculateArea(int x, int[] seq) {
        return Math.min(seq[x], seq[x + 1]) 
            + 0.5 * Math.abs(seq[x + 1] - seq[x]);
    }
    
    int[] getHailStoneSequence(int k) {
        List<Integer> seq = new ArrayList<>();
        seq.add(k);
        
        while (k > 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = (3 * k) + 1;
            }
            
            seq.add(k);
        }
        
        return seq.stream().mapToInt(i -> i).toArray();
    }
    
    
    void sout(Object o) {
        System.out.println(o);
    }
    
}