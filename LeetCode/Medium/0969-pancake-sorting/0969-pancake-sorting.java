class Solution {
    List<Integer> answer = new ArrayList<>();

    class Pair {
        int val, idx;
        
        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public List<Integer> pancakeSort(int[] arr) {
        sort(arr, arr.length);
        return answer;
    }

    // arr의 n개의 원소를 정렬한다.
    void sort(int[] arr, int n) {
        // base case
        if (n <= 1) {
            return;
        }

        // TODO: 앞의 원소 중 가장 큰 원소를 골라 제일 뒤로 보내기 
        Pair max = findMax(arr, n);
        int maxVal = max.val;
        int maxIdx = max.idx;
        
        // 목표 - max 가 (n - 1) 번쨰의 인덱스로 와야 함 (즉 최댓값을 맨 뒤로 보내야 함)
        // 맨뒤로 보내는 방법? -> 최댓값을 맨 앞으로 가져온다음에 (최댓값 인덱스 뒤에서 뒤집은 다음에) 전체로 한 번 더 뒤집으면 됨
        // 즉 1. 최댓값 인덱스 뒤에서 뒤집는다 -> (maxIdx + 1)에서 뒤집는다
        // 2. 전체 크기 (n에서 뒤집는다. )
        if (maxIdx != n - 1) { // 뒤집기 
            reverse(arr, maxIdx + 1);
            answer.add(maxIdx + 1);
            reverse(arr, n);
            answer.add(n);
        }

        sort(arr, n - 1);
    }

    // arr의 앞 n개 원소 뒤집기 
    void reverse(int[] arr, int n) {
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
    }

    // arr의 [0...N] 중 가장 큰 원소와 그 인덱스를 찾음 
    Pair findMax(int[] arr, int size) {
        int max = -1;
        int maxIdx = size;

        for (int i = 0; i < size; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIdx = i;
            }
        }

        return new Pair(max, maxIdx);
    }
}