#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX 16
#define INF 1e9

using namespace std;

int n;
int w[MAX][MAX], dp[MAX][1 << MAX];

void input() {
  FASTIO;
  cin >> n;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      cin >> w[i][j];
    }
  }
}

int tsp(int current, int visited) {
  if (dp[current][visited] != -1) {  // 이미 계산된 경우
    return dp[current][visited];
  }

  // base case : 모든 마을을 방문함.
  if (visited == (1 << n) - 1) {
    return w[current][0] ? w[current][0] : INF;
  }

  // 다른 마을 방문하기
  dp[current][visited] = INF;

  for (int i = 0; i < n; i++) {
    // 이미 방문했거나, 방문할 수 없는 경우
    if ((visited & (1 << i)) != 0 || w[current][i] == 0) {
      continue;
    }

    dp[current][visited] =
        min(dp[current][visited], tsp(i, visited | (1 << i)) + w[current][i]);
  }

  return dp[current][visited];
}

int main() {
  input();
  memset(dp, -1, sizeof(dp));
  cout << tsp(0, (1 << 0));  // 0번 마을부터 방문

  return 0;
}
