class Solution {
    
    // 멀리있는 것 집 부터 처리한다! 
    // "한 번의 왕복"으로 최대한 많은 배달과 수거를 해결 하려면? 
    
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        
         // 처리해야 할 누적 배달량 -> 현재 위치보다 더 먼 집들에 배달해야 할 상제의 총 갯수 
        int dCarry = 0;
        // 처리해야할 누적 수거량 -> 현재 위치보다 더 먼 집들에서 수거해야할 상자의 총 갯수  
        int pCarry = 0; 
        
        for (int i = n - 1; i >= 0; i--) { // 현재 i번째 집에 들렀다고 생각
            // 지금 거리에서 필요로 하는 무게
            dCarry += deliveries[i];
            pCarry += pickups[i]; 
            
            // 왕복이 필요한 시점 판단 -> dCarry 나 pCarry 중 하나라도 양수라는 건
            // 현재 i번째 집을 포함한 그 뒤쪽 어딘가에 아직 처리하지 못한 배달이나 수거가
            // 남아있다는 것을 의미 
            while (dCarry > 0 || pCarry > 0) {
                answer += (2 * (i + 1));
                // 한 번 왕복하면 트럭 용량만큼 배달 상자를 가져갈 수 있고,
                // cap 만큼 수거 상자를 가져올 수 있다.
                dCarry -= cap;
                pCarry -= cap;
            }
        }
        
        return answer;
    }
}