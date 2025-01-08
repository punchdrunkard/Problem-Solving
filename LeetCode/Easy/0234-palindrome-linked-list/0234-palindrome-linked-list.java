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

    // 정방향
    ListNode left;

    public boolean isPalindrome(ListNode head) {
        left = head;
        return isPalindromeHelper(head);
    }

    boolean isPalindromeHelper(ListNode right) {
        if (right == null) {
            return true;
        }

        boolean result = isPalindromeHelper(right.next);
        result &= (left.val == right.val);
        left = left.next;
        return result;
    }
}