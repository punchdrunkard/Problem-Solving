#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

const int SHARK = -1;
const int EMPTY = 0;

// 물고기의 정보
struct Fish {
  int x;
  int y;
  int dir;
  bool is_alive;
};

vector<vector<int>> fish_map(4, vector<int>(4));  // 현재 상태 저장을 위한 맵
vector<Fish> fish_info(17);

// 8방향, 0-indexed
vector<int> DX = {-1, -1, 0, 1, 1, 1, 0, -1};
vector<int> DY = {0, -1, -1, -1, 0, 1, 1, 1};

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  for (int i = 0; i < 4; i++) {
    for (int j = 0; j < 4; j++) {
      int id, dir;
      cin >> id >> dir;
      fish_map[i][j] = id;
      fish_info[id] = {i, j, dir - 1, true};
    }
  }
}

bool isValidRange(int x, int y) { return 0 <= x && x < 4 && 0 <= y && y < 4; }

int answer = 0;

void moveFish() {
  for (int i = 1; i <= 16; i++) {
    if (!fish_info[i].is_alive) continue;

    int dir = fish_info[i].dir;
    int cur_x = fish_info[i].x;
    int cur_y = fish_info[i].y;

    for (int d = dir; d < dir + 8; d++) {
      int next_dir = d >= 8 ? d % 8 : d;

      int next_x = cur_x + DX[next_dir];
      int next_y = cur_y + DY[next_dir];

      if (isValidRange(next_x, next_y) && fish_map[next_x][next_y] != -1) {
        int next_fish_id = fish_map[next_x][next_y];

        swap(fish_map[cur_x][cur_y], fish_map[next_x][next_y]);

        fish_info[i].x = next_x;
        fish_info[i].y = next_y;

        fish_info[i].dir = next_dir;

        if (next_fish_id != EMPTY) {
          fish_info[next_fish_id].x = cur_x;
          fish_info[next_fish_id].y = cur_y;
        }

        break;
      }
    }
  }
}

// 상어의 위치, 방향, 현재까지 먹은 물고기 번호 수의 합
void dfs(int x, int y, int dir, int sum) {
  answer = max(answer, sum);

  // backtracking을 위한 이전 상태 저장
  vector<vector<int>> current_map = fish_map;
  vector<Fish> current_fish_info = fish_info;

  moveFish();

  // 상어가 이동한다.
  // 맵이 4 * 4라서 최대 4번 밖에 못 움직인다.
  for (int i = 1; i < 4; i++) {
    int nx = x + DX[dir] * i;
    int ny = y + DY[dir] * i;

    if (isValidRange(nx, ny)) {
      if (fish_map[nx][ny] == 0) continue;

      int current_fish_id = fish_map[nx][ny];
      Fish current_fish = fish_info[current_fish_id];

      // 상어가 물고기를 잡아먹는다.
      fish_map[x][y] = 0;
      fish_map[nx][ny] = -1;
      fish_info[current_fish_id].is_alive = false;

      dfs(nx, ny, current_fish.dir, sum + current_fish_id);

      // 상태 복원
      fish_map[x][y] = -1;
      fish_map[nx][ny] = current_fish_id;
      fish_info[current_fish_id].is_alive = true;
    }
  }

  // 상태 복원
  fish_map = current_map;
  fish_info = current_fish_info;
}

int solve() {
  // 어는 (0, 0)에 있는 물고기를 먹고, (0, 0)에 들어가게 된다.
  int victim = fish_map[0][0];
  fish_info[victim].is_alive = false;
  fish_map[0][0] = -1;

  dfs(0, 0, fish_info[victim].dir, victim);

  return answer;
}

int main() {
  input();
  cout << solve();

  return 0;
}
