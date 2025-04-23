import java.util.*;
import java.io.*;

class Solution {
    
    final String ICN = "ICN";
    
    Map<String, List<State>> adj;
    String[] answer;
    
    class State {
        String dest;
        int ticketIdx;
        
        State (String dest, int ticketIdx) {
            this.dest = dest;
            this.ticketIdx = ticketIdx;
        }
        
        @Override
        public String toString() {
            return "(dest: " + dest + ", ticketIdx: " + ticketIdx + ")";
        }
    }
    
    public String[] solution(String[][] tickets) {
        createGraph(tickets);
        dfs(ICN, new boolean[tickets.length], new ArrayList<>(List.of(ICN)));
        return answer;
    }
    
    boolean dfs(String current, boolean[] used, List<String> route) {
        // 종료조건 - 모든 티켓을 사용한 경우 
        if (doesAllTicketsUsed(used)) {
            // 젤 먼저 찾은거에서 끝나게 한다.
            answer = route.toArray(new String[0]);
            return true;
        }
        
        for (State nextTicket: adj.get(current)) {
            int ticketIdx = nextTicket.ticketIdx;
            String next = nextTicket.dest;
            
            if (used[ticketIdx]) {
                continue;
            }
            
            // 다음 공항 방문
            used[ticketIdx] = true;
            route.add(next);
            if (dfs(next, used, route)) { // true 면 이미 답을 찾은 경우임
                return true;
            }
            
            used[nextTicket.ticketIdx] = false;
            route.remove(route.size() - 1);
        }
        
        return false;
    }
    
    boolean doesAllTicketsUsed(boolean[] used) {
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                return false;
            }
        }
        
        return true;
    }
    
    void createGraph(String[][] tickets) {
        // init
        adj = new HashMap<String, List<State>>();
        for (int i = 0; i < tickets.length; i++) {
            String[] ticket = tickets[i];
            adj.putIfAbsent(ticket[0], new ArrayList<>());
            adj.putIfAbsent(ticket[1], new ArrayList<>());
            adj.get(ticket[0]).add(new State(ticket[1], i));
        }
        
        // sort
        for (String start: adj.keySet()) {
            adj.get(start).sort((a, b) -> a.dest.compareTo(b.dest));
        }
    }
}