#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

typedef long long ll;

using namespace std;

// 이친수는 반드시 1로 시작하고, 연속된 1이 없어야 한다.
// 마지막 항은 1로 시작한다고 하고, 0으로 시작하는 경우와 1로 시작하는 경우의 이친수를 구한다.
// pinary[][1] => 반드시 뒤에 0으로 시작하는 이친수
// pinary[][0] => 0으로 시작하는 이친수 || 1로 시작하는 이친수

// 따라서 pinary[i][0]의 경우, 0으로 시작하는 i자리 이친수

int main() {
  FASTIO;

  int n;
  cin >> n;

  vector<vector<ll>> pinary(n + 1, vector<ll>(n + 1, 0));

  pinary[1][0] = 1;
  pinary[1][1] = 1;

  for (int i = 2; i <= n; i++) {
    pinary[i][0] = pinary[i - 1][0] + pinary[i - 1][1];
    pinary[i][1] = pinary[i - 1][0];  // 연속된 1이 없어야 하므로
  }

  cout << pinary[n][1];

  return 0;
}
