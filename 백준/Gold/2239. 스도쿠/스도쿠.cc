#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

vector<vector<int>> board(9, vector<int>(9, 0));
vector<pair<int, int>> empty_info;  // 빈칸의 정보 추적용

// 현재 row에 그 숫자가 있는지
vector<vector<bool>> row_info(9, vector<bool>(10, false));

// 현재 col에 그 숫자가 있는지
vector<vector<bool>> col_info(9, vector<bool>(10, false));

// 현재 square에 그 숫자가 있는지 (0번 square ~ 8번 square)
vector<vector<bool>> square_info(9, vector<bool>(10, false));

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  for (int i = 0; i < 9; i++) {
    string row;
    cin >> row;

    for (int j = 0; j < 9; j++) {
      board[i][j] = row[j] - '0';

      if (board[i][j] == 0) {
        empty_info.push_back({i, j});
      } else {
        row_info[i][board[i][j]] = true;
        col_info[j][board[i][j]] = true;
        square_info[(i / 3) * 3 + (j / 3)][board[i][j]] = true;
      }
    }
  }
}

void print() {
  // cout << "print\n";

  for (int i = 0; i < 9; i++) {
    for (int j = 0; j < 9; j++) {
      cout << board[i][j];
    }
    cout << '\n';
  }
}

// 각 행에 1부터 9까지의 숫자가 중복없이 나와야 함.
// 모든 경우의 수를 확인한다.
bool solve(int idx) {  // 현재 빈칸 정보 idx
  // 답을 찾은 경우
  if (idx == empty_info.size()) {
    print();
    return true;
  }

  // 현재 빈칸을 채우기
  pair<int, int> current = empty_info[idx];

  for (int k = 1; k <= 9; k++) {
    if (!row_info[current.X][k] && !col_info[current.Y][k] &&
        !square_info[(current.X / 3) * 3 + current.Y / 3][k]) {
      board[current.X][current.Y] = k;
      row_info[current.X][k] = true;
      col_info[current.Y][k] = true;
      square_info[(current.X / 3) * 3 + current.Y / 3][k] = true;

      if (solve(idx + 1)) {
        return true;
      }

      // backtrack
      board[current.X][current.Y] = 0;
      row_info[current.X][k] = false;
      col_info[current.Y][k] = false;
      square_info[(current.X / 3) * 3 + current.Y / 3][k] = false;
    }
  }

  return false;  // 이 경로에서 해결책을 찾지 못함.
}

int main() {
  input();
  solve(0);

  return 0;
}
