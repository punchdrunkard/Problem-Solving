#include <bits/stdc++.h>

using namespace std;

struct Compare {
  bool operator()(const string &a, const string &b) const {
    if (a.size() == b.size()) {
      return a < b;
    }

    return a.size() < b.size();
  }
};

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int i = 1; i <= test_case; i++) {
    int n;
    cin >> n;
    set<string, Compare> names;

    for (int i = 0; i < n; i++) {
      string name;
      cin >> name;
      names.insert(name);
    }

    cout << "#" << i << '\n';

    for (auto &name : names) {
      cout << name << '\n';
    }
  }

  return 0;
}
