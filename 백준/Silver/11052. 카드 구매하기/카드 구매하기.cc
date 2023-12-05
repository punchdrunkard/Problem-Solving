#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;
vector<int> p, dp;

void input() {
  FASTIO;

  cin >> n;
  p.resize(n + 1);

  for (int i = 1; i <= n; i++) {
    cin >> p[i];
  }
}

void preprocess() {
  dp.resize(n + 1);
  dp[0] = 0;
  dp[1] = p[1];
}

void solve() {
  for (int i = 2; i <= n; i++) {
    dp[i] = -1;

    for (int j = 1; j <= i; j++) {
      dp[i] = max(dp[i], (dp[i - j] + p[j]));
    }
  }

  cout << dp[n];
}

int main() {
  input();
  preprocess();
  solve();

  return 0;
}
