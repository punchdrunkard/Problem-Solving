#include <bits/stdc++.h>
#define MAX 31

using namespace std;

int board[MAX][MAX];

int r, c, w;

int solve() {
  board[1][1] = 1;

  for (int i = 2; i < MAX; i++) {
    for (int j = 1; j <= i; j++) {
      board[i][j] = board[i - 1][j - 1] + board[i - 1][j];
    }
  }

  long long answer = 0;

  for (int i = r; i < r + w; i++) {
    for (int j = c; j < c + i - r + 1; j++) {
      answer += board[i][j];
    }
    }

  return answer;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> r >> c >> w;
  cout << solve();

  return 0;
}
