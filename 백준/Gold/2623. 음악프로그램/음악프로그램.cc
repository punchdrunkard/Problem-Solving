#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;                 // 가수의 수, 보조 pd의 수
vector<vector<int>> adj;  // 인접 리스트
vector<int> inDegree;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m;

  // 1-based
  adj.resize(n + 1);
  inDegree.resize(n + 1, 0);

  for (int i = 0; i < m; i++) {
    int cnt;
    cin >> cnt;

    vector<int> temp(cnt);

    for (int j = 0; j < cnt; j++) {
      cin >> temp[j];
    }

    for (int j = 1; j < cnt; j++) {
      adj[temp[j - 1]].push_back(temp[j]);
      inDegree[temp[j]]++;
    }
  }
}

// 불가능하다면 0을 출력 => 큐가 비어있지 않음s
void topologySort() {
  queue<int> q;
  vector<int> singers;

  // inDegree가 0인 정점을 먼저 큐에 넣자.
  for (int i = 1; i <= n; i++) {
    if (inDegree[i] == 0) {
      q.push(i);
    }
  }

  // 큐가 빌 때 까지 반복
  while (!q.empty()) {
    // inDegree가 0인 정점을 꺼냄
    int current = q.front();
    q.pop();
    singers.push_back(current);

    // 걔랑 연결되 어있는 애들의 inDegree를 지워주자
    for (int edge = 0; edge < adj[current].size(); edge++) {
      inDegree[adj[current][edge]]--;

      if (inDegree[adj[current][edge]] == 0) {
        q.push(adj[current][edge]);
      }
    }
  }

  // cycle이 생긴 경우 (cycle이 포함된 정점은 indegree가 0이 될 수 없으므로 위상
  // 정렬 결과에 모든 정점이 포함되어 있지 않다.)
  if (singers.size() != n) {
    cout << 0;
    return;
  }

  for (auto singer : singers) {
    cout << singer << '\n';
  }
}

int main() {
  input();
  topologySort();

  return 0;
}
