#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

int n;
vector<vector<int>> board;
int answer = 0;  // 격자 밖으로 나간 모래의 양

vector<int> DX = {0, 1, 0, -1};
vector<int> DY = {-1, 0, 1, 0};
const int ALPHA = -1;

// 모래가 퍼지는 방향 비율을 따로 정의
vector<vector<vector<int>>> RATIOS;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  board.resize(n, vector<int>(n));

  // 격자의 각 칸에 있는 모래
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> board[i][j];
    }
  }
}

// board를 90도 반시계 방향으로 회전시킨 배열을 리턴한다.
vector<vector<int>> rotateCCW(vector<vector<int>> board) {
  vector<vector<int>> temp(5, vector<int>(5));

  for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
      temp[5 - j - 1][i] = board[i][j];
    }
  }

  return temp;
}

void makeRatio() {
  vector<vector<int>> current = {{0, 0, 2, 0, 0},
                                 {0, 10, 7, 1, 0},
                                 {5, ALPHA, 0, 0, 0},
                                 {0, 10, 7, 1, 0},
                                 {0, 0, 2, 0, 0}};

  RATIOS.push_back(current);

  // 반시계 방향으로 90도씩 회전
  for (int i = 1; i < 4; i++) {
    current = rotateCCW(current);
    RATIOS.push_back(current);
  }
}

bool isEnd(pair<int, int> current) {
  // 종료 조건 : 소용돌이가 (0, 0)에 있을 때
  return current.X == 0 && current.Y == 0;
}

int changeDir(int dir) {
  dir++;
  if (dir >= 4) dir %= 4;
  return dir;
}

bool isValidRange(int x, int y) { return 0 <= x && x < n && 0 <= y && y < n; }

void spreadSand(pair<int, int> next, int dir) {
  // 소용돌이 중심 기준 떨어진 거리
  int dx = next.X - 2;
  int dy = next.Y - 2;

  int sand = board[next.X][next.Y];
  if (sand == 0) return;

  int alpha = sand;

  // 모든 모래가 움직이기 때문에 해당 부분을 0으로 설정
  board[next.X][next.Y] = 0;

  // 모래 퍼뜨리기
  for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
      if (RATIOS[dir][i][j] == ALPHA) continue;  // 알파 부분

      int nx = i + dx, ny = j + dy;

      double ratio = (double)RATIOS[dir][i][j] / 100.0;
      int current_sand = sand * ratio;
      alpha -= current_sand;

      // 모래가 밖으로 나가는 경우
      if (!isValidRange(nx, ny)) {
        answer += current_sand;
      } else {
        // 모래가 밖으로 나가지 않는 경우, 모래의 양이 더해진다.
        board[nx][ny] += current_sand;
      }
    }
  }

  // 알파 부분 처리
  int alpha_x = next.X + DX[dir], alpha_y = next.Y + DY[dir];

  if (!isValidRange(alpha_x, alpha_y)) {
    answer += alpha;
  } else {
    board[alpha_x][alpha_y] += alpha;
  }
}

int solve() {
  pair<int, int> current = {n / 2, n / 2};  // 소용돌이의 현재 좌표
  int dir = 0;
  int ds = 0;  // 얼마나 이동하나?

  while (true) {
    // 토네이도 이동
    if (dir % 2 == 0) ds++;

    // 더이상 ds가 증가할 수 없는 경우
    if (ds >= n - 1) ds = n - 1;
    pair<int, int> next = current;

    // 주의! : 토네이도는 한 번에 한 칸 이동한다.
    for (int i = 1; i <= ds; i++) {
      next = {current.X + DX[dir] * i, current.Y + DY[dir] * i};
      spreadSand(next, dir);
    }

    dir = changeDir(dir);
    current = next;

    // 종료 조건 : 마지막으로 한번 더 이동하고 종료한다.
    if (isEnd(current)) {
      spreadSand(current, dir);
      break;
    }
  }

  return answer;
}

int main() {
  input();
  makeRatio();

  cout << solve();

  return 0;
}
