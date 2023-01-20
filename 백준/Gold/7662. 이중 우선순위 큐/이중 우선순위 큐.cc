#include <bits/stdc++.h>
using namespace std;

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int t;
  cin >> t;

  for (int i = 0; i < t; i++) {
    int k;
    cin >> k;
    multiset<int> s; // 동일한 정수가 삽입될 수 있음

    for (int j = 0; j < k; j++) {
      char op;
      int n;

      cin >> op >> n;

      switch (op) {
        case 'I':
          s.insert(n);
          break;
        case 'D':

          if (n == 1) {
            if (s.empty()) continue;
            s.erase(prev(s.end()));

          } else {
            if (s.empty()) continue;
            s.erase(s.begin());
          }
          break;
      }
    }

    if (s.empty())
      cout << "EMPTY\n";
    else {
      cout << *prev(s.end()) << ' ' << *s.begin() << '\n';
    }
  }

  return 0;
}
