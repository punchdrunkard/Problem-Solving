#include <bits/stdc++.h>

using namespace std;

struct Node {
  int data;
  Node* next;
};

constexpr int NODE_MAX = 50000;
Node node_pool[NODE_MAX];
int node_count = 0;

Node* new_node(int x) {
  node_pool[node_count].data = x;
  node_pool[node_count].next = nullptr;

  return &node_pool[node_count++];
}

class SinglyLinkedList {
  Node head;

 public:
  SinglyLinkedList() = default;

  void init() {
    head.next = nullptr;
    node_count = 0;
  }

  // 삽입 : 앞에서 부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다.
  void insert(int x, int y, vector<int>& s) {
    Node* prev_ptr = &head;
    int count = 0;

    while (prev_ptr->next != nullptr && count < x) {
      prev_ptr = prev_ptr->next;
      count++;
    }

    for (int i = y - 1; i >= 0; i--) {
      Node* node = new_node(s[i]);
      node->next = prev_ptr->next;
      prev_ptr->next = node;
    }
  }

  // 삭제 : 앞에서부터 x의 위치 바로 다음부터 y개의 숫자를 삭제한다.
  void remove(int x, int y) {
    Node* prev_ptr = &head;
    int count = 1;

    while (prev_ptr->next != nullptr && count < x) {
      prev_ptr = prev_ptr->next;
      count++;
    }

    // 해당 인덱스 부터 y개의 숫자를 삭제하기
    for (int i = 0; i < y; i++) {
      prev_ptr->next->next = prev_ptr->next->next->next;
    }
  }

  // 추가 : 암호문의 맨 뒤에 y개의 숫자를 덧붙인다. s는 덧붙일 숫자들이다.
  void add(int y, vector<int>& s) {
    Node* ptr = &head;

    while (ptr->next != nullptr) {
      ptr = ptr->next;
    }

    for (int i = 0; i < y; i++) {
      Node* node = new_node(s[i]);
      ptr->next = node;
      ptr = node;
    }
  }

  // 맨 앞에 원소 추가
  void insert_front(int x) {
    Node* node = new_node(x);
    node->next = head.next;
    head.next = node;
  }

  string getFrontTen() {
    Node* curr = head.next;
    int count = 0;
    string temp;

    while (curr != nullptr && count < 10) {
      temp = temp + ' ' + to_string(curr->data);
      curr = curr->next;
      count++;
    }

    return temp;
  }
};

SinglyLinkedList makeSlist() {
  int n;
  cin >> n;

  SinglyLinkedList slist;
  slist.init();

  vector<int> numbers(n);

  for (int j = 0; j < n; j++) {
    cin >> numbers[j];
  }

  for (int j = n - 1; j >= 0; j--) {
    slist.insert_front(numbers[j]);
  }

  return slist;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  // 총 10개의 테스트 케이스 TODO: 테스트 케이스는 실제로는 10개!
  for (int i = 1; i <= 10; i++) {
    SinglyLinkedList slist = makeSlist();

    int m;
    cin >> m;

    for (int oper = 0; oper < m; oper++) {
      char type;
      cin >> type;

      switch (type) {
        case 'I': {
          int x, y;
          cin >> x >> y;

          vector<int> s(y);

          for (int k = 0; k < y; k++) {
            cin >> s[k];
          }

          slist.insert(x, y, s);
          break;
        }
        case 'D': {
          // 앞에서부터 x의 위치 바로 다음부터 y개의 숫자를 삭제한다.
          int x, y;
          cin >> x >> y;

          slist.remove(x, y);

          break;
        }
        case 'A': {
          // 암호문의 맨 뒤에 y개의 숫자를 덧붙인다. s는 덧붙일 숫자들이다.
          int y;
          cin >> y;

          vector<int> s(y);

          for (int k = 0; k < y; k++) {
            cin >> s[k];
          }

          slist.add(y, s);
          break;
        }
      }
    }

    cout << "#" << i << slist.getFrontTen() << "\n";
  }

  return 0;
}
