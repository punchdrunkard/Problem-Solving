#include <bits/stdc++.h>

#define MAX 1000
#define X 0
#define Y 1

using namespace std;

char graph[MAX][MAX];
int dist[MAX][MAX][2];
int n, m;

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};

int bfs() {
  queue<tuple<int, int, bool>> q;
  q.push({0, 0, 0});

  while (!q.empty()) {
    auto current = q.front();
    int currentX = get<X>(current);
    int currentY = get<Y>(current);
    int isBreakWall = get<2>(current);

    q.pop();

    for (int i = 0; i < 4; i++) {
      int nextX = currentX + dx[i];
      int nextY = currentY + dy[i];

      if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
      if (dist[nextX][nextY][isBreakWall] >= 0) continue;

      if (graph[nextX][nextY] == '1') {
        if (isBreakWall) {
          continue;
        } else {
          dist[nextX][nextY][1] = dist[currentX][currentY][isBreakWall] + 1;
          q.push({nextX, nextY, 1});
        }
      } else {
        dist[nextX][nextY][isBreakWall] =
            dist[currentX][currentY][isBreakWall] + 1;
        q.push({nextX, nextY, isBreakWall});
      }
    }
  }

  if (dist[n - 1][m - 1][0] == -1) {
    return dist[n - 1][m - 1][1];
  } else if (dist[n - 1][m - 1][1] == -1) {
    return dist[n - 1][m - 1][0];
  } else {
    return min(dist[n - 1][m - 1][0], dist[n - 1][m - 1][1]);
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> m;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> graph[i][j];
    }
  }
  memset(dist, -1, sizeof(dist));
  dist[0][0][0] = 1;

  cout << bfs();

  return 0;
}
