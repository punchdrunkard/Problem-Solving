#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n;
vector<vector<char>> rooms;

// (0, 0) ~ (n - 1, n - 1) 로 갈 때
// 검은 방에서 흰 방으로 바꾸어야 할 최소의 수
void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n;
  rooms.resize(n, vector<char>(n));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> rooms[i][j];
    }
  }
}

struct State {
  int x;
  int y;
  int cost;  // rooms[x][y]로 가기 위한 비용

  bool operator<(const State &s) const { return cost > s.cost; }
};

bool isValidRange(int x, int y) { return 0 <= x && x < n && 0 <= y && y < n; }

int DX[4] = {-1, 1, 0, 0};
int DY[4] = {0, 0, -1, 1};

int dijkstra() {
  priority_queue<State> pq;
  // dist[i][j] = {0, 0} 에서 {i, j}로 가기 위해
  // 검은 방을 흰 방으로 바꾸는 최소의 갯수
  vector<vector<int>> dist(n, vector<int>(n, INT_MAX));

  pq.push({0, 0, 0});
  dist[0][0] = 0;

  while (!pq.empty()) {
    auto current = pq.top();
    pq.pop();

    int nowX = current.x;
    int nowY = current.y;
    int nowCost = current.cost;

    if (nowCost > dist[nowX][nowY]) {
      continue;
    }

    if (nowX == n - 1 && nowY == n - 1) {
      break;
    }

    for (int dir = 0; dir < 4; dir++) {
      int nextX = nowX + DX[dir];
      int nextY = nowY + DY[dir];

      if (!isValidRange(nextX, nextY)) {
        continue;
      }

      int nextCost = nowCost + (rooms[nextX][nextY] == '0' ? 1 : 0);

      if (dist[nextX][nextY] > nextCost) {
        dist[nextX][nextY] = nextCost;
        pq.push({nextX, nextY, nextCost});
      }
    }
  }

  return dist[n - 1][n - 1];
}

int main() {
  input();
  cout << dijkstra();

  return 0;
}
