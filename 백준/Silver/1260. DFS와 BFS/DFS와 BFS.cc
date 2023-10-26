#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m, st;  // 정점의 개수, 간선의 개수, 탐색을 시작할 정점의 번호
vector<vector<int>> adj;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> m >> st;
  adj.resize(n + 1);

  for (int i = 0; i < m; i++) {
    int v1, v2;
    cin >> v1 >> v2;

    adj[v1].push_back(v2);
    adj[v2].push_back(v1);
  }

  // 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문
  for (int i = 1; i <= n; i++) {
    sort(adj[i].begin(), adj[i].end());
  }
}

vector<bool> dfs_visited, bfs_visited;

void dfs(int st) {
  dfs_visited[st] = true;
  cout << st << " ";

  for (auto v : adj[st]) {
    if (dfs_visited[v]) continue;
    dfs(v);
  }
}

void bfs(int st) {
  queue<int> q;
  q.push(st);
  bfs_visited[st] = true;

  while (!q.empty()) {
    int current = q.front();
    q.pop();
    cout << current << ' ';

    for (auto v : adj[current]) {
      if (bfs_visited[v]) continue;
      q.push(v);
      bfs_visited[v] = true;
    }
  }
}

void solve() {
  dfs_visited.resize(n + 1, false);
  bfs_visited.resize(n + 1, false);

  dfs(st);
  cout << '\n';
  bfs(st);
}

int main() {
  input();
  solve();

  return 0;
}
