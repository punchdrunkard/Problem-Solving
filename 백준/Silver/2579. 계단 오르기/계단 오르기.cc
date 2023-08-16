#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;  // 계단의 갯수
vector<int> stair;
vector<vector<int>> dp;

void init() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  stair.resize(n + 1);

  for (int i = 1; i <= n; i++) {
    cin >> stair[i];
  }
}

int solve(int n) {
  // dp initialize, 아직 방문하지 않았으면 -1
  dp.resize(n + 1, vector<int>(n + 1, -1));

  // n이 1일떄 예외처리!
  if (n == 1) return stair[1];

  // 초기값
  dp[1][1] = stair[1];
  dp[1][2] = 0;
  dp[2][1] = stair[2];
  dp[2][2] = stair[1] + stair[2];

  for (int i = 3; i <= n; i++) {
    dp[i][1] = max(dp[i - 2][1], dp[i - 2][2]) + stair[i];
    dp[i][2] = dp[i - 1][1] + stair[i];
  }

  return max(dp[n][1], dp[n][2]);
}

int main() {
  init();
  cout << solve(n);

  return 0;
}
