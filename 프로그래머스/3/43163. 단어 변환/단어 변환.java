import java.util.*;

class Solution {
    
    public int solution(String begin, String target, String[] words) {
        List<Integer>[] adj = createGraph(words);
        Map<String, Integer> idxMap = createIdxMap(words);
        int answer = bfs(begin, target, words, adj, idxMap);
        return answer;
    }
    
    int bfs(String begin, String target, String[] words, 
            List<Integer>[] adj, Map<String, Integer> idxMap) {
        
        Queue<String> q = new LinkedList<>();
        Map<String, Integer> dist = new HashMap<>();
        
        // init
        for (int i = 0; i < words.length; i++) {
            if (canChange(begin, words[i])) {
                q.offer(words[i]);
                dist.put(words[i], 1);
            }
        }
        
        while (!q.isEmpty()) {
            int sz = q.size();
            
            for (int i  = 0; i < sz; i++) {
                String current = q.poll();
                
                // 도착한 경우
                if (current.equals(target)) {
                    return dist.get(target);
                }
                
                // 상태 전이
                for (int wordIdx: adj[idxMap.get(current)]) {
                    String nextWord = words[wordIdx];
                    if (dist.containsKey(nextWord)) {
                        continue;
                    }
                    
                    dist.put(nextWord, dist.get(current) + 1);
                    q.offer(nextWord);
                }
            }
        }
        
        return dist.getOrDefault(target, 0);
    }
    
    // 시작점: begin 
    // 한 번에 하나의 단어를 바꾸되, word 안에 있는 단어로만 바꿀 수 있는듯?
    
    Map<String, Integer> createIdxMap(String[] words) {
        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            idxMap.put(words[i], i);
        }
        return idxMap;
    } // end of createIdxMap;
    
    // words 끼리 그래프를 만든다.
    List<Integer>[] createGraph(String[] words) {
        int n = words.length;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // i번 word 에 대해 
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                
                if (canChange(words[i], words[j])) {
                    adj[i].add(j);
                }
            }
        } // end of for
        return adj;
    } // end of createGraph
    
    // 알파벳을 하나만 바꿔서 curr -> next 으로 도달할 수 있나?
    boolean canChange(String current, String next) {
        int count = 0;
        int wordLength = current.length(); // 모든 단어의 길이는 같음 
        
        for (int i = 0; i < wordLength; i++) {
            if (current.charAt(i) != next.charAt(i)) {
                count++;
            }
            
            if (count > 1) {
                return false;
            }
        }
        
        return count == 1;
    } // end of canChange
}