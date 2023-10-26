#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

// 가중치가 없는 그래프에서 최단거리 = BFS

int n, m;
vector<vector<int>> board;
pair<int, int> target;  // 목표지점까지의 좌표
vector<vector<int>> dist;

const int INF = 1e6;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  board.resize(n, vector<int>(m));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> board[i][j];
      if (board[i][j] == 2) {
        target = {i, j};
      }
    }
  }
}

int DX[4] = {-1, 1, 0, 0};
int DY[4] = {0, 0, -1, 1};

bool isValidRange(int x, int y) { return 0 <= x && x < n && 0 <= y && y < m; }

vector<vector<int>> bfs() {
  vector<vector<int>> dist(n, vector<int>(m, INF));
  vector<vector<bool>> visited(n, vector<bool>(m, false));

  queue<pair<int, int>> q;
  q.push(target);
  visited[target.X][target.Y] = true;
  dist[target.X][target.Y] = 0;

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      pair<int, int> next = {current.X + DX[dir], current.Y + DY[dir]};

      if (!isValidRange(next.X, next.Y)) continue;
      if (board[next.X][next.Y] == 0) continue;
      if (visited[next.X][next.Y]) continue;

      q.push(next);
      visited[next.X][next.Y] = true;
      dist[next.X][next.Y] = dist[current.X][current.Y] + 1;
    }
  }

  return dist;
}

void printAnswer() {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cout << dist[i][j] << " ";
    }
    cout << "\n";
  }
}

void solve() {
  dist = bfs();

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      if (dist[i][j] == INF) {
        if (board[i][j] == 0) {
          dist[i][j] = 0;
        } else {
          dist[i][j] = -1;
        }
      }
    }
  }
}

int main() {
  input();
  solve();
  printAnswer();

  return 0;
}
