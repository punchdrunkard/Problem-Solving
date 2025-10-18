class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        // chpeapest[i] := i날 이전까지 나온 가격 중 가장 싼 가격 
        int[] cheapest = new int[n];
        Arrays.fill(cheapest, Integer.MAX_VALUE);
        // expensivest[i] := i날 이후로 나올 가격 중 가장 비싼 가격
        int[] expensivest = new int[n];
        Arrays.fill(expensivest, Integer.MIN_VALUE);
        
        cheapest[0] = prices[0];
        for (int i = 1; i < n; i++) {
            cheapest[i] = Math.min(prices[i], cheapest[i - 1]);
        }

        expensivest[n - 1] = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            expensivest[i] = Math.max(prices[i], expensivest[i + 1]);
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            answer = Math.max(answer, Math.abs(cheapest[i] - expensivest[i]));
        }
        return answer;
    }
}