#include <bits/stdc++.h>
#define MAX 12

using namespace std;

int board[MAX][MAX];
int t, n, res, pnum;

enum DIR { RIGHT, DOWN, LEFT, UP };

bool isLine(int x, int y, int dir) {
  if (dir == RIGHT) {
    for (int i = y + 1; i < n; i++) {
      if (board[x][i] != 0) return false;
    }
  } else if (dir == DOWN) {
    for (int i = x + 1; i < n; i++) {
      if (board[i][y] != 0) return false;
    }
  } else if (dir == LEFT) {
    for (int i = 0; i < y; i++) {
      if (board[x][i] != 0) return false;
    }
  } else if (dir == UP) {
    for (int i = 0; i < x; i++) {
      if (board[i][y] != 0) return false;
    }
  }
  return true;
}

int drawLine(int x, int y, int dir, int flag) {
  int ans = 0;
  if (dir == RIGHT) {
    for (int i = y + 1; i < n; i++) {
      board[x][i] = (flag == 0) ? 2 : 0;
      ans++;
    }
  } else if (dir == DOWN) {
    for (int i = x + 1; i < n; i++) {
      board[i][y] = (flag == 0) ? 2 : 0;
      ans++;
    }
  } else if (dir == LEFT) {
    for (int i = 0; i < y; i++) {
      board[x][i] = (flag == 0) ? 2 : 0;
      ans++;
    }
  } else if (dir == UP) {
    for (int i = 0; i < x; i++) {
      board[i][y] = (flag == 0) ? 2 : 0;
      ans++;
    }
  }
  return ans;
}

void dfs(vector<pair<int, int>> &v, int idx, int num, int line) {
  if (idx == v.size()) {
    if (pnum < num) {
      res = line;
      pnum = num;
    } else if (pnum == num) {
      if (res > line) res = line;
    }
  } else {
    for (int i = 0; i < 4; i++) {
      if (isLine(v[idx].first, v[idx].second, i)) {
        dfs(v, idx + 1, num + 1,
            line + drawLine(v[idx].first, v[idx].second, i, 0));
        drawLine(v[idx].first, v[idx].second, i, 1);
      }
    }
    dfs(v, idx + 1, num, line);
  }
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
 //  freopen("sample_input.txt", "r", stdin);
  cin >> t;

  for (int tc = 1; tc <= t; tc++) {
    vector<pair<int, int>> v;
    cin >> n;

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        cin >> board[i][j];

        if (i == 0 || j == 0 || i == n - 1 || j == n - 1) continue;

        if (board[i][j] == 1) {
          v.push_back({i, j});
        }
      }
    }

    res = INT_MAX;
    pnum = 0;

    dfs(v, 0, 0, 0);

    cout << "#" << tc << " " << res << '\n';
  }
}
