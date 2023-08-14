#include <bits/stdc++.h>
#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;
typedef pair<int, int> pii;

struct Node {
  int cost;
  int X;
  int Y;
};

struct Compare {
  bool operator()(const Node &a, const Node &b) const {
    return a.cost > b.cost;
  }
};

const int DX[4] = {1, -1, 0, 0};
const int DY[4] = {0, 0, 1, -1};

const int INF = 1e9;

bool isValid(int x, int y, int n) { return 0 <= x && x < n && 0 <= y && y < n; }

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int test_case;
  cin >> test_case;

  for (int tc = 1; tc <= test_case; tc++) {
    int n;  // 지도의 크기
    cin >> n;

    vector<vector<int>> graph;

    for (int i = 0; i < n; i++) {
      string temp;
      cin >> temp;

      vector<int> row;

      for (auto &c : temp) {
        row.push_back(int(c - '0'));
      }

      graph.push_back(row);
    }

    // 가중치가 있는 그래프에서 시작점에서 끝점까지
    // 즉 graph[0][0] 에서 graph[n -1][n - 1]까지의 최단경로를 구한다.

    vector<vector<int>> dist(n, vector<int>(n, INF));

    // 시작점의 dist는 0이다.
    dist[0][0] = 0;

    // 우선 순위 큐(최소 힙)에 시작 점을 넣는다.
    priority_queue<Node, vector<Node>, Compare> pq;
    pq.push({dist[0][0], 0, 0});  // {cost, x, y}

    while (!pq.empty()) {
      auto current = pq.top();
      pq.pop();

      // 현재 점이 valid 한 점인가?
      if (current.cost != dist[current.X][current.Y]) continue;

      // 목적지에 도달한 경우
      if (current.X == n - 1 && current.Y == n - 1) break;

      // 현재 정점에서 4방향 탐색
      for (int dir = 0; dir < 4; dir++) {
        pii next = {current.X + DX[dir], current.Y + DY[dir]};
        // 탐색하고자 하는 점이 올바른가?
        if (!isValid(next.first, next.second, n)) continue;

        // 경로를 업데이트 할 수 있는가?
        if (graph[next.first][next.second] + dist[current.X][current.Y] <
            dist[next.first][next.second]) {
          dist[next.first][next.second] =
              graph[next.first][next.second] + dist[current.X][current.Y];

          // 거리를 갱신할 떄 마다 우선순위 큐에 넣는다.
          pq.push({dist[next.first][next.second], next.first, next.second});
        }
      }
    }

    cout << "#" << tc << " " << dist[n - 1][n - 1] << "\n";
  }

  return 0;
}
