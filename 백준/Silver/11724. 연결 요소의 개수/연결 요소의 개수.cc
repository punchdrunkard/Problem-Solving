#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;

vector<vector<int>> adj;
vector<bool> visited;

void input() {
  FASTIO;

  cin >> n >> m;
  adj.resize(n + 1);
  visited.resize(n + 1);

  for (int i = 0; i < m; i++) {
    int u, v;
    cin >> u >> v;
    adj[u].push_back(v);
    adj[v].push_back(u);
  }
}

void bfs(int st) {
  queue<int> q;
  q.push(st);
  visited[st] = true;

  while (!q.empty()) {
    auto current = q.front();
    q.pop();

    for (auto v : adj[current]) {
      if (visited[v]) continue;
      q.push(v);
      visited[v] = true;
    }
  }
}

int solve() {
  int answer = 0;

  for (int i = 1; i <= n; i++) {
    if (visited[i]) continue;
    bfs(i);
    answer++;
  }

  return answer;
}

int main() {
  input();
  cout << solve();

  return 0;
}
