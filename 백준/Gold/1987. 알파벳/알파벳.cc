#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int r, c;

int DX[4] = {-1, 1, 0, 0};
int DY[4] = {0, 0, -1, 1};

vector<vector<char>> board;

// 말은 상하좌우로 인접한 네 칸 중 한 칸으로 이동
// 같은 알파벳이 적힌 칸을 두 번 지날 수 없다. => 알파벳에 대한 방문 체크 필요
// 알파벳은 모두 26개이므로 배열로 해결 가능
vector<bool> visited;

void input() {
  FASTIO;
  cin >> r >> c;
  board.resize(r + 1, vector<char>(c + 1));
  visited.resize('Z' - 'A' + 1, false);

  for (int i = 1; i <= r; i++) {
    for (int j = 1; j <= c; j++) {
      cin >> board[i][j];
    }
  }
}

bool isValidRange(int x, int y) { return 1 <= x && x <= r && 1 <= y && y <= c; }

int answer = -1;

// 좌측 상단에서 부터 시작해서, 말이 최대한 몇 칸을 지날 수 있는가?
// (x, y)에 있는 말, 현재 count 개의 칸을 지남
// 꼭 도착을 하지 않아도 된다!
void solve(int x, int y, int count) {
  answer = max(answer, count);

  for (int dir = 0; dir < 4; dir++) {
    int nx = x + DX[dir];
    int ny = y + DY[dir];

    // 다음 칸으로 갈 수 있는가?
    if (!isValidRange(nx, ny)) continue;
    
    // 이미 방문한 알파벳인가?
    int next_alpha_idx = board[nx][ny] - 'A';
    if (visited[next_alpha_idx]) continue;
   
    visited[next_alpha_idx] = true;
    solve(nx, ny, count + 1);
    visited[next_alpha_idx] = false;
  }
}

int main() {
  input();

  visited[board[1][1] - 'A'] = true;
  solve(1, 1, 1);

  cout << answer;

  return 0;
}
