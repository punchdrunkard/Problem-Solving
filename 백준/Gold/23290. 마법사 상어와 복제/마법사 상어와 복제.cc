#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

struct BoardInfo {
  vector<int> fishes;
  int scent_time;
};

struct Shark {
  int x;
  int y;
};

int m, s;     // 물고기의 수, 마법 연습의 수
Shark shark;  // 현재 상어의 상태
vector<vector<BoardInfo>> board(5, vector<BoardInfo>(5));  // 1-indexed

// 물고기의 이동 방향
int DX[9] = {0, 0, -1, -1, -1, 0, 1, 1, 1};
int DY[9] = {0, -1, -1, 0, 1, 1, 1, 0, -1};

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);
  cin >> m >> s;

  // 물고기의 정보
  for (int i = 0; i < m; i++) {
    int x, y, d;
    cin >> x >> y >> d;
    board[x][y].fishes.push_back(d);
  }

  // 상어의 정보
  cin >> shark.x >> shark.y;
}

bool isValidRange(int x, int y) { return 1 <= x && x <= 4 && 1 <= y && y <= 4; }

bool canMove(int x, int y) {
  return isValidRange(x, y) && !(shark.x == x && shark.y == y) &&
         board[x][y].scent_time == 0;
}

void moveFish() {
  // 이동 결과 저장하는 임시 배열
  vector<vector<BoardInfo>> temp(5, vector<BoardInfo>(5));

  for (int i = 1; i <= 4; i++) {
    for (int j = 1; j <= 4; j++) {
      // 냄새 정보 유지
      temp[i][j].scent_time = board[i][j].scent_time;

      // 현재 칸에 있는 물고기들에 대해서
      for (auto fish_dir : board[i][j].fishes) {
        bool isMove = false;

        for (int dir = fish_dir; dir > fish_dir - 8; dir--) {
          int current_dir = dir <= 0 ? (dir % 8) + 8 : dir;
          int next_x = i + DX[current_dir], next_y = j + DY[current_dir];

          if (canMove(next_x, next_y)) {
            temp[next_x][next_y].fishes.push_back(current_dir);
            isMove = true;
            break;
          }
        }

        // 이동할 수 없으면 이동하지 않는다.
        if (!isMove) {
          temp[i][j].fishes.push_back(fish_dir);
        }
      }
    }
  }

  board = temp;
}

void printBoard() {
  for (int i = 1; i <= 4; i++) {
    for (int j = 1; j <= 4; j++) {
      if (board[i][j].fishes.empty())
        cout << "빔 ";
      else
        cout << "( ";

      for (auto fish : board[i][j].fishes) {
        cout << fish << ' ';
      }

      if (!board[i][j].fishes.empty()) cout << ") ";
    }

    cout << '\n';
  }
}

// 상어의 이동 방향 - 상 : 1,  좌 : 2, 하 : 3, 우 : 4
vector<vector<int>> shark_sequences;
int S_DX[5] = {0, -1, 0, 1, 0};
int S_DY[5] = {0, 0, -1, 0, 1};

void makeSharkSequence(vector<int> sequence) {
  if (sequence.size() == 3) {
    shark_sequences.push_back(sequence);
    return;
  }

  for (int i = 1; i <= 4; i++) {
    sequence.push_back(i);
    makeSharkSequence(sequence);
    sequence.pop_back();
  }
};

vector<int> defineSharkMove() {
  int max_count = -1;
  vector<int> move;

  for (auto sequence : shark_sequences) {
    int current_count = 0;  // 현재 물고기를 잡아먹을 수 있는 양
    bool isMove = true;
    vector<vector<bool>> visited(5, vector<bool>(5, false));

    int current_x = shark.x;
    int current_y = shark.y;

    for (auto dir : sequence) {
      int next_x = current_x + S_DX[dir];
      int next_y = current_y + S_DY[dir];

      if (!isValidRange(next_x, next_y)) {
        isMove = false;
        break;
      }

      // 물고기가 중복될 수도 있으므로
      if (!visited[next_x][next_y]) {
        current_count += board[next_x][next_y].fishes.size();
      }

      current_x = next_x;
      current_y = next_y;
      visited[current_x][current_y] = true;
    }

    if (!isMove) continue;  // 불가능한 이동 방법

    // 상어 방향 업데이트 기준 (이미 사전순 정렬되있어서 업데이트하면 된다)
    if (current_count > max_count) {
      move = sequence;
      max_count = current_count;
    }
  }

  return move;
}

void moveShark() {
  vector<int> shark_move = defineSharkMove();

  for (auto shark_dir : shark_move) {
    int next_x = shark.x + S_DX[shark_dir];
    int next_y = shark.y + S_DY[shark_dir];

    // 연속해서 이동하는 중, 상어가 물고기가 있는 칸으로 이동한다면
    if (board[next_x][next_y].fishes.size() > 0) {
      // 그 칸에 있는 모든 물고기는 격자에서 제외
      board[next_x][next_y].fishes.clear();
      // 물고기 냄새 (이 후, 냄새가 사라지는 로직이 있으므로 3으로 처리)
      board[next_x][next_y].scent_time = 3;
    }

    // 상어의 좌표 업데이트
    shark.x = next_x;
    shark.y = next_y;
  }
}

void decreaseScent() {
  for (int i = 1; i <= 4; i++) {
    for (int j = 1; j <= 4; j++) {
      if (board[i][j].scent_time > 0) {
        board[i][j].scent_time--;
      }
    }
  }
}

void copyFish(vector<vector<BoardInfo>> &initialBoard) {
  for (int i = 1; i <= 4; i++) {
    for (int j = 1; j <= 4; j++) {
      for (auto fish : initialBoard[i][j].fishes) {
        board[i][j].fishes.push_back(fish);
      }
    }
  }
}

int countFish() {
  int count = 0;

  for (int i = 1; i <= 4; i++) {
    for (int j = 1; j <= 4; j++) {
      count += board[i][j].fishes.size();
    }
  }

  return count;
}

void solve() {
  makeSharkSequence({});

  for (int magic_count = 0; magic_count < s; magic_count++) {
    vector<vector<BoardInfo>> initialBoard = board;
    moveFish();
    moveShark();
    decreaseScent();
    copyFish(initialBoard);
  }

  int count = countFish();
  cout << count;
}

int main() {
  input();
  solve();

  return 0;
}
