#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int m;
  cin >> m;

  // 집합의 표현 => 비트 마스킹을 이용한다.
  // 특히 S = {1, 2, .. 20} 으로 나타낼 수 있으므로,
  // 각 원소들을 index로 하고, 원소의 존재여부를 bool 로 나타낼 수 있다.

  vector<bool> s(21, false);

  for (int i = 0; i < m; i++) {
    string op;
    cin >> op;

    if (op == "add") {
      int x;
      cin >> x;
      s[x] = true;
    } else if (op == "remove") {
      int x;
      cin >> x;
      s[x] = false;
    } else if (op == "check") {
      int x;
      cin >> x;
      cout << s[x] << '\n';
    } else if (op == "toggle") {
      int x;
      cin >> x;
      s[x] = !s[x];
    } else if (op == "all") {
      for (int i = 1; i <= 20; i++) {
        s[i] = true;
      }
    } else if (op == "empty") {
      for (int i = 1; i <= 20; i++) {
        s[i] = false;
      }
    }
  }

  return 0;
}
