#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;
set<string> hear, see;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;

  string name;
  for (int i = 0; i < n; i++) {
    cin >> name;
    hear.insert(name);
  }

  for (int i = 0; i < m; i++) {
    cin >> name;
    see.insert(name);
  }
}

int main() {
  input();

  vector<string> result;
  result.resize(n + m);

  auto it = set_intersection(hear.begin(), hear.end(), see.begin(), see.end(),
                             result.begin());

  result.erase(it, result.end());

  cout << result.size() << '\n';

  for (auto s : result) {
    cout << s << '\n';
  }

  return 0;
}
