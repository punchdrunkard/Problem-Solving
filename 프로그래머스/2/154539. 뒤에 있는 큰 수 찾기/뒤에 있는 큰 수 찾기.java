import java.util.*;

class Solution {
    // stack에는 "뒷 큰수"가 될 수 있는 후보들을 담는다.
    // 현재 숫자 numbers[i]를 기준으로, 
    // 스택의 top에 numbers[i] 보다 같거나 작은 값이 있다면 해당 수를 제거한다.
    // (왜냐하면 이제 앞으로의 숫자들에 대해서 numbers[i]가 그 뒤에 있는 수보다 크고 가깝기 때문)
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        Arrays.fill(answer, -1);
        
        Deque<Integer> stk = new ArrayDeque<>();
        stk.offerLast(numbers[n - 1]);
        
        for (int i = n - 2; i >= 0; i--) {
            while (!stk.isEmpty() && stk.peekLast() <= numbers[i]) {
                stk.pollLast();
            }
            
            if (stk.isEmpty()) {
                answer[i] = -1;
            } else {
                answer[i] = stk.peekLast();
            }
            
            stk.offerLast(numbers[i]);
        }
        
        return answer;
    }
}