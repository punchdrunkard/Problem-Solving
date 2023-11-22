#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, q;
vector<int> a, pSum;

// 주어진 "구간"들의 초당 박자 변화량의 "합"
void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> q;
  a.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> a[i];
  }
}

void makePSum() {
  pSum.resize(n + 1, 0);

  // pSum[i] = 인접한 값들의 차의 prefix sum
  for (int i = 1; i < n; i++) {
    pSum[i + 1] = pSum[i] + abs(a[i] - a[i - 1]);
  }
}

int solve(int l, int r) { return pSum[r] - pSum[l]; }

int main() {
  input();
  makePSum();

  for (int i = 0; i < q; i++) {
    int l, r;
    cin >> l >> r;

    cout << solve(l, r) << '\n';
  }

  return 0;
}
