#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int main() {
  FASTIO;

  int n;
  cin >> n;

  stack<int> s;

  for (int i = 0; i < n; i++) {
    int op;
    cin >> op;

    if (op == 1) {
      int x;
      cin >> x;
      s.push(x);
    } else if (op == 2) {
      if (!s.empty()) {
        int top = s.top();
        s.pop();
        cout << top << "\n";
      } else {
        cout << -1 << "\n";
      }
    } else if (op == 3) {
      cout << s.size() << "\n";
    } else if (op == 4) {
      cout << s.empty() << "\n";
    } else {
      if (!s.empty()) {
        cout << s.top() << "\n";
      } else {
        cout << -1 << "\n";
      }
    }
  }
  return 0;
}
