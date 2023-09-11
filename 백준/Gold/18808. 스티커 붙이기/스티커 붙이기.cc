#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m, k;  // 가로, 세로, 스티커의 수
vector<vector<int>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> k;
  board.resize(n, vector<int>(m, 0));
}

// 90도씩 스티커를 돌리는 함수
void rotate(vector<vector<int>> &sticker, int r, int c) {
  vector<vector<int>> temp(c, vector<int>(r));

  for (int i = 0; i < r; i++) {
    for (int j = 0; j < c; j++) {
      temp[j][r - i - 1] = sticker[i][j];
    }
  }

  sticker = temp;
}

// 스티커를 붙이는 함수
// 스티커, 스티커의 세로 길이, 스티커의 가로 길이
bool stick(vector<vector<int>> &sticker, int r, int c) {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (i + r > n || j + c > m) continue;

      // cout << "valid..\n";

      vector<vector<int>> temp(r, vector<int>(c));

      for (int di = 0; di < r; di++) {
        for (int dj = 0; dj < c; dj++) {
          temp[di][dj] = board[i + di][j + dj];
        }
      }

      bool flag = true;

      // 잘라낸 부분
      for (int di = 0; di < r; di++) {
        for (int dj = 0; dj < c; dj++) {
          if (sticker[di][dj] == 1) {  // 스티커를 붙여야 되는 부분
            if (temp[di][dj] == 1) {   // 붙일 수 없는 경우
              flag = false;
              break;
            } else {  // 붙일 수 있는 경우
              temp[di][dj] = 1;
            }
          }
        }
      }

      if (flag) {
        for (int di = 0; di < r; di++) {
          for (int dj = 0; dj < c; dj++) {
            board[i + di][j + dj] = temp[di][dj];
          }
        }

        return true;
      }
    }
  }

  return false;
}

int main() {
  input();

  // TODO: 나중에 bool로 바꿔서 메모리 아끼기
  for (int s = 0; s < k; s++) {
    // 스티커가 붙어있는 모눈 종이
    int s_row, s_col;
    cin >> s_row >> s_col;

    vector<vector<int>> sticker(s_row, vector<int>(s_col));

    // 스티커 입력
    for (int i = 0; i < s_row; i++) {
      for (int j = 0; j < s_col; j++) {
        cin >> sticker[i][j];
      }
    }

    // 붙여본다.
    if (stick(sticker, s_row, s_col)) {  // 0도 회전

      continue;
    }

    // 90도 회전
    rotate(sticker, s_row, s_col);

    if (stick(sticker, s_col, s_row)) {
      continue;
    }

    // 180도 회전
    rotate(sticker, s_col, s_row);

    if (stick(sticker, s_row, s_col)) {
      continue;
    }

    // 270도 회전
    rotate(sticker, s_row, s_col);

    if (stick(sticker, s_col, s_row)) {
      continue;
    }
  }

  int answer = 0;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (board[i][j] == 1) {
        answer++;
      }
    }
  }

  cout << answer;

  return 0;
}
