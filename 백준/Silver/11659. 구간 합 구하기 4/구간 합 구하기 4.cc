#include <bits/stdc++.h>
using namespace std;

#define MAX 100001

int dp[MAX];
int numbers[MAX];

void compute(int n) {
  dp[1] = numbers[1];

  for (int i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + numbers[i];
  }
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n, m;
  cin >> n >> m;

  for (int i = 1; i <= n; i++) {
    cin >> numbers[i];
  }

  compute(n);

  for (int i = 0; i < m; i++) {
    int a, b;
    cin >> a >> b;
    cout << dp[b] - dp[a - 1] << '\n';
  }

  return 0;
}
