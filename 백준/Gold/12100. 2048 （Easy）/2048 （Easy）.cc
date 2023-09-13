#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;  // 보드의 크기
vector<vector<int>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  board.resize(n, vector<int>(n));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> board[i][j];
    }
  }
}

int findMaxVal(vector<vector<int>> &board) {
  int max_value = 2;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      max_value = max(max_value, board[i][j]);
    }
  }

  return max_value;
}

vector<vector<int>> sequences;

void makeSequence(vector<int> sequence) {
  if (sequence.size() == 5) {
    sequences.push_back(sequence);
    return;
  }

  for (int i = 0; i < 4; i++) {
    sequence.push_back(i);
    makeSequence(sequence);
    sequence.pop_back();
  }
}

enum Direction { LEFT = 0, RIGHT = 1, DOWN = 2, UP = 3 };

// 배열을 돌린다.
void rotate(vector<vector<int>> &board) {
  vector<vector<int>> temp = board;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      board[j][i] = temp[i][j];
    }
  }
}

void move(vector<vector<int>> &board, int dir) {
  // 한 번의 iteration : 해당 블록을 합치고, 해당 블록 이전의 부분을 민다.
  vector<bool> merged(n, false);
  vector<int> temp(n, 0);  // 이동한 결과를 담을 임시 배열

  if (dir == UP || dir == DOWN) {
    rotate(board);
  }

  if (dir == LEFT || dir == UP) {
    for (int i = 0; i < n; i++) {  // 각 행에 대하여
      for (int j = 0; j < n; j++) {
        if (board[i][j] == 0) {  // 현재 빈칸이면 넘어간다.
          continue;
        }

        if (temp[0] == 0) {  // 처음으로 원소가 밀리는 경우
          temp[0] = board[i][j];
          continue;
        }

        // 빈칸이 아니라면, 왼쪽으로 갈 수 있을만큼 넘긴다.
        int idx = j;

        while (temp[idx] == 0) {
          idx--;
        }

        // 합칠 수 있는가?
        if (!merged[idx] && temp[idx] == board[i][j]) {
          temp[idx] *= 2;
          merged[idx] = true;
        } else {  // 합칠 수 없다면 빈칸에 저장한다.
          temp[idx + 1] = board[i][j];
        }
      }

      board[i] = temp;
      fill(temp.begin(), temp.end(), 0);
      fill(merged.begin(), merged.end(), false);
    }
  }

  if (dir == RIGHT || dir == DOWN) {
    for (int i = 0; i < n; i++) {
      for (int j = n - 1; j >= 0; j--) {
        if (board[i][j] == 0) {
          continue;
        }

        if (temp[n - 1] == 0) {
          temp[n - 1] = board[i][j];
          continue;
        }

        int idx = j;
        while (temp[idx] == 0) {
          idx++;
        }

        if (!merged[idx] && temp[idx] == board[i][j]) {
          temp[idx] *= 2;
          merged[idx] = true;
        } else {
          temp[idx - 1] = board[i][j];
        }
      }

      board[i] = temp;

      fill(temp.begin(), temp.end(), 0);
      fill(merged.begin(), merged.end(), false);
    }
  }

  if (dir == UP || dir == DOWN) {
    rotate(board);
  }
}

int main() {
  input();
  makeSequence({});

  int answer = 2;

  for (auto &sequence : sequences) {
    vector<vector<int>> temp = board;
    for (auto &dir : sequence) {
      move(temp, dir);
    }
    answer = max(answer, findMaxVal(temp));
  }

  cout << answer;

  return 0;
}
