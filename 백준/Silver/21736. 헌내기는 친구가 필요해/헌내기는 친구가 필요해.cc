#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

int n, m;
vector<vector<char>> campus;
pair<int, int> target;

const int DX[4] = {1, 0, -1, 0};
const int DY[4] = {0, 1, 0, -1};

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  campus.resize(n, vector<char>(m));

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> campus[i][j];
      if (campus[i][j] == 'I') {
        target = {i, j};
      }
    }
  }
}

bool isValidRange(pair<int, int> p) {
  return 0 <= p.X && p.X < n && 0 <= p.Y && p.Y < m;
}

int bfs() {
  int count = 0;

  queue<pair<int, int>> q;
  vector<vector<bool>> visited(n, vector<bool>(m, false));

  q.push(target);
  visited[target.X][target.Y] = true;

  while (!q.empty()) {
    pair<int, int> current = q.front();
    q.pop();

    for (int dir = 0; dir < 4; dir++) {
      pair<int, int> next = {current.X + DX[dir], current.Y + DY[dir]};

      if (!isValidRange(next)) continue;
      if (visited[next.X][next.Y]) continue;
      if (campus[next.X][next.Y] == 'X') continue;

      if (campus[next.X][next.Y] == 'P') count++;
      q.push(next);
      visited[next.X][next.Y] = true;
    }
  }

  return count;
}

int main() {
  input();

  int answer = bfs();

  if (answer == 0) {
    cout << "TT";
  } else {
    cout << answer;
  }

  return 0;
}
