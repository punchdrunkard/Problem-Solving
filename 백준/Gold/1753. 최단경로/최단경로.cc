#include <bits/stdc++.h>

#define X first
#define Y second

using namespace std;

// {비용, 정점 번호}
vector<pair<int, int>> adj[20005];
const int INF = 1e9 + 10;
int d[20005];  // 최단거리 테이블

void djik(int v, int st) {
  // 전체 테이블을 무한대로 초기화
  fill(d, d + v + 1, INF);

  // 최소 힙
  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      pq;

  // 시작점의 거리는 0이다.
  d[st] = 0;

  // 우선순위 큐에 시작점을 넣는다.
  pq.push({d[st], st});

  while (!pq.empty()) {
    auto cur = pq.top();
    pq.pop();

    // 우선순위 큐에 있는 점이 올바른 값인지 확인한다.
    if (d[cur.Y] != cur.X) continue;

    // 올바르다면 현재 정점에서 갈 수있는 거리와 테이블의 최단 거리를 비교한다.
    for (auto next : adj[cur.Y]) {
      if (d[next.Y] > d[cur.Y] + next.X) {
        d[next.Y] = d[cur.Y] + next.X;
        pq.push({d[next.Y], next.Y});
      }
    }
  }
}

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);
  int v, e, st;

  cin >> v >> e >> st;

  for (int i = 0; i < e; i++) {
    int u, v, w;
    cin >> u >> v >> w;

    adj[u].push_back({w, v});
  }

  djik(v, st);

  for (int i = 1; i <= v; i++) {
    if (d[i] == INF)
      cout << "INF\n";
    else
      cout << d[i] << '\n';
  }

  return 0;
}
