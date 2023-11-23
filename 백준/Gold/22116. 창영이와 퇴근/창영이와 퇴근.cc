#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define INF 1000000001

using namespace std;

typedef long long ll;

int n;
vector<vector<ll>> board;

void input() {
  FASTIO;
  cin >> n;
  board.resize(n + 1, vector<ll>(n + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cin >> board[i][j];
    }
  }
}

bool isValidRange(int x, int y) { return 1 <= x && x <= n && 1 <= y && y <= n; }

struct Edge {
  int x;
  int y;
  ll cost;

  // 최소 힙
  bool operator<(const Edge &e) const { return cost > e.cost; }
};

int DX[4] = {-1, 1, 0, 0};
int DY[4] = {0, 0, -1, 1};

ll dijkstra() {
  priority_queue<Edge> pq;
  // dist[i][j] = {1, 1} ~ {i, j} 까지의 경로를 탐색할 때 만난 최대 경사
  vector<vector<ll>> dist(n + 1, vector<ll>(n + 1, INF));

  pq.push({1, 1, 0});
  dist[1][1] = 0;

  // 항상 최단거리 방향으로 탐색하기 위함
  while (!pq.empty()) {
    auto current = pq.top();
    pq.pop();

    int nowX = current.x;
    int nowY = current.y;
    ll nowCost = current.cost;

    if (nowCost > dist[nowX][nowY]) {
      continue;
    }

    if (nowX == n && nowY == n) {
      break;
    }

    for (int dir = 0; dir < 4; dir++) {
      int nextX = nowX + DX[dir];
      int nextY = nowY + DY[dir];

      if (!isValidRange(nextX, nextY)) continue;

      // 다음으로 가기 위한 최소 경사 기록
      ll heightDiff = abs(board[nowX][nowY] - board[nextX][nextY]);
      ll nextCost = max(nowCost, heightDiff);

      if (dist[nextX][nextY] > nextCost) {
        dist[nextX][nextY] = nextCost;
        pq.push({nextX, nextY, nextCost});
      }
    }
  }

  return dist[n][n];
}

int main() {
  input();
  cout << dijkstra();

  return 0;
}
