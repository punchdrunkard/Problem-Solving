#include <bits/stdc++.h>
using namespace std;

// 최대 25명이므로, 전체에서 가능한 7명을 뽑아서
// 그 7명이 문제의 조건에 맞는지 체크하는 문제이다.

// 배열은 메모리 구조 상, 2차원 배열 역시 1차원 배열로 저장되어있으므로
// 2차원 배열을 1차원 배열로 변환이 가능하다.

char board[5][5];
// 조합을 위한 배열
bool mask[25];

int dx[4] = {0, 0, -1, 1};
int dy[4] = {-1, 1, 0, 0};

void input() {
  for (int i = 0; i < 5; i++) {
    for (int j = 0; j < 5; j++) {
      cin >> board[i][j];
    }
  }
}

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);
  input();

  int answer = 0;
  fill(mask + 7, mask + 25, true);

  do {
    int dasomCount = 0;
    // 인접 갯수, 즉 현재 그래프의 요소 개수를 센다. 
    // bfs 로 방문하므로, 어짜피 연결되어 있다.
    int adj = 0;
    queue<pair<int, int>> q;
    bool visited[5][5] = {};
    bool choose[5][5] = {};

    for (int i = 0; i < 25; i++) {
      // 1차원 배열과 2차월 배열의 변환법
      if (!mask[i]) {
        int x = i / 5;
        int y = i % 5;
        choose[x][y] = true;

        if (q.empty()) {
          q.push({x, y});
          visited[x][y] = true;
        }
      }
    }

    while (!q.empty()) {
      auto [currentX, currentY] = q.front();
      q.pop();
      adj++;

      if (board[currentX][currentY] == 'S') {
        dasomCount += 1;
      }

      // 현재 점에서 인접한 학생들의 수를 센다.
      for (int i = 0; i < 4; i++) {
        int nextX = currentX + dx[i];
        int nextY = currentY + dy[i];

        if (nextX < 0 || nextX >= 5 || nextY < 0 || nextY >= 5) continue;
        if (visited[nextX][nextY]) continue;
        if (!choose[nextX][nextY]) continue;

        q.push({nextX, nextY});
        visited[nextX][nextY] = true;
      }
    }

    if (dasomCount >= 4 && adj == 7) answer += 1;
  } while (next_permutation(mask, mask + 25));

  cout << answer;
  return 0;
}
