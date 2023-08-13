#include <functional>
#include <iostream>
#include <queue>
#include <utility>
#include <vector>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

#define weight first
#define node second

const int INF = 1e9;

// 총 몇 대의 컴퓨터가 감염되는지?
// 마지막 컴퓨터가 감염되는데 걸리는 시간?

// 1. 역방향 그래프를 만든다.
// 2. c에서부터 다익스트라를 돌린다.
// 3. 돌린 후 최단 거리 배열을 체크해서, 총 컴퓨터 수와 마지막 컴퓨터가 감염되는
// 시간을 계산한다.

void makeGraph(vector<vector<pair<int, int>>>& graph, int edges) {
  for (int i = 0; i < edges; i++) {
    int a, b, s;
    cin >> a >> b >> s;
    graph[b].push_back({s, a});
  }
}

void dijkstra(int start, vector<int>& dist,
              vector<vector<pair<int, int>>>& adj) {
  // 정점 c에서 부터 다익스트라 알고리즘 적용
  priority_queue<pair<int, int>, vector<pair<int, int>>,
                 greater<pair<int, int>>>
      pq;  // 최소 힙

  // 시작점의 거리
  dist[start] = 0;
  pq.push({dist[start], start});

  while (!pq.empty()) {
    auto cur = pq.top();
    pq.pop();

    // 우선순위 큐에 있는 값이 올바른 값인지 검사
    if (dist[cur.node] != cur.weight) continue;

    for (const auto& next : adj[cur.node]) {
      if (dist[cur.node] + next.weight < dist[next.node]) {
        dist[next.node] = dist[cur.node] + next.weight;
        pq.push({dist[next.node], next.node});
      }
    }
  }
}

// dist 배열을 검사하면서 답을 구한다.
pair<int, int> findAnswer(vector<int>& dist, int n) {
  int total_count = 0, max_time = 0;

  for (int i = 1; i <= n; i++) {
    if (dist[i] != INF) {
      total_count += 1;
      max_time = max(max_time, dist[i]);
    }
  }

  return {total_count, max_time};
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  freopen("sample_input.txt", "r", stdin);

  int test_case;  // 테스트 케이스의 수
  cin >> test_case;

  for (int tc = 1; tc <= test_case; tc++) {
    int n, d, c;  // 컴퓨터 개수, 의존성 개수, 해킹당한 컴퓨터의 번호
    cin >> n >> d >> c;

    vector<vector<pair<int, int>>> adj(n + 1);  // 가중치, 이웃한 정점 번호
    makeGraph(adj, d);

    vector<int> dist(n + 1, INF);  // 거리 배열
    dijkstra(c, dist, adj);

    auto answer = findAnswer(dist, n);

    cout << answer.first << ' ' << answer.second << '\n';
  }

  return 0;
}
