#include <bits/stdc++.h>

#define MAX 101
#define INF 1e9

using namespace std;

int dist[MAX][MAX];

void initDist(int n) {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      i == j ? dist[i][j] = 0 : dist[i][j] = INF;
    }
  }
}

void print(int n) {
  for (int i = 1; i <= n; i++) {
    for (int j = 1; j <= n; j++) {
      if (dist[i][j] == INF) {
        cout << 0 << ' ';
      } else {
        cout << dist[i][j] << ' ';
      }
    }
    cout << '\n';
  }
}

void floyd(int n, int m) {
  for (int k = 1; k <= n; k++) {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
      }
    }
  }
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  int n, m;
  cin >> n >> m;

  initDist(n);

  for (int i = 0; i < m; i++) {
    int a, b, c;
    cin >> a >> b >> c;

    if (dist[a][b] != INF) {
      dist[a][b] = min(dist[a][b], c);
    } else {
      dist[a][b] = c;
    }
  }

  floyd(n, m);
  print(n);

  return 0;
}
