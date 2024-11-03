class Solution {
    public int openLock(String[] deadends, String target) {
        // bfs init
        String start = "0000";
        Queue<String> q = new LinkedList();
        Map<String, Boolean> visited = new HashMap();
        Map<String, Boolean> isDeadEnd = new HashMap();
        
        for (String deadend: deadends) {
            isDeadEnd.put(deadend, true);
        }

        visited.put(start, true);
        q.offer(start);
        int dist = 0;

        while (!q.isEmpty()){
            int size = q.size();
            
            for (int i = 0; i < size; i++) {
                String current = q.poll();

                // 그 다음으로 갈 수 없는 경우
                if (isDeadEnd.getOrDefault(current, false)) {
                    continue;    
                }

                // 정답을 찾은 경우 
                if (current.equals(target)) {
                    return dist;
                }

                // 노드의 근접 노드를 큐에 추가한다.
                for (int j = 0; j < 4; j++) {
                    List<String> candidates = List.of(rotateUp(current, j), rotateDown(current, j));
                    
                    for (String candidate: candidates) {
                        if (visited.getOrDefault(candidate, false)) {
                            continue;
                        }
                        q.offer(candidate);
                        visited.put(candidate, true);
                    }
                }
            }

            dist++;
        }

        return -1;
    }

    // idx 번째 인덱스를 up 방향으로 돌림 
    String rotateUp(String s, int idx) {
        char[] charArr = s.toCharArray();
        
        if (charArr[idx] == '9') {
            charArr[idx] = '0';
        } else {
            charArr[idx]++;
        }

        return new String(charArr);
    }

    // idx 번째 인덱스를 down 방향으로 돌림
    String rotateDown(String s, int idx) {
        char[] charArr = s.toCharArray();
        
        if (charArr[idx] == '0') {
            charArr[idx] = '9';
        } else {
            charArr[idx]--;
        }

        return new String(charArr);
    }
}