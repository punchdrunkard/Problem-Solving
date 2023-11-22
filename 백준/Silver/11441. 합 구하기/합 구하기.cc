#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;
vector<int> a, pSum;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  a.resize(n + 1);

  for (int i = 1; i <= n; i++) {
    cin >> a[i];
  }

  cin >> m;
}

// pSum[i] = i까지의 구간의 합
void makePSum() {
  pSum.resize(n + 1, 0);

  for (int i = 1; i <= n; i++) {
    pSum[i] = pSum[i - 1] + a[i];
  }
}

int solve(int i, int j) { return pSum[j] - pSum[i - 1]; }

int main() {
  input();
  makePSum();

  for (int query = 0; query < m; query++) {
    int i, j;
    cin >> i >> j;
    cout << solve(i, j) << '\n';
  }

  return 0;
}
