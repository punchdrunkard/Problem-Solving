class Solution {

    // queue의 head가 최댓값임을 보장하는 큐 
    class MonoticQueue {
        LinkedList<Integer> q = new LinkedList<>();
        
        public void push(int n) {
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }

            q.offerLast(n);
        }

        public int getMax() {
            return q.getFirst();
        }
        
        // 최댓값인 경우에만 영향을 미침
        public void pop(int n) {
            if (q.getFirst() != n) {
                return;
            }

            q.pollFirst();
        }
    }

    // 결론 -> 최댓값만 업데이트하는 큐 
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonoticQueue window = new MonoticQueue();
        List<Integer> res = new ArrayList<>();

        // 슬라이딩 윈도우
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                // 윈도우 앞의 k - 1을 먼저 채운다. 
                window.push(nums[i]);
            } else {
                // 새 요소 추가
                window.push(nums[i]);

                // 현재 윈도우의 최대 요소 기록
                res.add(window.getMax());

                // 빠지는 요소
                window.pop(nums[i - k + 1]);
            }
        } // end of for

        return res.stream().mapToInt(i -> i).toArray();
    }
}