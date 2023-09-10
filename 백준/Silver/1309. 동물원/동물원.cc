#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define MOD 9901

using namespace std;

int n;  // 우리의 크기

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
}

int main() {
  input();
  vector<vector<int>> dp(n + 1, vector<int>(2));  // N행, 2열

  dp[1][0] = 1;  // 두 칸 모두 배치하지 않을 경우
  dp[1][1] = 1;  // 두 칸 중 왼쪽에만 배치
  dp[1][2] = 1;  // 두 칸 중 오른쪽에만 배치

  for (int i = 2; i <= n; i++) {
    dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
    dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
    dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
  }

  int answer = 0;

  for (int i = 0; i <= 2; i++) {
    answer += (dp[n][i] % MOD);
  }

  cout << answer % MOD;

  return 0;
}
