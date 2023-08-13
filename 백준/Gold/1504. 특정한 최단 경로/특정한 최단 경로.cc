#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define weight first
#define node second

using namespace std;

const int INF = 1e9;

// 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서
// 최단경로로 이동하는 프로그램을 작성

vector<vector<pair<int, int>>> adj;  // 가중치, 정점 번호

void dijkstra(vector<int> &dist, int start) {
  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      pq;

  dist[start] = 0;
  pq.push({dist[start], start});

  while (!pq.empty()) {
    auto cur = pq.top();
    pq.pop();

    // 현재 점이 유효한 점인가?
    if (dist[cur.node] != cur.weight) continue;

    for (const auto &next : adj[cur.node]) {
      if (next.weight + dist[cur.node] < dist[next.node]) {
        dist[next.node] = dist[cur.node] + next.weight;
        pq.push({dist[next.node], next.node});
      }
    }
  }
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int n, e;  // 정점의 갯수, 간선의 갯수
  cin >> n >> e;

  adj.resize(n + 1);

  for (int i = 0; i < e; i++) {
    int a, b, c;
    // a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c이다.
    cin >> a >> b >> c;
    adj[a].push_back({c, b});
    adj[b].push_back({c, a});
  }

  int v1, v2;
  cin >> v1 >> v2;

  // 모든 정점을 이동할 때 "반드시 최단경로로만" 이동하기 때문에,
  // 정점을 이동하는 방식 자체가 다익스트라 알고리즘과 같다.

  vector<int> dist_v1(n + 1, INF);
  dijkstra(dist_v1, v1);
  int v1_to_v2 = dist_v1[v2];
  int v1_to_n = dist_v1[n];

  vector<int> dist_start(n + 1, INF);
  dijkstra(dist_start, 1);
  int st_to_v1 = dist_start[v1];
  int st_to_v2 = dist_start[v2];

  vector<int> dist_v2(n + 1, INF);
  dijkstra(dist_v2, v2);
  int v2_to_n = dist_v2[n];

  int answer;

  if (v1_to_v2 == INF || v1_to_n == INF || st_to_v1 == INF || st_to_v2 == INF ||
      v2_to_n == INF) {
    answer = -1;
  } else {
    int route1 = st_to_v1 + v1_to_v2 + v2_to_n;
    int route2 = st_to_v2 + v1_to_v2 + v1_to_n;

    answer = min(route1, route2);
  }

  cout << answer << '\n';

  return 0;
}
