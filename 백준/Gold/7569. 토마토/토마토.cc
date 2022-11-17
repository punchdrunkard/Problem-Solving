// 토마토

#include <bits/stdc++.h>

#define MAX 100

using namespace std;

int M, N, H;
int boxes[MAX][MAX][MAX];
int dist[MAX][MAX][MAX];

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};
int dz[2] = {1, -1};

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  memset(dist, -1, sizeof(dist));
  queue<tuple<int, int, int>> q;

  // input
  cin >> M >> N >> H;

  for (int i = 0; i < H; i++) {
    for (int j = 0; j < N; j++) {
      for (int k = 0; k < M; k++) {
        cin >> boxes[j][k][i];

        if (boxes[j][k][i] == 1) {
          q.push({j, k, i});
          dist[j][k][i] = 0;
        }
      }
    }
  }

  // bfs
  while (!q.empty()) {
    auto current = q.front();
    q.pop();

    int currentX = get<0>(current);
    int currentY = get<1>(current);
    int currentZ = get<2>(current);

    for (int dir = 0; dir < 4; dir++) {
      int nextX = currentX + dx[dir];
      int nextY = currentY + dy[dir];
      int nextZ = currentZ;

      if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
      if (dist[nextX][nextY][nextZ] != -1 || boxes[nextX][nextY][nextZ] == -1)
        continue;

      dist[nextX][nextY][nextZ] = dist[currentX][currentY][currentZ] + 1;
      q.push({nextX, nextY, nextZ});
    }

    for (int dir = 0; dir < 2; dir++) {
      int nextX = currentX;
      int nextY = currentY;
      int nextZ = currentZ + dz[dir];

      if (nextZ < 0 || nextZ >= H) continue;
      if (dist[nextX][nextY][nextZ] != -1 || boxes[nextX][nextY][nextZ] == -1)
        continue;

      dist[nextX][nextY][nextZ] = dist[currentX][currentY][currentZ] + 1;
      q.push({nextX, nextY, nextZ});
    }
  }

  // answer
  int answer = 0;

  for (int i = 0; i < H; i++) {
    for (int j = 0; j < N; j++) {
      for (int k = 0; k < M; k++) {
        if (dist[j][k][i] == -1 && boxes[j][k][i] != -1) {
          cout << -1;
          return 0;
        }

        if (answer <= dist[j][k][i]) {
          answer = dist[j][k][i];
        }
      }
    }
  }

  cout << answer;
  return 0;
}
