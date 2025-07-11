class Solution {
    // 어떤 원소 집단에서 최대 공약수 a를 구하고 O(nlog~)
    // 그 최대 공약수가 다른 집단에 대해 다 못나누는 조건을 만족하면 됨 O(n) 
    // 시간 복잡도 내에 들어올 수 있어 보임 ! 
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        int aGCD = findArrayGcd(arrayA);
        int bGCD = findArrayGcd(arrayB);
        
        
        // 1. aGCD 가 arrayB의 모든 원소를 나눌 수 없어야 한다.
        if (check(aGCD, arrayB)) {
            answer = Math.max(answer, aGCD);
        }
        // 2. bGCD 가 arrayA의 모든 원소르 나눌 수 없어야 한다.
        if (check(bGCD, arrayA)) {
            answer = Math.max(answer, bGCD);
        }
   
        return answer;
    }
    
    boolean check(int a, int[] arr) {
        if (a == 1) {
            return false;
        }
        
        for (int i: arr) {
            if (i % a == 0) {
                return false;
            }
        }
        
        return true;
    }
    int findArrayGcd(int[] arr) {
        int res = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            res = gcd(res, arr[i]);
        }
        
        return res;
    }
    
    int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        
        return a;
    }
    
    void sout(Object o) {
        System.out.println(o);
    }
}