#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define cost first
#define node second

#define INF 1e9
#define pii pair<int, int>

using namespace std;

int n, m;  // n : 관게의 수 (edge), m : 정치인의 수 (vertex)
vector<vector<pair<int, int>>> adj;  // 인접 리스트

void dijkstra(vector<int> &dist, vector<int> &route) {
  // 최소 힙 선언, {cost, node_index}
  priority_queue<pii, vector<pii>, greater<pii>> pq;
  pq.push({0, 0});
  dist[0] = 0;

  while (!pq.empty()) {
    auto current = pq.top();
    pq.pop();

    if (current.cost != dist[current.node]) {
      continue;
    }

    if (current.node == m - 1) break;

    for (auto &next : adj[current.node]) {
      if (dist[next.node] > current.cost + next.cost) {
        // 거리 업데이트
        dist[next.node] = dist[current.node] + next.cost;
        // 경로 업데이트
        route[next.node] = current.node;

        // 다음 점을 우선 순위 큐에 넣음
        pq.push({dist[next.node], next.node});
      }
    }
  }
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int tc = 1; tc <= test_case; tc++) {
    cin >> n >> m;

    adj.clear();
    adj.resize(m);

    for (int i = 0; i < n; i++) {
      int x, y, z;  // 정치인, 친구, 친밀도
      cin >> x >> y >> z;

      adj[x].push_back({z, y});
      adj[y].push_back({z, x});
    }

    vector<int> dist(m, INF), route(m, -1);  // 거리, 경로
    dijkstra(dist, route);

    cout << "Case #" << tc << ": ";

    if (dist[m - 1] == INF) {
      cout << -1 << '\n';
    } else {
      // 만난 순서대로 출력한다. => 경로를 복구하면서 출력한다.
      stack<int> stk;
      vector<int> answer;

      stk.push(m - 1);  // 끝 점

      int current = route[m - 1];

      while (current != 0) {
        stk.push(current);
        current = route[current];
      }

      stk.push(current);  // 첫 점

      // 스택에서 하나씩 빼면서 출력한다.
      while (!stk.empty()) {
        answer.push_back(stk.top());
        stk.pop();
      }

      for (auto &a : answer) {
        cout << a << ' ';
      }

      cout << '\n';
    }
  }

  return 0;
}
