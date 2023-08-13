#include <bits/stdc++.h>
#define fastio ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

// 루피
// 도둑 루피 : 소지한 루피가 감소하게 된다!

const int DX[4] = {-1, 1, 0, 0};
const int DY[4] = {0, 0, -1, 1};
const int INF = 1e9;

struct Node {
  int weight;
  int X;
  int Y;
};

struct Compare {
  bool operator()(const Node &a, const Node &b) const {
    return a.weight > b.weight;
  }
};

int main() {
  fastio;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  int n;
  cin >> n;

  int tc = 0;

  // 종료
  while (n != 0) {
    tc += 1;

    // 그래프 입력 받기
    vector<vector<int>> cave(n, vector<int>(n, 0));

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        cin >> cave[i][j];
      }
    }

    //  다익스트라 알고리즘
    priority_queue<Node, vector<Node>, Compare> pq;    // 최소 힙
    vector<vector<int>> dist(n, vector<int>(n, INF));  // 최단거리 테이블

    // 시작 점의 거리
    dist[0][0] = cave[0][0];

    // 우선 순위 큐에 시작점을 넣는다.
    pq.push({dist[0][0], 0, 0});

    while (!pq.empty()) {
      auto cur = pq.top();
      pq.pop();

      // 우선 순위 큐에 있는 점이 올바른 값인지 확인
      if (dist[cur.X][cur.Y] != cur.weight) continue;

      // 올바르다면 현재 정점에서 갈 수 있는 거리와 테이블의 최단 거리를 비교
      for (int dir = 0; dir < 4; dir++) {
        pair<int, int> next = {cur.X + DX[dir], cur.Y + DY[dir]};

        // 올바른 범위의 값인가?
        if (next.first < 0 || next.second < 0 || next.first >= n ||
            next.second >= n)
          continue;

        // 거리를 갱신할 수 있는가?
        if (cur.weight + cave[next.first][next.second] <
            dist[next.first][next.second]) {
          dist[next.first][next.second] =
              cur.weight + cave[next.first][next.second];
          pq.push({dist[next.first][next.second], next.first, next.second});
        }
      }
    }

    cout << "Problem " << tc << ": " << dist[n - 1][n - 1] << '\n';

    cin >> n;
  }

  return 0;
}

