#include <bits/stdc++.h>
using namespace std;

#define MAX 501
#define X first
#define Y second

int n, m;

int board[MAX][MAX];
int visited[MAX][MAX];
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

int BFS(pair<int, int> startCoord) {
  int width = 1;

  queue<pair<int, int>> q;
  visited[startCoord.X][startCoord.Y] = 1;
  q.push(startCoord);

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    for (int i = 0; i < 4; i++) {
      int nextX = current.X + dx[i];
      int nextY = current.Y + dy[i];

      if (nextX < 1 || nextX > n || nextY < 1 || nextY > m) continue;

      if (!visited[nextX][nextY] && board[nextX][nextY]) {
        width++;
        visited[nextX][nextY] = 1;
        q.push({nextX, nextY});
      }
    }
  }
  return width;
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int count = 0;
  int maxWidth = 0;

  cin >> n;
  cin >> m;

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
      cin >> board[i][j];
    }
  }

  // 그래프 순회
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= m; j++) {
      // 방문하지 않았거나, 값이 1인 경우에 BFS
      if (!visited[i][j] && board[i][j]) {
        count++;
        int width = BFS({i, j});

        if (width > maxWidth) {
          maxWidth = width;
        }
      }
    }
  }

  cout << count << '\n' << maxWidth << '\n';
}
