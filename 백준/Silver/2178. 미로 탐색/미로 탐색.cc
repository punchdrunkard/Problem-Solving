// 미로찾기
// 지나야 하는 최소의 칸 수를 출력한다. -> bfs 를 이용하여 거리를 측정하자.
#include <bits/stdc++.h>

#define MAX 101
#define X first
#define Y second

using namespace std;

char graph[MAX][MAX];
int N, M;

bool visited[MAX][MAX];
int dx[4] = {-1, 1, 0, 0};
int dy[4] = {0, 0, -1, 1};

int bfs(int x, int y) {
  queue<pair<pair<int, int>, int>> q;  // {x, y}, distance
  q.push({{0, 0}, 1});

  while (!q.empty()) {
    auto current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      int nextX = current.first.X + dx[dir];
      int nextY = current.first.Y + dy[dir];

      if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
      if (visited[nextX][nextY] || graph[nextX][nextY] != '1') continue;

      q.push({{nextX, nextY}, current.second + 1});
      visited[nextX][nextY] = true;
    }

    if (current.first.X == N - 1 && current.first.Y == M - 1) {
      return current.second;
    }
  }

  return -1;
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> N >> M;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < M; j++) {
      cin >> graph[i][j];
    }
  }

  cout << bfs(0, 0);
  return 0;
}
