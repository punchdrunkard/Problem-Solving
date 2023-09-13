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

enum Direction { LEFT = 0, DOWN = 1, RIGHT = 2, UP = 3 };

void rotate90Degree(vector<vector<int>> &board) {
  vector<vector<int>> temp = board;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      board[n - j - 1][i] = temp[i][j];
    }
  }
}

void move(vector<vector<int>> &board, int dir) {
  for (int i = 0; i < dir; i++) {
    rotate90Degree(board);
  }

  for (int i = 0; i < n; i++) {  // 각 행에 대하여
    int idx = 0;                 // 마지막으로 처리된 인덱스
    vector<int> temp(n, 0);

    for (int j = 0; j < n; j++) {
      if (board[i][j] == 0) {
        continue;
      }

      if (temp[idx] == 0) {  // 빈칸인 경우
        temp[idx] = board[i][j];
      } else if (temp[idx] == board[i][j]) {
        temp[idx] *= 2;
        idx++;
      } else {
        ++idx;
        temp[idx] = board[i][j];
      }
    }

    board[i] = temp;
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
