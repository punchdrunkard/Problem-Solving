public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) { // 리스트가 비어있거나 노드가 하나뿐일 때
            return null;
        }
        
        ListNode fast = head;
        ListNode slow = head;

        // fast와 slow가 만나는 지점을 찾음
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                break;
            }
        }

        // 사이클이 없으면 null 반환
        if (slow != fast) {
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
