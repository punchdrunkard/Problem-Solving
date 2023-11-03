#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int a_size, b_size;

set<int> a, b;  // 집합 a, b

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> a_size >> b_size;

  for (int i = 0; i < a_size; i++) {
    int _a;
    cin >> _a;
    a.insert(_a);
  }

  for (int i = 0; i < b_size; i++) {
    int _b;
    cin >> _b;
    b.insert(_b);
  }
}

int solve() {
  set<int> diff;
  auto it = set_symmetric_difference(a.begin(), a.end(), b.begin(), b.end(),
                                     inserter(diff, diff.begin()));

  return diff.size();
}

int main() {
  input();
  cout << solve();

  return 0;
}
