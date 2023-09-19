#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

int n, m;
vector<vector<int>> room;

const int DONE = -1;
const int WALL = 1;
const int EMPTY = 0;

struct Robot {
  int x;
  int y;
  int dir;
};

Robot robot;

vector<int> DX = {-1, 0, 1, 0};
vector<int> DY = {0, 1, 0, -1};

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  room.resize(n, vector<int>(m));

  cin >> robot.x >> robot.y >> robot.dir;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> room[i][j];
    }
  }
}

int turn(int d) {
  int res = d - 1;
  return res < 0 ? res + 4 : res;
}

bool isValidRange(int x, int y) { return 0 <= x && x < n && 0 <= y && y < m; }

// x, y 의 주위 4칸에 청소할 수 있는 칸이 있는가?
bool canClean(int x, int y) {
  for (int i = 0; i < 4; i++) {
    int next_x = x + DX[i], next_y = y + DY[i];
    if (!isValidRange(next_x, next_y)) continue;
    if (room[next_x][next_y] == EMPTY) return true;
  }

  return false;
}

int solve() {
  int answer = 0;  // 로봇 청소기가 청소하는 칸의 갯수

  while (true) {
    // 현재 칸이 청소되지 않은 경우,
    if (room[robot.x][robot.y] == EMPTY) {
      // 현재 칸을 청소한다.
      room[robot.x][robot.y] = DONE;
      answer++;
    }
    // 주변 네 칸 중 청소되지 않은 빈칸이 없는 경우
    else if (!canClean(robot.x, robot.y)) {
      // 바라보는 방향을 유지한 채로, 한 칸 후진할 수 있다면 한 칸 후진
      int prev_x = robot.x - DX[robot.dir];
      int prev_y = robot.y - DY[robot.dir];

      if (isValidRange(prev_x, prev_y) && room[prev_x][prev_y] != WALL) {
        robot.x = prev_x;
        robot.y = prev_y;
        continue;
      } else {
        // 후진할 수 없다면 작동을 멈춘다.
        break;
      }
    } else {  // 주변 4칸 중 청소되지 않은 빈칸이 있는 경우

      // 반시계 방향으로 회전
      robot.dir = turn(robot.dir);

      int next_x = robot.x + DX[robot.dir];
      int next_y = robot.y + DY[robot.dir];

      if (isValidRange(next_x, next_y) && room[next_x][next_y] == EMPTY) {
        robot.x = next_x;
        robot.y = next_y;
        continue;
      }
    }
  }

  return answer;
}

int main() {
  input();
  cout << solve();

  return 0;
}
