#include <bits/stdc++.h>

#include <queue>
using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int N;
  cin >> N;

  queue<int> q;

  while (N--) {
    string op;
    cin >> op;
    if (op == "push") {
      int add;
      cin >> add;
      q.push(add);
    } else if (op == "pop") {
      if (q.size() != 0) {
        cout << q.front() << '\n';
        q.pop();
      } else {
        cout << -1 << '\n';
      }
    } else if (op == "size") {
      cout << q.size() << '\n';
    } else if (op == "empty") {
      if (q.size() == 0) {
        cout << 1 << '\n';
      } else {
        cout << 0 << '\n';
      }
    } else if (op == "front") {
      if (q.size() != 0) {
        cout << q.front() << '\n';
      } else {
        cout << -1 << '\n';
      }
    } else if (op == "back") {
      if (q.size() != 0) {
        cout << q.back() << '\n';
      } else {
        cout << -1 << '\n';
      }
    }
  }
}