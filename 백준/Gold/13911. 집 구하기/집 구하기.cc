#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

#define cost first
#define node second

int v, e;  // 정점의 갯수, 도로의 갯수
int m, x;  // 맥도날드 갯수, 맥세권 조건
int s, y;  // 스타벅스 갯수, 스세권 조건

vector<vector<pair<int, int>>> adj;
vector<bool> store;  // 맥도날드 or 스타벅스가 위치한 곳

void init() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> v >> e;
  adj.resize(v + 2);  // 더미노드를 위한 두 자리
  store.resize(v + 2);
  store[0] = true;
  store[v + 1] = true;

  for (int i = 0; i < e; i++) {
    int st, en, w;  // st와 en 사이에 가중치가 w인 도로가 존재한다.
    cin >> st >> en >> w;

    adj[st].push_back({w, en});
    adj[en].push_back({w, st});
  }

  cin >> m >> x;

  // 맥도날드 정점 번호
  for (int i = 0; i < m; i++) {
    int m_node;
    cin >> m_node;

    store[m_node] = true;

    // 해당 정점 번호와 거리가 0인 더미노드를 이어준다.
    adj[0].push_back({0, m_node});
    adj[m_node].push_back({0, 0});
  }

  cin >> s >> y;

  // 스타벅스 정점 번호
  for (int i = 0; i < s; i++) {
    int s_node;
    cin >> s_node;

    store[s_node] = true;

    // 해당 정점 번호와 거리가 0인 더미노드를 이어준다.
    adj[v + 1].push_back({0, s_node});
    adj[s_node].push_back({0, v + 1});
  }
}

void dijkstra(vector<int> &dist, int start) {
  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      pq;  // 최소힙

  dist[start] = 0;
  pq.push({dist[start], start});

  while (!pq.empty()) {
    auto current = pq.top();
    pq.pop();

    if (current.cost != dist[current.node]) {
      continue;
    }

    for (auto &next : adj[current.node]) {
      // 더미노드인 경우 넣지 않는다.
      if (next.node == 0 || next.node == v + 1) {
        continue;
      }

      if (current.cost + next.cost < dist[next.node]) {
        dist[next.node] = current.cost + next.cost;
        pq.push({dist[next.node], next.node});
      }
    }
  }
}

int solve() {
  vector<int> m_dist(v + 2, INT_MAX), s_dist(v + 2, INT_MAX);
  dijkstra(m_dist, 0);
  dijkstra(s_dist, v + 1);

  int answer = INT_MAX;

  for (int i = 1; i <= v; i++) {
    // 맥도날드나 스타벅스가 위치한 정점에는 집이 없다.
    if (store[i]) continue;

    if (m_dist[i] <= x && s_dist[i] <= y) {
      answer = min(answer, m_dist[i] + s_dist[i]);
    }
  }

  // 만일 원하는 집이 존재하지 않으면 -1을 출력한다.
  if (answer == INT_MAX) answer = -1;

  return answer;
}

int main() {
  init();
  int answer = solve();
  cout << answer;

  return 0;
}
