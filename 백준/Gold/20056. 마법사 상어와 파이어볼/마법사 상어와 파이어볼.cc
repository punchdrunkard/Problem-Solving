#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

const array<int, 8> DX = {-1, -1, 0, 1, 1, 1, 0, -1};
const array<int, 8> DY = {0, 1, 1, 1, 0, -1, -1, -1};

struct Fireball {
  int x;
  int y;
  int m;  // 질량
  int s;  // 속도
  int d;  // 방향
};

int n, m, k;  // 칸의 크기, 파이어볼 갯수, 명령 갯수
vector<vector<vector<Fireball>>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> k;
  board.resize(n + 1, vector<vector<Fireball>>(n + 1));  // 1-indexed

  // 파이어볼 입력
  for (int i = 0; i < m; i++) {
    int x, y, m, s, d;
    cin >> x >> y >> m >> s >> d;
    board[x][y].push_back({x, y, m, s, d});
  }
}

int getBoardIndex(int i) {
  i = (i % n + n) % n; // 음수 처리
  if (i == 0) return n;

  return i;
}

// 각 파이어볼의 방향을 정하기 위함
bool isAllOddOrEven(vector<int> &dirs) {
  int remainder = dirs[0] % 2;

  for (int dir : dirs) {
    if (dir % 2 != remainder) return false;
  }

  return true;
}

void moveFireball() {
  vector<vector<vector<Fireball>>> result(n + 1,
                                          vector<vector<Fireball>>(n + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      // board[i][j] 에 있는 각 fireball에 대하여
      for (auto fireball : board[i][j]) {
        int next_x = getBoardIndex(fireball.x + fireball.s * DX[fireball.d]);
        int next_y = getBoardIndex(fireball.y + fireball.s * DY[fireball.d]);

        result[next_x][next_y].push_back(
            {next_x, next_y, fireball.m, fireball.s, fireball.d});
      }
    }
  }

  board = result;
}

// (i, j)의 칸에 있는 파이어볼을 나눔
void separateFireball(int i, int j) {
  Fireball f1, f2, f3, f4;  // 4개의 파이어볼로 나누어 지므로

  int total_mass = 0;
  int total_speed = 0;
  int fireball_count = board[i][j].size();
  vector<int> dirs;
  vector<Fireball> temp;

  for (auto fireball : board[i][j]) {
    total_mass += fireball.m;
    total_speed += fireball.s;
    dirs.push_back(fireball.d);
  }

  int mass = floor(total_mass / 5);
  int speed = floor(total_speed / fireball_count);

  // 질량이 0인 파이어볼은 소멸되어 없어진다.
  if (mass == 0) {
    board[i][j].clear();
    return;
  }

  // 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 방향은 0, 2, 4, 6
  if (isAllOddOrEven(dirs)) {
    for (int dir = 0; dir <= 6; dir += 2) {
      temp.push_back({i, j, mass, speed, dir});
    }
  } else {  // 아니면 1, 3, 5, 7
    for (int dir = 1; dir <= 7; dir += 2) {
      temp.push_back({i, j, mass, speed, dir});
    }
  }

  board[i][j] = temp;
}

int findAnswer() {
  int answer = 0;

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      for (auto fireball : board[i][j]) {
        answer += fireball.m;
      }
    }
  }

  return answer;
}

int solve() {
  for (int cmd = 0; cmd < k; cmd++) {
    moveFireball();

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        if (board[i][j].size() >= 2) {  // 2개 이상의 파이어볼이 있는 칸
          separateFireball(i, j);
        }
      }
    }
  }

  return findAnswer();
}

int main() {
  input();
  cout << solve();

  return 0;
}
