#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

struct Shark {
  int x;
  int y;
  int dir;
  bool exist;  // 현재 보드에 상어가 존재하는가?
  vector<vector<int>> dir_table;
};

struct Board_Info {
  vector<int> sharks;  // 해당 칸에 존재하는 상어들
  int smell_host;      // 냄새 주인
  int smell_time;      // 냄새 남은 시간
};

int n, m, k;
vector<vector<Board_Info>> board;
vector<Shark> sharks;

vector<int> DX = {-1, 1, 0, 0};
vector<int> DY = {0, 0, -1, 1};

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> k;
  board.resize(n, vector<Board_Info>(n));
  sharks.resize(m + 1);

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      int shark;
      cin >> shark;

      if (shark != 0) {
        board[i][j] = {{shark}, shark, k};  // 초기값}
        sharks[shark].x = i;
        sharks[shark].y = j;
        sharks[shark].exist = true;
      } else {
        board[i][j] = {{}, 0, 0};
      }
    }
  }

  // 각 상어의 초기 방향
  for (int i = 1; i <= m; i++) {
    // cout << "초기 방향\n";

    int dir;
    cin >> dir;
    sharks[i].dir = dir - 1;
  }

  // 상어의 방향 테이블
  for (int i = 1; i <= m; i++) {
    sharks[i].dir_table.resize(4);

    for (int j = 0; j < 4; j++) {
      for (int k = 0; k < 4; k++) {
        int dir;
        cin >> dir;
        sharks[i].dir_table[j].push_back(dir - 1);
      }
    }
  }
}

// 시간이 지나면 냄새가 줄어들어야 한다.
void decreaseScent() {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (board[i][j].smell_time > 0) {
        board[i][j].smell_time--;

        if (board[i][j].smell_time == 0) {
          board[i][j].smell_host = 0;
        }
      }
    }
  }
}

bool isValidRange(pair<int, int> p) {
  return 0 <= p.X && p.X < n && 0 <= p.Y && p.Y < n;
}

// condition에 맞는 인접한 칸을 찾아준다.
int findDirection(string condition, Shark current, int id) {
  int x = current.x;
  int y = current.y;

  for (auto d : current.dir_table[current.dir]) {
    pair<int, int> next = {x + DX[d], y + DY[d]};

    if (!isValidRange(next)) continue;

    if (condition == "EMPTY") {
      if (board[next.X][next.Y].smell_host == 0) {
        return d;
      }
    } else if (condition == "SAME") {
      if (board[next.X][next.Y].smell_host == id) {
        return d;
      }
    }
  }

  return -1;
}

// 상어를 움직임
void moveShark() {
  for (int id = 1; id <= m; id++) {
    if (!sharks[id].exist) continue;

    // 1. 인접한 칸 중 아무 냄새가 없는 칸의 방향 찾기
    int next_direction = findDirection("EMPTY", sharks[id], id);

    if (next_direction == -1) {
      // 2.  인접한 칸 중 자신의 냄새가 있는 칸의 방향 찾기
      next_direction = findDirection("SAME", sharks[id], id);
    }

    // 해당 칸으로 이동시키기
    int px = sharks[id].x;
    int py = sharks[id].y;

    int nx = px + DX[next_direction];
    int ny = py + DY[next_direction];

    board[nx][ny].sharks.push_back(id);
    board[px][py].sharks.erase(
        remove(board[px][py].sharks.begin(), board[px][py].sharks.end(), id),
        board[px][py].sharks.end());

    sharks[id].x = nx;
    sharks[id].y = ny;
    sharks[id].dir = next_direction;
  }
}

// 상어를 내쫓음
void kickOutShark() {
  // 모든 상어가 이동한 후 한 칸에 여러 마리가 남아 있으면,
  // 가장 작은 번호를 가진 상어만 남긴다.
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (board[i][j].sharks.size() != 0) {
        sort(board[i][j].sharks.begin(), board[i][j].sharks.end());

        for (int k = 1; k < board[i][j].sharks.size(); k++) {
          sharks[board[i][j].sharks[k]].exist = false;
        }

        board[i][j].sharks = {board[i][j].sharks[0]};
      }
    }
  }
}

// 냄새를 뿌림 : 현재 이동한 상어에 대해서 냄새를 뿌린다.
void spreadScent() {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      if (board[i][j].sharks.size() != 0) {
        int host = board[i][j].sharks[0];
        board[i][j].smell_host = host;
        board[i][j].smell_time = k;
      }
    }
  }
}

bool checkEnd() {
  for (int i = 2; i <= m; i++) {
    if (sharks[i].exist) return false;
  }

  return sharks[1].exist;
}

int solve() {
  int time = 0;

  while (time <= 1000) {
    time++;

    moveShark();
    kickOutShark();
    decreaseScent();
    spreadScent();

    if (checkEnd()) break;
  }

  if (time > 1000) {
    return -1;
  } else {
    return time;
  }
}

int main() {
  input();
  cout << solve();
  return 0;
}
