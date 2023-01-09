#include <bits/stdc++.h>
#define MAX 51
#define HOLE -1

using namespace std;

int n, m;
int graph[MAX][MAX];
int dp[MAX][MAX];
int visited[MAX][MAX];

int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, -1, 0, 1};

int dfs(int x, int y) {
  if (dp[x][y]) return dp[x][y];
  visited[x][y] = true;

  for (int i = 0; i < 4; i++) {
    int nx = x + dx[i] * graph[x][y];
    int ny = y + dy[i] * graph[x][y];

    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;  // 범위 체크
    if (graph[nx][ny] == HOLE) continue;                   // H인 경우 체크

    // 사이클을 찾았을 때  : 현재 dfs에서 방문한 점을 다시 방문하였을 경우
    if (visited[nx][ny]) return -2;
    int ret = dfs(nx, ny);
    if (ret == -2) return ret;
    dp[x][y] = max(dp[x][y], ret + 1);
  }

  visited[x][y] = false;
  return dp[x][y];
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> m;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      char entry;
      cin >> entry;
      if (entry == 'H') {
        graph[i][j] = HOLE;
      } else {
        graph[i][j] = int(entry - '0');
      }
    }
  }

  cout << dfs(0, 0) + 1;

  return 0;
}
