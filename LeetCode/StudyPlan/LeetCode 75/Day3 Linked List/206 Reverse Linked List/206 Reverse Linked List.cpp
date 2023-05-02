/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
 public:
  ListNode *reverseList(ListNode *head) {
    // curr : 현재 노드, prev : 이전 노드, temp : 임시 노드
    ListNode *curr = head;
    ListNode *prev = NULL;
    ListNode *temp = NULL;

    // curr이 NULL이 될 때 까지 반복문을 수행한다.
    // 이는 연결 리스트의 끝까지 순회할 때 까지 계속된다.
    while (curr) {
      // temp는 curr의 다음 노드를 가리키게 한다. (curr의 다음 노드 정보를 임시
      // 저장한다.)
      temp = curr->next;
      // 리스트를 뒤집는다. (curr의 다음 노드를 prev로 설정)
      curr->next = prev;
      prev = curr;
      curr = temp;  // 다음 노드로 이동한다.
    }

    return prev;
  }
};