#include <bits/stdc++.h>
using namespace std;

#define MAX 401

int v, e, a, b, c;
int dp[MAX][MAX];

void initTable() {
  for (int i = 1; i <= v; i++) {
    for (int j = 1; j <= v; j++) {
      dp[i][j] = INT_MAX;
    }
  }
}

void input() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  cin >> v >> e;

  initTable();

  for (int i = 0; i < e; i++) {
    cin >> a >> b >> c;
    dp[a][b] = min(dp[a][b], c);
  }
}

int floyd() {
  int min_distance = INT_MAX;

  for (int i = 1; i <= v; i++) {
    for (int j = 1; j <= v; j++) {
      for (int k = 1; k <= v; k++) {
        if (dp[j][i] != INT_MAX && dp[i][k] != INT_MAX) {
          if (dp[j][i] + dp[i][k] < dp[j][k]) {
            dp[j][k] = dp[j][i] + dp[i][k];
          }
        }
      }
    }
  }

  for (int i = 1; i <= v; i++) {
    for (int j = 1; j <= v; j++) {
      if (i == j) {
        min_distance = min(min_distance, dp[i][j]);
      }
    }
  }

  return min_distance;
}

int main() {
  input();
  int answer = floyd();

  if (answer == INT_MAX) {
    cout << "-1";
  } else {
    cout << answer;
  }

  return 0;
}
