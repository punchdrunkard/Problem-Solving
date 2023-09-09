#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MOD 10007

using namespace std;

int main() {
  FASTIO;

  int n;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;

  vector<vector<int>> dp(n + 1, vector<int>(10, 0));

  // dp[i][j] : j로 시작하는 길이가 i인 오르막 수

  // 길이가 1인 오르막수는 하나씩이다.
  for (int i = 0; i <= 9; i++) {
    dp[1][i] = 1;
  }

  for (int i = 2; i <= n; i++) {
    for (int j = 0; j <= 9; j++) {
      for (int k = 0; k <= j; k++) {
        dp[i][j] += (dp[i - 1][k] % MOD);
      }
    }
  }

  int answer = 0;

  for (int i = 0; i <= 9; i++) {
    answer += (dp[n][i] % MOD);
  }

  cout << answer % MOD;

  return 0;
}
