#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;
vector<vector<int>> board, pSum;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  board.resize(n + 1, vector<int>(n + 1, 0));  // 1-indexed
  // pSum[i][j] = (0, 0) ~ (i, j) 까지의 구간 합
  pSum.resize(n + 1, vector<int>(n + 1, 0));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cin >> board[i][j];
    }
  }
}

void makePsum() {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      pSum[i][j] =
          pSum[i - 1][j] + pSum[i][j - 1] + board[i][j] - pSum[i - 1][j - 1];
    }
  }
}

int solve(int x1, int y1, int x2, int y2) {
  return pSum[x2][y2] - pSum[x1 - 1][y2] - pSum[x2][y1 - 1] +
         pSum[x1 - 1][y1 - 1];
}

int main() {
  input();
  makePsum();

  for (int i = 0; i < m; i++) {
    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;

    cout << solve(x1, y1, x2, y2) << '\n';
  }

  return 0;
}
