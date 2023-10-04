#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

enum DIRS { EAST, SOUTH, WEST, NORTH };
int DX[4] = {0, 1, 0, -1};
int DY[4] = {1, 0, -1, 0};

int n, m, k;
vector<vector<int>> board;
// 현재 주사위에서 1번면 (윗면) ~ 6번면 (바닥면)
vector<int> dice = {0, 1, 2, 3, 4, 5, 6};
int currentDir = EAST;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> k;
  board.resize(n, vector<int>(m));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> board[i][j];
    }
  }
}

int changeDirection(string type, int dir) {
  if (type == "CW") {
    return (dir + 1) % 4;
  } else if (type == "CCW") {
    return (dir - 1 + 4) % 4;
  } else {
    if (dir == 0) return 2;
    if (dir == 1) return 3;
    if (dir == 2) return 0;
    if (dir == 3) return 1;
  }

  return -1;
}

void moveDice(int dir) {
  vector<int> temp = dice;

  switch (dir) {
    case EAST: {
      dice[1] = temp[4];
      dice[3] = temp[1];
      dice[4] = temp[6];
      dice[6] = temp[3];
      break;
    }
    case SOUTH: {
      dice[1] = temp[2];
      dice[2] = temp[6];
      dice[6] = temp[5];
      dice[5] = temp[1];
      break;
    }
    case WEST: {
      dice[1] = temp[3];
      dice[6] = temp[4];
      dice[4] = temp[1];
      dice[3] = temp[6];
      break;
    }
    case NORTH: {
      dice[2] = temp[1];
      dice[1] = temp[5];
      dice[5] = temp[6];
      dice[6] = temp[2];
      break;
    }
  }
}

bool isValidRange(pair<int, int> p) {
  return 0 <= p.X && p.X < n && 0 <= p.Y && p.Y < m;
}

// connected component 갯수 구하기
int bfs(int x, int y) {
  int count = 1;
  queue<pair<int, int>> q;
  vector<vector<bool>> visited(n, vector<bool>(m, false));

  q.push({x, y});
  visited[x][y] = true;

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      pair<int, int> next = {current.X + DX[dir], current.Y + DY[dir]};

      if (!isValidRange(next)) continue;
      if (visited[next.X][next.Y]) continue;
      if (board[x][y] != board[next.X][next.Y]) continue;

      q.push(next);
      visited[next.X][next.Y] = true;
      count++;
    }
  }

  return count;
}

int decideNextDirection(pair<int, int> current) {
  int A = dice[6];
  int B = board[current.X][current.Y];

  if (A > B) return changeDirection("CW", currentDir);
  if (A < B) return changeDirection("CCW", currentDir);

  return currentDir;
}

int solve() {
  int score = 0;
  int x = 0, y = 0;  // 현재 좌표

  // k번 이동
  for (int move = 0; move < k; move++) {
    // 1. 주사위가 이동 방향으로 한 칸 굴러간다.
    pair<int, int> next = {x + DX[currentDir], y + DY[currentDir]};

    if (!isValidRange(next)) {
      // 이동 방향을 반대로 한 다음, 한 칸 굴러간다
      currentDir = changeDirection("REVERSE", currentDir);
      next = {x + DX[currentDir], y + DY[currentDir]};
    }

    moveDice(currentDir);
    x = next.X;
    y = next.Y;

    // 2. 주사위가 도착한 칸 (x, y)에 대한 점수를 획득한다.
    int c = bfs(x, y);
    score += (c * board[x][y]);

    // 3. 이동방향 결정
    currentDir = decideNextDirection({x, y});
  }

  return score;
}

int main() {
  input();
  cout << solve();

  return 0;
}
