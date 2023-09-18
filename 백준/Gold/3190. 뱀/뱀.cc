#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define X first
#define Y second

using namespace std;

// 보드의 상태를 나타내기 위한 변수
const int APPLE = 1;
const int SNAKE = -1;
const int EMPTY = 0;

int n, k, l;  // 보드의 크기, 사과의 개수, 뱀의 방향 변환 횟수
vector<vector<int>> board;
vector<pair<int, string>> moves;

vector<pair<int, int>> DIRS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> k;
  board.resize(n + 1, vector<int>(n + 1, 0));

  for (int i = 0; i < k; i++) {
    int x, y;
    cin >> x >> y;
    board[x][y] = APPLE;
  }

  cin >> l;
  moves.resize(l);

  for (int i = 0; i < l; i++) {
    cin >> moves[i].first >> moves[i].second;
  }
}

bool isValidRange(pair<int, int> p) {
  return 1 <= p.X && p.X <= n && 1 <= p.Y && p.Y <= n;
}

void solve() {
  // 뱀의 위치 init
  board[1][1] = SNAKE;
  int dir = 0;  // 뱀의 이동방향 인덱스

  deque<pair<int, int>> dq;  // 뱀의 정보를 덱에 저장함
  dq.push_back({1, 1});

  int time = 0;  // 현재 시간
  int idx = 0;   // 현재 move 인덱스

  // 먼저 move 에 있는 시간만큼 움직여 줘야 한다.
  while (true) {
    pair<int, int> head = dq.back();

    // 시간에 따라 한 칸 간다.
    pair<int, int> next = {head.X + DIRS[dir].X, head.Y + DIRS[dir].Y};

    // 게임이 끝나는 경우
    if (!isValidRange(next)) {  // 범위를 벗어난 경우
      break;
    }

    if (board[next.X][next.Y] == SNAKE) {  // 자기 자신의 몸에 부딪히는 경우
      break;
    }

    if (board[next.X][next.Y] == EMPTY) {  // 사과가 없다면 꼬리를 제거
      pair<int, int> prev_tail = dq.front();
      dq.pop_front();
      board[prev_tail.X][prev_tail.Y] = EMPTY;
    }

    dq.push_back(next);
    board[next.X][next.Y] = SNAKE;
    time++;

    if (0 <= idx && idx < l) {
      if (time == moves[idx].first) {
        if (moves[idx].second == "L") {
          dir -= 1;
          if (dir < 0) dir += 4;
        } else {
          dir += 1;
          if (dir >= 4) dir -= 4;
        }

        idx++;
      }
    }
  }

  cout << time + 1;
}

int main() {
  input();
  solve();

  return 0;
}
