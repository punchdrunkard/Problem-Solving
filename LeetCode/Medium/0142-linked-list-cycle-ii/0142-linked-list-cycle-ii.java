public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean hasCycle = false;

        // fast와 slow가 만나는 지점을 찾음
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // 사이클이 없으면 null 반환
        if (!hasCycle) {
            return null;
        }

        slow = head;

        // 같은 속도로 이동시켜서 만나는 지점이 사이클의 시작이 됨
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
