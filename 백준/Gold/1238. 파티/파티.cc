#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

const int INF = 1e9;

using namespace std;

#define weight first
#define node second

void dijkstra(vector<int> &dist, int start,
              vector<vector<pair<int, int>>> &graph) {
  // 최소 힙 선언, {거리, vertex 번호}
  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      pq;

  // 시작점
  pq.push({0, start});
  dist[start] = 0;

  while (!pq.empty()) {
    auto current = pq.top();
    pq.pop();

    if (dist[current.node] != current.weight) {
      continue;
    }

    for (auto &next : graph[current.node]) {
      if (dist[current.node] + next.weight < dist[next.node]) {
        dist[next.node] = dist[current.node] + next.weight;
        pq.push({dist[next.node], next.node});
      }
    }
  }
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  // n : 마을(vertex) 갯수, m : edge 갯수, x : 기준점
  int n, m, x;
  cin >> n >> m >> x;

  vector<vector<pair<int, int>>> adj(n + 1), adjReverse(n + 1);

  for (int i = 0; i < m; i++) {
    int start, end, cost;
    cin >> start >> end >> cost;
    adj[start].push_back({cost, end});
    adjReverse[end].push_back({cost, start});
  }

  // X에서 어떤 점으로 가는 최단 거리
  vector<int> distToV(n + 1, INF);
  dijkstra(distToV, x, adj);

  // 어떤 점에서 X로 가는 최단 거리 (역방향 다익스트라)
  vector<int> distToX(n + 1, INF);
  dijkstra(distToX, x, adjReverse);

  int answer = -1;

  for (int i = 1; i <= n; i++) {
    int dist = distToV[i] + distToX[i];
    answer = max(dist, answer);
  }

  cout << answer;

  return 0;
}
