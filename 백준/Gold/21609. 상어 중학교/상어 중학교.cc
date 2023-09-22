#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

const int RAINBOW = 0;
const int BLACK = -1;
const int EMPTY = -2;

int DX[4] = {1, -1, 0, 0};
int DY[4] = {0, 0, 1, -1};

using namespace std;

struct BFSResult {
  int size;                       // 블럭 그룹 사이즈
  int rainbow_count;              // 무지개 블럭 카운트
  vector<pair<int, int>> points;  // 좌표
};

int n, m;
vector<vector<int>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;

  board.resize(n, vector<int>(n));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> board[i][j];
    }
  }
}

void applyGravity() {
  vector<vector<int>> result(n, vector<int>(n, EMPTY));

  for (int col = 0; col < n; col++) {
    int temp_row = n - 1;

    for (int row = n - 1; row >= 0; row--) {
      if (board[row][col] == BLACK) {
        temp_row = row;
        result[temp_row][col] = BLACK;
        temp_row--;
      } else if (board[row][col] == EMPTY) {
        continue;
      } else {
        result[temp_row][col] = board[row][col];
        temp_row--;
      }
    }
  }

  board = result;
}

void rotate() {
  vector<vector<int>> result(n, vector<int>(n));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      result[n - j - 1][i] = board[i][j];
    }
  }

  board = result;
}

void resetRainbowVisited(vector<vector<bool>>& visited) {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (board[i][j] == RAINBOW) {
        visited[i][j] = false;
      }
    }
  }
}

bool isValidRange(pair<int, int> p) {
  return 0 <= p.X && p.X < n && 0 <= p.Y && p.Y < n;
}

BFSResult bfs(int x, int y, vector<vector<bool>>& visited, int color) {
  int size = 1, rainbow_count = 0;
  vector<pair<int, int>> points;

  queue<pair<int, int>> q;

  q.push({x, y});
  points.push_back({x, y});
  visited[x][y] = true;

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      pair<int, int> next = {current.X + DX[dir], current.Y + DY[dir]};

      if (!isValidRange(next)) continue;

      if (board[next.X][next.Y] == BLACK) continue;
      if (visited[next.X][next.Y]) continue;

      if (!(board[next.X][next.Y] == RAINBOW || board[next.X][next.Y] == color))
        continue;

      if (board[next.X][next.Y] == RAINBOW) {
        rainbow_count++;
      }

      q.push(next);
      points.push_back(next);
      size++;
      visited[next.X][next.Y] = true;
    }
  }

  return {size, rainbow_count, points};
}

bool canVisit(int x, int y, vector<vector<bool>>& visited) {
  return board[x][y] != BLACK && board[x][y] != EMPTY &&
         board[x][y] != RAINBOW && !visited[x][y];
}

BFSResult compareBFSResults(const BFSResult& oldResult,
                            const BFSResult& newResult,
                            pair<int, int>& standard, int i, int j) {
  if (oldResult.size < newResult.size) {
    standard = {i, j};
    return newResult;
  } else if (oldResult.size == newResult.size) {
    if (oldResult.rainbow_count < newResult.rainbow_count) {
      standard = {i, j};
      return newResult;
    } else if (oldResult.rainbow_count == newResult.rainbow_count) {
      if (standard.X < i) {
        standard = {i, j};
        return newResult;
      } else if (standard.X == i) {
        if (standard.Y < j) {
          standard = {i, j};
          return newResult;
        }
      }
    }
  }
  return oldResult;
}

int solve() {
  int score = 0;

  while (true) {
    BFSResult result = {-1, -1, {}};
    pair<int, int> standard = {0, 0};  // 기준 블록의 좌표

    vector<vector<bool>> visited(n, vector<bool>(n, false));

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (canVisit(i, j, visited)) {
          BFSResult temp = bfs(i, j, visited, board[i][j]);
          result = compareBFSResults(result, temp, standard, i, j);
          resetRainbowVisited(visited);
        }
      }
    }

    if (result.size <= 1) break;

    for (auto p : result.points) {
      board[p.X][p.Y] = EMPTY;
    }

    score += (result.size * result.size);

    applyGravity();
    rotate();
    applyGravity();
  }  // end of while

  return score;
}

int main() {
  input();
  cout << solve();

  return 0;
}
