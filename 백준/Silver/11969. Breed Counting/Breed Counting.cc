#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// 1 ~ n의 번호가 매겨진 소 n 마리
// 각 소는 breed id를 가지고 있음 (1, 2, 3)
// 특정 구간에서 각 품종별 소의 수를 세기
// 예를 들어 [a, b] 구간에서 1 품종이 몇개, 2 품종이 몇개, 3 품종이 몇개

int n, q;
vector<int> cows;

void input() {
  FASTIO;

  cin >> n >> q;
  cows.resize(n + 1);

  for (int i = 1; i <= n; i++) {
    cin >> cows[i];
  }
}

vector<vector<int>> pSum;

void makePSum() {
  pSum.resize(n + 1, vector<int>(3, 0));
  pSum[0] = {0, 0, 0};

  for (int i = 1; i <= n; i++) {
    pSum[i][cows[i] - 1] += 1;
    // 이전까지의 합을 더해준다.
    for (int j = 0; j < 3; j++) {
      pSum[i][j] += pSum[i - 1][j];
    }
  }
}

void solve(int a, int b) {
  // 품종별로 출력
  cout << pSum[b][0] - pSum[a - 1][0] << ' ';
  cout << pSum[b][1] - pSum[a - 1][1] << ' ';
  cout << pSum[b][2] - pSum[a - 1][2] << '\n';
}

int main() {
  input();
  makePSum();

  for (int i = 0; i < q; i++) {
    int a, b;
    cin >> a >> b;
    solve(a, b);
  }

  return 0;
}
