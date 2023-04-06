#include <bits/stdc++.h>
using namespace std;

#define N_MAX 101

int item[N_MAX];
int graph[N_MAX][N_MAX];
int dp[N_MAX][N_MAX];

int n, m, r;

const int INF = 0x3f3f3f3f;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  cin >> n >> m >> r;

  // 각 구역에 있는 아이템 수
  for (int i = 1; i <= n; i++) {
    cin >> item[i];
  }

  // 초기화
  for (int i = 1; i <= n; i++)
    for (int j = 1; j <= n; j++) {
      if (i != j) dp[i][j] = INF;
    }

  for (int i = 0; i < r; i++) {
    int a, b, l;
    cin >> a >> b >> l;
    dp[a][b] = l;
    dp[b][a] = l;
  }

  // Floyd-Warshall
  // 각 그래프의 정점 쌍에 대하여 최단 경로를 구한다.
  for (int k = 1; k <= n; k++) {  // 그래프의 vertex 번호 k에 대하여
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        // k 를 거치는 경우, 거치지 않는 경우
        dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j]);
      }
    }
  }

  int count[N_MAX];
  fill(count + 1, count + n + 1, 0);

  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (dp[i][j] <= m) {
        count[i] += item[j];
      }
    }
  }

  int answer = *max_element(count + 1, count + n + 1);
  cout << answer;

  return 0;
}
