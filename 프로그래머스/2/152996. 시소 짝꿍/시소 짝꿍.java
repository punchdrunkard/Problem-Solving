import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        
        Map<Integer, Integer> count = new HashMap<>();
        for (int weight: weights) {
            List<Integer> targets = getTargetWeights(weight);
        
            for (int target: targets) {
                answer += count.getOrDefault(target, 0);
            }
            
            count.put(weight, count.getOrDefault(weight, 0) + 1);
        }
        
        return answer;
    }
    
    List<Integer> getTargetWeights(int weight) {
        List<Integer> res = new ArrayList<>();
        res.add(weight);
        if (weight % 3 == 0) {
            res.add(2 * weight / 3);
            res.add(4 * weight / 3);
        }
        
        if (weight % 2 == 0) {
            res.add(3 * weight / 2);
            res.add(weight / 2);
        }
        
        if (weight % 4 == 0) {
            res.add(3 * weight / 4);
        }
        
        res.add(2 * weight);
        
        return res;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}