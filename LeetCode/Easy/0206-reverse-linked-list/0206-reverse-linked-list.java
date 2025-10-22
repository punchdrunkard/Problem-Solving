/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // 리스트를 순회하면서 현재 노드의 next 포인터가 이전 노드를 가리키도록 방향을 바꿔야 한다 
    public ListNode reverseList(ListNode head) {
        ListNode prev = null; // 이전 노드를 저장
        ListNode current = head; // 현재 처리 중인 노드를 저장
        ListNode nextTemp = new ListNode();

        while (current != null) {
            nextTemp = current.next;
            current.next = prev;
            prev=  current;
            current = nextTemp;
        }

        return prev;
    }
}