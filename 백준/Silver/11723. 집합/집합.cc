#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int m;
  cin >> m;

  // 집합을 나타내는 비트 마스크
  int s = 0;

  for (int i = 0; i < m; i++) {
    string op;
    cin >> op;

    if (op == "add") {
      int x;
      cin >> x;
      s |= (1 << x);  // (x 번째 비트를 1로 한다.)
    } else if (op == "remove") {
      int x;
      cin >> x;
      s &= ~(1 << x);
    } else if (op == "check") {
      int x;
      cin >> x;
      cout << bool(s & (1 << x)) << '\n';
    } else if (op == "toggle") {
      int x;
      cin >> x;
      s ^= (1 << x);
    } else if (op == "all") {
      s = (1 << 21) - 1;

    } else if (op == "empty") {
      s = 0;
    }
  }

  return 0;
}
