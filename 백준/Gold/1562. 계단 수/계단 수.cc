#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

typedef long long ll;

const int MOD = 1000000000;
int n;

// dp[자릿수][마지막으로 사용된 숫자][현재 state]
ll dp[101][10][1 << 10];

using namespace std;

int main() {
  FASTIO;
  cin >> n;

  ll answer = 0;

  // dp init
  for (int i = 1; i <= 9; i++) {
    dp[1][i][1 << i] = 1;
  }

  for (int length = 2; length <= n; length++) {
    for (int last = 0; last <= 9; last++) {
      for (int mask = 0; mask < (1 << 10); mask++) {
        if (last > 0) {
          dp[length][last][mask | (1 << last)] +=
              (dp[length - 1][last - 1][mask] % MOD);
        }

        if (last < 9) {
          dp[length][last][mask | (1 << last)] +=
              (dp[length - 1][last + 1][mask] % MOD);
        }
      }
    }
  }

  for (int i = 0; i <= 9; i++) {
    answer += (dp[n][i][(1 << 10) - 1] % MOD);
  }

  cout << answer % MOD;

  return 0;
}
