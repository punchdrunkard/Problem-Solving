import java.util.*;


class Solution {
    
    Set<Integer> results = new HashSet<>();
                        
    public int solution(String numbers) {
        int answer = 0;
    
        boolean[] visited = new boolean[numbers.length()];
        dfs(numbers, visited, "");
        // System.out.println(results);

        return results.size();
    }
    
    void dfs(String numbers, boolean[] visited, String current) {
        
       if (!current.isEmpty()) {
           int currentNumber = Integer.parseInt(current);
           
           if (isPrimeNumber(currentNumber)) {
               results.add(currentNumber);
           }
       }
        
        for (int idx = 0; idx < numbers.length(); idx++) {
            if (visited[idx]) {
                continue;
            }
            
            visited[idx] = true;
            dfs(numbers, visited, current + numbers.charAt(idx));
            visited[idx] = false;
        }
    }
    
    boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        
        return true;
    }
}