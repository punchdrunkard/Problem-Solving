#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;
vector<vector<int>> table, dp;

void input() {
  FASTIO;
  cin >> n >> m;
  table.resize(n + 1, vector<int>(n + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      cin >> table[i][j];
    }
  }
}

void makeDPTable() {
  dp.resize(n + 1, vector<int>(n + 1));

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (j == 1) {
        dp[i][j] = table[i][j];
      } else {
        dp[i][j] = dp[i][j - 1] + table[i][j];
      }
    }
  }
}

int query(int x1, int y1, int x2, int y2) {
  int sum = 0;

  for (int x = x1; x <= x2; x++) {
    sum += (dp[x][y2] - dp[x][y1 - 1]);
  }

  return sum;
}

int main() {
  input();
  makeDPTable();

  for (int i = 0; i < m; i++) {
    int x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;

    cout << query(x1, y1, x2, y2) << '\n';
  }

  return 0;
}
