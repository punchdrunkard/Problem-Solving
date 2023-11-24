#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

#define cost first
#define en second

// n = 2000 이므로, 가능한 최대의 최단거리는 2000 * 1000 = 2,000,000 이므로
// INF 값을 설정할 때 1e6으로 하면 안됨
#define INF 1e9

using namespace std;

typedef pair<int, int> pii;

int n, m, t;              // 교차로, 도로, 목적지 후보의 갯수
int s, g, h;              // 출발지, 반드시 지나야하는 도로
vector<vector<pii>> adj;  // 그래프
vector<int> targets;      // 목적지 후보

int g_to_h;  //  g와 h 사이의 도로를 나타낸 것이 존재한다.

void init() {
  adj.clear();
  targets.clear();

  cin >> n >> m >> t >> s >> g >> h;

  adj.resize(n + 1);
  targets.resize(t);

  for (int i = 0; i < m; i++) {
    int a, b, d;  // a와 b 사이에 길이가 d인 "양방향" 도로가 있다.
    cin >> a >> b >> d;

    adj[a].push_back({d, b});
    adj[b].push_back({d, a});

    if ((a == g && b == h) || (a == h && b == g)) {
      g_to_h = d;
    }
  }

  // 목적지 후보
  for (int i = 0; i < t; i++) {
    cin >> targets[i];
  }
}

// st -> 다른 정점으로 가는 최단거리를 구하는 다익스트라
vector<int> dijkstra(int st) {
  priority_queue<pii, vector<pii>, greater<pii>> pq;
  vector<int> dist(n + 1, INF);

  pq.push({0, st});
  dist[st] = 0;

  while (!pq.empty()) {
    pii now = pq.top();
    pq.pop();

    if (now.cost > dist[now.en]) {
      continue;
    }

    for (auto edge : adj[now.en]) {
      int nextEn = edge.en;
      int nextCost = dist[now.en] + edge.cost;

      if (nextCost < dist[nextEn]) {
        dist[nextEn] = nextCost;
        pq.push({nextCost, nextEn});
      }
    }
  }

  return dist;
}

// s 에서 target으로 간 최단 거리가
// g-h 를 거친 최단거리와 같아야 한다.
vector<int> solve() {
  vector<int> answers;

  vector<int> s_dist = dijkstra(s);
  vector<int> g_dist = dijkstra(g);
  vector<int> h_dist = dijkstra(h);

  int s_to_g = s_dist[g];
  int s_to_h = s_dist[h];

  for (int target : targets) {
    // 현재 최단 거리
    int s_to_target = s_dist[target];

    // s - g - h- target 또는 s - h - g - target
    int h_to_target = h_dist[target];
    int g_to_target = g_dist[target];

    if (s_to_g == INF || s_to_h == INF || s_to_target == INF ||
        h_to_target == INF || g_to_target == INF) {
      continue;
    }

    int s_g_h_target = s_to_g + g_to_h + h_to_target;
    int s_h_g_target = s_to_h + g_to_h + g_to_target;

    int detour_dist = min(s_g_h_target, s_h_g_target);

    if (s_to_target == detour_dist) {
      answers.push_back(target);
    }
  }

  sort(answers.begin(), answers.end());
  return answers;
}

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;  // 테스트 케이스
  cin >> test_case;

  for (int tc = 0; tc < test_case; tc++) {
    init();
    auto answers = solve();

    for (auto answer : answers) {
      cout << answer << ' ';
    }
    cout << "\n";
  }

  return 0;
}
