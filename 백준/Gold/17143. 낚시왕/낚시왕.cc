#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

struct Shark {
  int speed;  // 속력
  int dir;    // 이동 방향
  int size;   // 크기
};

int r, c, m;
vector<vector<vector<Shark>>> board;

int DX[5] = {0, -1, 1, 0, 0};
int DY[5] = {0, 0, 0, 1, -1};

int changeDir(int dir) {
  if (dir == 1) return 2;
  if (dir == 2) return 1;
  if (dir == 3) return 4;
  if (dir == 4) return 3;

  return -1;
}

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> r >> c >> m;
  board.resize(r + 1, vector<vector<Shark>>(c + 1));

  for (int i = 0; i < m; i++) {
    int r, c, s, d, z;
    cin >> r >> c >> s >> d >> z;
    board[r][c].push_back({s, d, z});
  }
}

bool isValidRange(int x, int y) { return 1 <= x && x <= r && 1 <= y && y <= c; }

// 한 칸에 상어가 두 마리 이상인 경우, 크기가 가장 큰 상어가 나머지를 잡아먹음
void eatUpShark() {
  for (int i = 1; i <= r; i++) {
    for (int j = 1; j <= c; j++) {
      if (board[i][j].size() >= 2) {
        Shark max_shark = board[i][j][0];

        for (auto shark : board[i][j]) {
          if (shark.size > max_shark.size) {
            max_shark = shark;
          }
        }

        board[i][j] = {max_shark};
      }
    }
  }
}

void moveShark() {
  vector<vector<vector<Shark>>> temp(r + 1, vector<vector<Shark>>(c + 1));

  for (int i = 1; i <= r; i++) {
    for (int j = 1; j <= c; j++) {
      // 해당 칸에 있는 상어들에 대해서
      for (auto shark : board[i][j]) {
        int count = 0;
        int current_x = i, current_y = j;
        int next_x = current_x, next_y = current_y;  // 업데이트 할 위치

        // 실제로 움직여야 하는 거리만을 계산하기
        if (shark.dir == 1 || shark.dir == 2) {
          shark.speed %= (2 * (r - 1));
        } else {
          shark.speed %= (2 * (c - 1));
        }

        while (count < shark.speed) {
          count++;

          next_x = current_x + DX[shark.dir];
          next_y = current_y + DY[shark.dir];

          // 상어가 격자를 넘어가는 경우, 방향을 반대로 전환한다.
          if (!isValidRange(next_x, next_y)) {
            shark.dir = changeDir(shark.dir);
            next_x = current_x + DX[shark.dir];
            next_y = current_y + DY[shark.dir];
          }

          current_x = next_x;
          current_y = next_y;
        }
        temp[next_x][next_y].push_back(shark);
      }
    }
  }

  board = temp;
}

int solve() {
  int answer = 0;    // 낚시왕이 잡은 상어 크기의 합
  int location = 0;  // 낚시왕의 위치

  while (location < c) {
    // 1. 낚시왕이 한 칸 이동한다.
    location++;

    // 2. 상어를 잡는다. 해당 행에서 내려가면서 가장 가까운 상어를 잡는다.
    for (int i = 1; i <= r; i++) {
      if (!board[i][location].empty()) {
        answer += board[i][location][0].size;
        board[i][location].clear();
        break;
      }
    }

    // 3. 상어가 이동한다.
    moveShark();
    eatUpShark();
  }

  return answer;
}

int main() {
  input();
  cout << solve();

  return 0;
}
