#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)
#define X first
#define Y second

using namespace std;

// 가중치가 있는 그래프(directed)에서의 최단경로 => 다익스트라
int n, m, a, b;
vector<vector<pair<int, int>>> adj;  // {가중치, 도착지}

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;
  adj.resize(n + 1);

  for (int i = 0; i < m; i++) {
    int st, en, cost;
    cin >> st >> en >> cost;
    adj[st].push_back({cost, en});
  }

  cin >> a >> b;
}

// st에서 en으로 가는 최단거리
int dijkstra(int st, int en) {
  // 초기화 : 우선 모든 정점의 최단거리를 무한대로 숫자를 정해둔다.
  vector<int> dist(n + 1, INT_MAX);

  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      pq;

  // 시작점을 큐에 넣고, dist를 0으로 설정한다.
  pq.push({0, st});
  dist[st] = 0;

  while (!pq.empty()) {
    auto current = pq.top();
    pq.pop();

    int nowEnd = current.Y;
    int nowCost = current.X;

    // 답을 찾은 경우
    if (nowEnd == en) {
      break;
    }

    // 이미 처리된 노드인 경우
    if (dist[nowEnd] < nowCost) {
      continue;
    }

    // 해당 정점에서 나가는 간선들을 전체 순회하며, 다음 갈 수 있는 정점들에
    // 대해 가중치를 계산
    for (auto& edge : adj[nowEnd]) {
      int nextNode = edge.second;           // 인접 노드
      int nextDist = nowCost + edge.first;  // 인접 노드까지의 거리

      if (dist[nextNode] > nextDist) {
        dist[nextNode] = nextDist;
        pq.push({nextDist, nextNode});
      };
    }
  }

  return dist[en];
}

int main() {
  input();
  auto dist = dijkstra(a, b);
  cout << dist;

  return 0;
}
