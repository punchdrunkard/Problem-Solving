#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define INF 2'000'000'001

typedef long long ll;

using namespace std;

int n, m;
vector<int> a;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  a.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> a[i];
  }
}

int main() {
  input();

  sort(a.begin(), a.end());

  int lo = 0, hi = 0, ans = INF;

  while (lo <= hi && hi < n) {
    int diff = a[hi] - a[lo];

    if (diff > m) {
      lo++;
      ans = min(diff, ans);
    } else if (diff < m) {
      hi++;
    } else {
      ans = m;
      break;
    }
  }

  cout << ans;

  return 0;
}
