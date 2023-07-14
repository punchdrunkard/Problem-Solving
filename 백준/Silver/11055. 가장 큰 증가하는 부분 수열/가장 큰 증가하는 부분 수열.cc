#include <bits/stdc++.h>
#define MAX 1001

using namespace std;

int n;
vector<int> a, dp;

void input() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  cin >> n;
  a.resize(n);
  dp.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> a[i];
  }
}

void init() {
  for (int i = 0; i < n; i++) {
    dp[i] = a[i];
  }
}

int solve() {
  int answer = 0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < i; j++) {
      if (a[j] < a[i]) {
        dp[i] = max(dp[i], dp[j] + a[i]);
      }
    }

    answer = max(dp[i], answer);
  }

  return answer;
}

int main() {
  input();
  init();
  cout << solve();

  return 0;
}
