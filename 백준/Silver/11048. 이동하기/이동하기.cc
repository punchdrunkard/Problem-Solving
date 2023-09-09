#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

int n, m;
vector<vector<int>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  board.resize(n + 1, vector<int>(m + 1));  // 1-indexed

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
      cin >> board[i][j];
    }
  }
}

bool isValidRange(int x, int y) { return 1 <= x && x <= n && 1 <= y && y <= m; }

const vector<pair<int, int>> DIRS = {{-1, 0}, {0, -1}, {-1, -1}};

int main() {
  input();

  vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
  dp[1][1] = board[1][1];

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
      for (auto DIR : DIRS) {
        pair<int, int> prev = {i + DIR.X, j + DIR.Y};
        if (!isValidRange(prev.X, prev.Y)) continue;
        dp[i][j] = max(dp[i][j], dp[prev.X][prev.Y] + board[i][j]);
      }
    }
  }

  cout << dp[n][m];

  return 0;
}
