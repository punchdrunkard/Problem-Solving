// bfs 를 이용해서 connected component 를 찾기
#include <bits/stdc++.h>

#define MAX 51
#define X first
#define Y second

using namespace std;

int graph[MAX][MAX];
int visited[MAX][MAX];

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};
int M, N, K;

void bfs(int x, int y) {
  queue<pair<int, int>> q;
  q.push({x, y});

  while (!q.empty()) {
    auto current = q.front();
    q.pop();

    for (int i = 0; i < 4; i++) {
      pair<int, int> next = {current.X + dx[i], current.Y + dy[i]};

      if (next.X < 0 || next.X >= N || next.Y < 0 || next.Y >= M) continue;
      if (visited[next.X][next.Y] || graph[next.X][next.Y] != 1) continue;

      q.push(next);
      visited[next.X][next.Y] = 1;
    }
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int T;
  cin >> T;

  while (T--) {
    int count = 0;
    cin >> M >> N >> K;

    while (K--) {
      int x, y;
      cin >> x >> y;
      graph[y][x] = 1;
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (graph[i][j] == 1 && !visited[i][j]) {
          bfs(i, j);
          count++;
        }
      }
    }

    cout << count << '\n';

    // 테스트 케이스가 끝난 후 그래프 초기화
    memset(graph, 0, sizeof(graph));
    memset(visited, 0, sizeof(visited));
  }

  return 0;
}