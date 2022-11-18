// 불
// 불의 입장에서 BFS, 상근이의 입장에서 BFS를 하여 시간 (거리)를 측정한 후,
// 시간을 비교하여 탈출 여부를 구한다.

// 반례 : https://www.acmicpc.net/board/view/37724

#include <bits/stdc++.h>

#define MAX 1000
#define X first
#define Y second

using namespace std;

char graph[MAX][MAX];
int distF[MAX][MAX];
int distS[MAX][MAX];

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int t;
  cin >> t;

  while (t--) {
    // 그래프 초기화
    memset(distF, -1, sizeof(distF));
    memset(distS, -1, sizeof(distS));

    int w, h;
    cin >> w >> h;

    bool isEscape = false;

    queue<pair<int, int>> fireQ;
    queue<pair<int, int>> sangQ;

    // 입력
    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        cin >> graph[i][j];

        if (graph[i][j] == '*') {
          fireQ.push({i, j});
          distF[i][j] = 0;
        }

        if (graph[i][j] == '@') {
          sangQ.push({i, j});
          distS[i][j] = 0;
        }
      }
    }

    // 불에 대한 bfs
    while (!fireQ.empty()) {
      auto currentF = fireQ.front();
      fireQ.pop();

      for (int dir = 0; dir < 4; dir++) {
        int nextX = currentF.X + dx[dir];
        int nextY = currentF.Y + dy[dir];

        if (nextX < 0 || nextX >= h || nextY < 0 || nextY >= w) continue;
        if (distF[nextX][nextY] != -1 || graph[nextX][nextY] == '#') continue;

        distF[nextX][nextY] = distF[currentF.X][currentF.Y] + 1;
        fireQ.push({nextX, nextY});
      }
    }

    // 상근이에 대한 bfs
    while (!sangQ.empty() && !isEscape) {
      auto currentS = sangQ.front();
      sangQ.pop();

      // 반례 : 시작지점이 답인 경우
      if (currentS.X == 0 || currentS.X == h - 1 || currentS.Y == 0 ||
          currentS.Y == w - 1) {
        isEscape = true;
        cout << "1\n";
        break;
      }

      for (int dir = 0; dir < 4; dir++) {
        int nextX = currentS.X + dx[dir];
        int nextY = currentS.Y + dy[dir];

        if (nextX < 0 || nextX >= h || nextY < 0 || nextY >= w) continue;

        if (distS[nextX][nextY] != -1 || graph[nextX][nextY] == '#') continue;

        // 불이 먼저 도달했을 경우
        if (distF[nextX][nextY] != -1 &&
            distF[nextX][nextY] <= distS[currentS.X][currentS.Y] + 1)
          continue;

        distS[nextX][nextY] = distS[currentS.X][currentS.Y] + 1;

        // 탈출 조건 - 가장자리에 있을 경우
        if (nextX == 0 || nextX == h - 1 || nextY == 0 || nextY == w - 1) {
          isEscape = true;
          cout << distS[nextX][nextY] + 1 << '\n';
          break;
        }
        sangQ.push({nextX, nextY});
      }
    }

    if (!isEscape) {
      cout << "IMPOSSIBLE\n";
    }
  }
  return 0;
}
