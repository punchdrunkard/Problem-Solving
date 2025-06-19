import java.util.*;
import java.io.*;

// 비밀코드가 서로 다른 정수 5개가 정렬된 형태이고,
// n = 30 이므로 가능한 후보는 30C5 = 243 * 10^5 < 10^8 이라서 
// 모든 비밀코드 후보들 중에서 조건을 만족하는지 세면 된다!
class Solution {
    
    // 비밀 코드 후보 
    List<List<Integer>> candidates = new ArrayList<>();
        
    public int solution(int n, int[][] q, int[] ans) {
        createCandidates(n, 1, 0, new ArrayList<>(), new boolean[n + 1]);
        return countCandidates(n, q, ans);
    }
    
    int countCandidates(int n, int[][] q, int[] ans) {
        int count = 0;
        int m = q.length;
        
        for (List<Integer> candidate: candidates) {
            boolean isValid = true;
            
            for (int i = 0; i < m; i++) {
                if (!isValidCandidate(candidate, q[i], ans[i])) {
                    isValid = false;
                    break;
                }
            }
            
            if (isValid) {
                count++;
            }
        }
        
        return count;
    }
    
    // 조건을 만족하는 후보인지 
    boolean isValidCandidate(List<Integer> candidate, int[] q, int target) {
        int matchCount = 0;
        
        for (int i = 0; i < candidate.size(); i++) {
            for (int j = 0; j < q.length; j++) {
                if (candidate.get(i) == q[j]) {
                    matchCount++;
                }
            }
        }
        
        // sout("matchCount : " + matchCount);
        
        return matchCount == target;
    }
    
    // 비밀코드 후보를 만들고 저장 => nC5, 최대 30C5 = 243 * 10^5 <= 10^8 
    void createCandidates(int n, int i, int depth, List<Integer> current, boolean[] visited) {
        // base case : 비밀코드가 5자리 수이기 때문에 
        if (depth == 5) {
            candidates.add(new ArrayList(current));
            return;
        }
        
        // recursive case
        for (int idx = i; idx <= n; idx++) {
            if (visited[idx]) {
                continue;
            }
            
            visited[idx] = true;
            current.add(idx);
            createCandidates(n, idx + 1, depth + 1, current, visited);
            current.remove(current.size() - 1);
            visited[idx] = false;
        }
    }
    
    // 2. 조건 만족 확인 => q의 길이가 10이라서 그냥 세면 될 듯 (upperBound, lowerBound 써볼 수는 있겠지만... )
    
 
    void sout(Object o) {
        System.out.println(o);
    }
}