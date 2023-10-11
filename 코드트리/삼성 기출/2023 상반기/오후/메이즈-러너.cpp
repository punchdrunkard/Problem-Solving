#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

int DX[4] = {-1, 1, 0, 0};
int DY[4] = {0, 0, -1, 1};

struct MazeInfo {
  int status;   // 미로 칸의 상태: 0 (빈칸), 1 ~ 9 (벽)
  int count;    // 현재 칸에 있는 사람들의 수
  bool isExit;  // 출구 여부
};

vector<vector<MazeInfo>> maze;

int n, m, k;

// TODO: 모든 참가자들의 이동 거리 합, 출구의 좌표
int dist_sum;
int exit_x, exit_y;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> k;
  maze.resize(n + 1, vector<MazeInfo>(n + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cin >> maze[i][j].status;
      maze[i][j].count = 0;
      maze[i][j].isExit = false;
    }
  }

  for (int i = 0; i < m; i++) {
    int x, y;
    cin >> x >> y;
    maze[x][y].count++;
  }

  // 출구의 좌표
  cin >> exit_x >> exit_y;
  maze[exit_x][exit_y].isExit = true;
}

void printPeopleCount() {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cout << maze[i][j].count << ' ';
    }
    cout << '\n';
  }
}

void printMaze() {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cout << maze[i][j].status << ' ';
    }
    cout << '\n';
  }
}

// 모든 참가자가 탈출에 성공 -> 게임의 끝
bool isEnd() {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (maze[i][j].count != 0) return false;
    }
  }

  return true;
}

bool isValidRange(pair<int, int> p) {
  return 1 <= p.X && p.X <= n && 1 <= p.Y && p.Y <= n;
}

// 최단 거리 := abs(x1 - x2) + abs(y1 - y2)
int getDist(pair<int, int> p1, pair<int, int> p2) {
  return abs(p1.X - p2.X) + abs(p1.Y - p2.Y);
}

// 참가자들이 다음으로 이동할 좌표를 구함
pair<int, int> getNextPoint(int x, int y) {
  vector<pair<int, int>> locations;

  // 상, 하, 좌, 우 로 탐색
  for (int dir = 0; dir < 4; dir++) {
    pair<int, int> next = {x + DX[dir], y + DY[dir]};

    // 범위 체크
    if (!isValidRange(next)) continue;

    // 벽이 없는 곳으로만 이동
    if (maze[next.X][next.Y].status != 0) continue;

    // 움직인 칸은 현재 머물러 있던 칸 보다 출구까지의 최단 거리가 가까워야 함
    if (!(getDist(next, {exit_x, exit_y}) < getDist({x, y}, {exit_x, exit_y})))
      continue;

    locations.push_back(next);
  }

  // 참가자가 움직일 수 없으면 움직이지 않는다.
  if (locations.empty()) return {x, y};
  // 움직일 수 있는 칸이 2개 이상 -> 상/하 우선
  return locations[0];
}

// 참가자의 움직임 (1초 마다)
void moveParticipants() {
  // 이동 완료한 사람들을 담아두는 배열
  vector<vector<int>> temp(n + 1, vector<int>(n + 1, 0));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (maze[i][j].isExit) continue;
      if (maze[i][j].count == 0) continue;  // 사람이 없으면..

      pair<int, int> next = getNextPoint(i, j);
      dist_sum += (maze[i][j].count * getDist(next, {i, j}));

      if (maze[next.X][next.Y].isExit) continue;  // 출구면 그 사람들 사라짐
      temp[next.X][next.Y] += maze[i][j].count;  // 모든 참가자는 동시에 움직임
    }
  }

  // 사람들을 움직이고, 원래 배열에 복사
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      maze[i][j].count = temp[i][j];
    }
  }
}

// 최소 정사각형의 크기 구하기
int findMinLength() {
  int size = 1e6;  // 정사각형의 한 변 길이

  // 사람이 있는 칸과 출구를 포함하는 정사각형의 최소 길이를 찾는다.
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (maze[i][j].count == 0) continue;
      size = min(size, max(abs(exit_x - i), abs(exit_y - j)) + 1);
    }
  }

  return size;
}

// 최소 정사각형의 좌상단 좌표 구하기
pair<int, int> findSquarePoint(int size) {
  // (i, j)가 좌상단일 때, 정사각형의 조건을 만족하는지 확인
  int best_x, best_y;
  bool find = false;

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      bool exitFlag = false;
      bool pFlag = false;

      // (i, j)가 좌상단인 정사각형 내부의 점 (r, c)에 대하여

      // 경계 확인
      if (!isValidRange({i + size - 1, j + size - 1})) continue;

      for (int r = i; r < i + size; r++) {
        for (int c = j; c < j + size; c++) {
          if (maze[r][c].isExit) exitFlag = true;
          if (maze[r][c].count >= 1) pFlag = true;

          if (exitFlag && pFlag) {
            find = true;
            best_x = i;
            best_y = j;
            break;
          }
        }
      }  // end-of-rc-loop

      if (find) break;
    }
    if (find) break;
  }

  return {best_x, best_y};
}

// 좌상단 좌표가 (x, y)이고 길이가 length인 정사각형을 시계 90도 회전
void rotateSquare(int x, int y, int length) {
  vector<vector<MazeInfo>> temp(length + 1, vector<MazeInfo>(length + 1));
  vector<vector<MazeInfo>> original(length + 1, vector<MazeInfo>(length + 1));

  // 원래 배열 복사
  for (int i = x; i < x + length; i++) {
    for (int j = y; j < y + length; j++) {
      original[i - x + 1][j - y + 1] = maze[i][j];
    }
  }

  // 배열 돌리기
  for (int i = 1; i <= length; i++) {
    for (int j = 1; j <= length; j++) {
      temp[i][j] = original[length - j + 1][i];
    }
  }

  // 원래 배열에 내용 반영

  for (int i = x; i < x + length; i++) {
    for (int j = y; j < y + length; j++) {
      maze[i][j] = temp[i - x + 1][j - y + 1];

      // 회전된 벽은 내구도가 1씩 깎인다.
      if (maze[i][j].status > 0) {
        maze[i][j].status--;
      }

      if (maze[i][j].isExit) {
        exit_x = i;
        exit_y = j;
      }
    }
  }
}

// 미로의 회전
void rotateMaze() {
  // 한 명 이상의 참가자와 출구를 포함한 가장 작은 정사각형을 잡기
  int size = findMinLength();                   // 최소 정사각형의 크기
  pair<int, int> best = findSquarePoint(size);  // 최소 정사각형의 좌상단 좌표

  rotateSquare(best.X, best.Y, size);
}

void solve() {
  // k 초 동안 과정 반복.
  // k초 전에 모든 참가자가 탈출에 성공 -> 게임의 끝
  while (k--) {
    moveParticipants();
    if (isEnd()) break;
    rotateMaze();
  }
}

void printAnswer() {
  cout << dist_sum << "\n" << exit_x << " " << exit_y << "\n";
}

int main() {
  input();
  solve();
  printAnswer();

  return 0;
}
