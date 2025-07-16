import java.util.*;

class Solution {
    
    int n, m;
    int[] discountRates = new int[]{10, 20, 30, 40};
    int[][] users;
    int[] emoticons;
    int[] answer = {-1, -1};
    public int[] solution(int[][] users, int[] emoticons) {
        init(users, emoticons);
        dfs(0, new int[m]);
        return answer;
    }
    
    void dfs(int i, int[] currentRates) {
        // base case
        if (i >= m) {
            int[] result = calculate(currentRates);
            
            if (canRenewAnswer(answer, result)) {
                answer = new int[]{result[0], result[1]};
            }
            
            return; 
        }
        
        // recursive case
        for (int rate: discountRates) {
            currentRates[i] = rate;
            dfs(i + 1, currentRates);
        }
    }
    
    boolean canRenewAnswer(int[] prev, int[] next) {
        if (prev[0] == next[0]) {
            return next[1] > prev[1];
        }
        
        return next[0] > prev[0];
    }
    
    int[] calculate(int[] discountRates) { 
        int[] userTotalCost = new int[n];
        boolean[] isUserSignedUp = new boolean[n];
        
        for (int i = 0; i < n; i++) {
            if (isUserSignedUp[i]) {
                continue;
            }
            
            int userRate = users[i][0];
            int userSignUpThreshold = users[i][1];
            
            for (int j = 0; j < m; j++) {
                if (discountRates[j] >= userRate) {
                    userTotalCost[i] += 
                        calculateDiscountPrice(emoticons[j], discountRates[j]);
                }
                
                if (userTotalCost[i] >= userSignUpThreshold) {
                    userTotalCost[i] = 0;
                    isUserSignedUp[i] = true;
                    break;
                }
            }
        }
        
        int signedUpUserCount = 0;
        int totalPrice = 0;
        
        for (boolean isSignedUp: isUserSignedUp) {
            signedUpUserCount += (isSignedUp ? 1 : 0);
        }
        
        for (int cost: userTotalCost) {
            totalPrice += cost;
        }
        
        return new int[]{signedUpUserCount, totalPrice};
    }
    
    int calculateDiscountPrice(int price, int rate) {
        int discountAmount = price * rate / 100;
        return price - discountAmount;
    }
    
    void init(int[][] users, int[] emoticons) {
        n = users.length;
        m = emoticons.length;
        this.users = users;
        this.emoticons = emoticons;
        
    }
    void sout(Object o) {
        System.out.println(o);
    }
}