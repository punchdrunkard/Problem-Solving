#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;
vector<vector<int>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  board.resize(n, vector<int>(m));

  for (int i = 0; i < n; i++) {
    string line;
    cin >> line;

    for (int j = 0; j < m; j++) {
      board[i][j] = line[j] - '0';
    }
  }
}

int main() {
  input();

  // dp[i][j] = board에서 점 (i, j)를 정사각형의 우하단 좌표라고 가정했을 때,
  // 만들 수 있는 가장 큰 정사각형의 한 변의 길이
  int dp[n][m];
  memset(dp, 0, sizeof(dp));

  // initialize
  for (int col = 0; col < m; col++) {
    dp[0][col] = board[0][col];
  }

  for (int row = 0; row < n; row++) {
    dp[row][0] = board[row][0];
  }

  // bottom-up
  for (int i = 1; i < n; i++) {
    for (int j = 1; j < m; j++) {
      if (board[i][j] == 0) {
        dp[i][j] = 0;
      } else {
        dp[i][j] = min({dp[i - 1][j - 1], dp[i][j - 1], dp[i - 1][j]}) + 1;
      }
    }
  }

  int answer = 0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      answer = max(answer, dp[i][j]);
    }
  }

  cout << answer * answer;

  return 0;
}
