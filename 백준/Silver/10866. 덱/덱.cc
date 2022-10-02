#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int N;
  cin >> N;

  deque<int> deq;

  while (N--) {
    string op;
    cin >> op;
    if (op == "push_front") {
      int add;
      cin >> add;
      deq.push_front(add);
    } else if (op == "push_back") {
      int add;
      cin >> add;
      deq.push_back(add);
    } else if (op == "pop_front") {
      if (deq.size() != 0) {
        cout << deq.front() << '\n';
        deq.pop_front();
      } else {
        cout << -1 << '\n';
      }
    } else if (op == "pop_back") {
      if (deq.size() != 0) {
        cout << deq.back() << '\n';
        deq.pop_back();
      } else {
        cout << -1 << '\n';
      }
    } else if (op == "size") {
      cout << deq.size() << '\n';
    } else if (op == "empty") {
      if (deq.size() == 0)
        cout << 1 << '\n';
      else
        cout << 0 << '\n';
    } else if (op == "front") {
      if (deq.size() != 0) {
        cout << deq.front() << '\n';
      } else {
        cout << -1 << '\n';
      }
    } else if (op == "back") {
      if (deq.size() != 0) {
        cout << deq.back() << '\n';
      } else {
        cout << -1 << '\n';
      }
    }
  }
}