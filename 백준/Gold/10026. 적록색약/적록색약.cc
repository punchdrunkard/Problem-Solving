// 구역을 찾기 - 일정 색상으로 연결된 요소를 찾는다. -> bfs 로 찾을 수 있다.
// 적록색맹인 경우와 적록색맹이 아닌 경우를 나누어서 bfs 를 돌린다.

#include <bits/stdc++.h>

#define MAX 101
#define X first
#define Y second

using namespace std;

int N;
char graph[MAX][MAX];
bool visited[MAX][MAX];

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};

void bfs(int x, int y, bool isBlind) {
  queue<pair<int, int>> q;
  q.push({x, y});

  while (!q.empty()) {
    auto current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      int nextX = current.X + dx[dir];
      int nextY = current.Y + dy[dir];

      if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= N) continue;

      char nextColor = graph[nextX][nextY];
      char currentColor = graph[current.X][current.Y];

      if (isBlind) {
        if (nextColor != currentColor) {
          if (!(currentColor == 'R' && nextColor == 'G') &&
              !(currentColor == 'G' && nextColor == 'R')) {
            continue;
          }
        }

        if (visited[nextX][nextY]) continue;

      } else {
        if (nextColor != currentColor || visited[nextX][nextY]) continue;
      }

      q.push({nextX, nextY});
      visited[nextX][nextY] = true;
    }
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> N;

  int realCount = 0;
  int blindCount = 0;

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      char color;
      cin >> color;
      graph[i][j] = color;
    }
  }

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (!visited[i][j]) {
        // bfs 를 돌린다.
        bfs(i, j, false);
        realCount++;
      }
    }
  }

  memset(visited, false, sizeof(visited));

  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (!visited[i][j]) {
        // bfs 를 돌린다.
        bfs(i, j, true);
        blindCount++;
      }
    }
  }

  cout << realCount << " " << blindCount;

  return 0;
}