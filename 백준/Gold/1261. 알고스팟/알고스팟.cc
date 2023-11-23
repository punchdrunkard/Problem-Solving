#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;
vector<vector<char>> board;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> m >> n;
  board.resize(n + 1, vector<char>(m + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
      cin >> board[i][j];
    }
  }
}

struct Edge {
  int x;
  int y;
  int cost;

  // 최소 힙
  bool operator<(const Edge& a) const { return cost > a.cost; }
};

bool isValidRange(int x, int y) { return 1 <= x && x <= n && 1 <= y && y <= m; }

int DX[4] = {-1, 1, 0, 0};
int DY[4] = {0, 0, -1, 1};

int dijkstra() {
  priority_queue<Edge> pq;  // { 가중치, 현재 위치 }
  // dist[i][j] = { 1, 1 } => {i , j} 로 가는 벽을 부숴야하는 최소 갯수
  vector<vector<int>> dist(n + 1, vector<int>(m + 1, INT_MAX));

  pq.push({1, 1, 0});
  dist[1][1] = 0;

  while (!pq.empty()) {
    Edge current = pq.top();
    pq.pop();

    int nowX = current.x;
    int nowY = current.y;
    int nowCost = current.cost;

    if (nowCost > dist[nowX][nowY]) {
      continue;
    }

    // 도착한 경우
    if (nowX == n && nowY == m) {
      break;
    }

    for (int dir = 0; dir < 4; dir++) {
      int nextX = nowX + DX[dir];
      int nextY = nowY + DY[dir];

      if (!isValidRange(nextX, nextY)) continue;

      int nextCost = board[nextX][nextY] == '1' ? nowCost + 1 : nowCost;

      if (dist[nextX][nextY] > nextCost) {
        dist[nextX][nextY] = nextCost;
        pq.push({nextX, nextY, nextCost});
      }
    }
  }

  return dist[n][m];
}

int main() {
  input();
  cout << dijkstra();

  return 0;
}
