#include <bits/stdc++.h>

using namespace std;

// 입을 수 있는 의상의 수
// 각 종류에 해당하는 의상의 수 + 1 (그 옷을 입지 않는 경우)를 곱하고,
// 알몸인 상태를 뺸다. (-1)

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int t;
  cin >> t;

  while (t--) {
    int n;
    cin >> n;

    unordered_map<string, int> um;

    for (int i = 0; i < n; i++) {
      string name, kind;
      cin >> name >> kind;
      um[kind] += 1;
    }

    int answer = 1;

    for (auto dress : um) {
      answer *= (dress.second + 1);
    }

    cout << answer - 1 << '\n';
  }

  return 0;
}
