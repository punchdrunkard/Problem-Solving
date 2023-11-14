#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

vector<vector<int>> board(9, vector<int>(9));

int blank_index = 0;            // 현재 빈칸의 상태 인덱스
vector<pair<int, int>> blanks;  // 빈칸의 정보

// row[i][j] : i번째 row에 j라는 숫자가 존재하는가?
vector<vector<bool>> row(9, vector<bool>(10, false)),
    col(9, vector<bool>(10, false)), square(9, vector<bool>(10, false));

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
      cin >> board[i][j];

      if (board[i][j] == 0) {
        blanks.push_back({i, j});
      } else {
        row[i][board[i][j]] = true;
        col[j][board[i][j]] = true;
        square[(i / 3) * 3 + (j / 3)][board[i][j]] = true;
      }
    }
  }
}

void printBoard() {
  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
      cout << board[i][j] << " ";
    }
    cout << "\n";
  }
}

// TODO: 첫 번재 답을 찾은 후 백트래킹을 중단해야 한다.
// 답을 찾으면 true를 반환한다.
bool backtracking(int idx) {
  // 종료 조건 : 빈칸이 모두 채워진 경우
  if (idx == blanks.size()) {
    printBoard();
    return true;  // 답을 찾음
  }

  pair<int, int> current = blanks[idx];

  // 빈칸에 1 ~ 9까지의 숫자를 모두 대입한다.
  for (int i = 1; i <= 9; i++) {
    if (!row[current.X][i] && !col[current.Y][i] &&
        !square[(current.X / 3) * 3 + (current.Y / 3)][i]) {
      board[current.X][current.Y] = i;
      row[current.X][i] = true;
      col[current.Y][i] = true;
      square[(current.X / 3) * 3 + (current.Y / 3)][i] = true;

      if (backtracking(idx + 1)) return true;  // 답을 찾으면 백트래킹을 중단

      board[current.X][current.Y] = 0;
      row[current.X][i] = false;
      col[current.Y][i] = false;
      square[(current.X / 3) * 3 + (current.Y / 3)][i] = false;
    }
  }

  // 이 경로에서는 해를 찾지 못함
  return false;
}

int main() {
  input();
  backtracking(0);

  return 0;
}
