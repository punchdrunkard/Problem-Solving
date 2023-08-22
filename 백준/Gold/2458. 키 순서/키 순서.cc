#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define MAX 501

using namespace std;

// 자신이 몇 번째이기 알기 위해서는
// 해당 정점으로 가는 경로와
// 해당 점점에서 다른 정점으로 가는 경로가 존재해야 한다.

// 따라서 먼저 플로이드 워셜을 통하여 모든 정점에 대한 최단 경로를 구한다음
// 경로가 존재하는지에 따라서 답을 구해보자.

int n, m;
int dist[MAX][MAX];  // 인접 행렬
const int INF = 1e9;

void initGraph() {
  // init
  for (int i = 0; i <= n; i++) {
    for (int j = 0; j <= n; j++) {
      dist[i][j] = i == j ? 0 : INF;
    }
  }
}

void floyd() {
  for (int k = 1; k <= n; k++) {
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
      }
    }
  }
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);
  cin >> n >> m;

  initGraph();

  for (int i = 0; i < m; i++) {
    int a, b;
    cin >> a >> b;
    dist[a][b] = 1;
  }

  // floyd-warshall
  floyd();

  int count = 0;

  for (int v = 1; v <= n; v++) {
    bool flag = true;  // 키 순서를 알수 있으면 true
    for (int i = 1; i <= n; i++) {
      if (!(dist[i][v] != INF || dist[v][i] != INF)) {
        flag = false;
        break;
      }
    }

    if (flag) count++;
  }

  cout << count << '\n';

  return 0;
}
