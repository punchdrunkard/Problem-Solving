#include <bits/stdc++.h>
using namespace std;

#define W_MAX 100001
#define N_MAX 101

int n, k;
int w[N_MAX], v[N_MAX];

// dp[i][j] : i번째 물건까지 고려했을 때, 최대 용량이 j일 때 최대 가치
int dp[N_MAX][W_MAX];

void input() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> k;

  for (int i = 0; i < n; i++) {
    cin >> w[i] >> v[i];
  }
}

int main() {
  input();

  for (int i = 0; i < n; i++) {
    for (int j = 0; j <= k; j++) {
      // 인덱스가 0인 경우 (base condition)
      if (i == 0) {
        if (w[i] <= j) {
          dp[i][j] = v[i];
        }
        continue;
      }

      // 현재 item을 담지 못하는 경우
      if (w[i] > j) {
        dp[i][j] = dp[i - 1][j];
      } else {
        dp[i][j] = max(dp[i - 1][j], v[i] + dp[i - 1][j - w[i]]);
      }
    }
  }

  cout << dp[n - 1][k];

  return 0;
}
