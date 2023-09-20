#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define X first
#define Y second

using namespace std;

int n;
vector<vector<int>> board;

const int SHARK = 9;
const int EMPTY = 0;

vector<int> DX = {-1, 0, 0, 1};
vector<int> DY = {0, -1, 1, 0};

struct Shark {
  int x;
  int y;
  int size;   // 현재 상어의 크기ㄴ
  int count;  // 잡아먹은 물고기 수
};

Shark shark;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  board.resize(n, vector<int>(n));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> board[i][j];

      if (board[i][j] == SHARK) {
        shark = {i, j, 2, 0};
      }
    }
  }
}

bool compare(const pair<int, int> &a, const pair<int, int> &b) {
  if (a.X == b.X) return a.Y < b.Y;
  return a.X < b.X;
}

bool isValidRange(pair<int, int> p) {
  return 0 <= p.X && p.X < n && 0 <= p.Y && p.Y < n;
}

bool canEat(pair<int, int> fish) {
  return 1 <= board[fish.X][fish.Y] && board[fish.X][fish.Y] < shark.size;
}

// 현재 위치에서 가장 가까운 물고기의 좌표들을 리턴함.
int bfs(vector<pair<int, int>> &fishes) {
  queue<pair<int, int>> q;
  vector<vector<bool>> visited(n, vector<bool>(n, false));
  vector<vector<int>> dist(n, vector<int>(n, -1));

  q.push({shark.x, shark.y});
  visited[shark.x][shark.y] = true;
  dist[shark.x][shark.y] = 0;

  int minDist = INT_MAX;

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      pair<int, int> next = {current.X + DX[dir], current.Y + DY[dir]};

      if (!isValidRange(next)) continue;
      if (visited[next.X][next.Y]) continue;

      if (board[next.X][next.Y] == EMPTY ||
          board[next.X][next.Y] <= shark.size) {
        q.push({next.X, next.Y});
        visited[next.X][next.Y] = true;
        dist[next.X][next.Y] = dist[current.X][current.Y] + 1;

        if (canEat(next)) {
          if (minDist >= dist[next.X][next.Y]) {
            minDist = dist[next.X][next.Y];
            fishes.push_back(next);
          }
        }
      }
    }
  }

  fishes.erase(remove_if(fishes.begin(), fishes.end(),
                         [minDist, &dist](pair<int, int> &p) {
                           return dist[p.X][p.Y] != minDist;
                         }),
               fishes.end());

  return minDist == INT_MAX ? 0 : minDist;
}

int solve() {
  int ans = 0;

  while (true) {
    // 1. 현재 상어의 위치로 부터, 물고기들의 거리를 측정한다.
    vector<pair<int, int>> fishes;
    int count = bfs(fishes);

    if (fishes.empty()) {  // 더 이상 먹을 수 있는 물고기가 공간에 없는 경우
      break;
    }

    // 2. fishes 배열을 정렬한다. (가장 위에, 가장 왼쪽에)
    sort(fishes.begin(), fishes.end(), compare);

    // 0번째 물고기를 잡아먹고 이동
    board[shark.x][shark.y] = EMPTY;
    board[fishes[0].X][fishes[0].Y] = SHARK;
    shark.count++;

    if (shark.size == shark.count) {
      shark.size++;
      shark.count = 0;
    }

    shark.x = fishes[0].X;
    shark.y = fishes[0].Y;

    ans += count;
  }

  return ans;
}

int main() {
  input();

  cout << solve();

  return 0;
}
