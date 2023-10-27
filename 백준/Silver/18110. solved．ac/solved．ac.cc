#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;
vector<int> opinions;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  //  freopen("sample_input.txt", "r", stdin);

  cin >> n;
  opinions.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> opinions[i];
  }
}

int solve() {
  if (n == 0) return 0;

  double trimmed = round(n * (15.0 / 100));
  sort(opinions.begin(), opinions.end());

  int sum = 0;

  for (int i = trimmed; i < n - trimmed; i++) {
    sum += opinions[i];
  }

  return round(sum / (n - 2 * trimmed));
}

int main() {
  input();
  cout << solve();
  return 0;
}
