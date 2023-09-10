#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

typedef long long ll;
using namespace std;

int n;  // 게임판의 크기
vector<vector<int>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  board.resize(n + 1, vector<int>(n + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cin >> board[i][j];
    }
  }
}

bool isValidRange(int x, int y) { return 1 <= x && x <= n && 1 <= y && y <= n; }

int main() {
  input();

  vector<vector<ll>> dp(n + 1, vector<ll>(n + 1, 0));
  dp[1][1] = 1;  // 시작점 초기화

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      int val = board[i][j];
      if (val == 0) continue;

      // 오른쪽으로 이동
      if (isValidRange(i, j + val)) {
        dp[i][j + val] += dp[i][j];
      }

      // 아래로 이동
      if (isValidRange(i + val, j)) {
        dp[i + val][j] += dp[i][j];
      }
    }
  }

  cout << dp[n][n] << '\n';

  return 0;
}
