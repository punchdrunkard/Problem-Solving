#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX 101

typedef long long ll;

using namespace std;

const ll MOD = 1'000'000'000;

int n;

int main() {
  FASTIO;

  // freopen("sample_input.txt", "r", stdin);

  ll n;
  cin >> n;

  // dp[i][j] : i 자릿수 계단수, 맨 앞 숫자는 0, 1, 2 (others)
  ll dp[n + 1][10];

  // init
  for (int i = 0; i < 10; i++) {
    dp[1][i] = 1;  // 자기 자신
  }

  for (int i = 2; i <= n; i++) {    // i 자릿수
    for (int j = 0; j < 10; j++) {  // j로 시작하는 수
      if (j == 0) {
        dp[i][j] = dp[i - 1][1] % MOD;
      } else if (j == 9) {
        dp[i][j] = dp[i - 1][j - 1] % MOD;
      } else {
        dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1]) % MOD;
      }
    }
  }

  // 정답 : 0을 제외한 n자리 계단수
  ll answer = 0;

  for (int i = 1; i <= 9; i++) {
    answer += (dp[n][i] % MOD);
  }

  cout << answer % MOD;

  return 0;
}
