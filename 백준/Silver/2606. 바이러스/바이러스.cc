#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int v, e;
vector<vector<int>> graph;

void input() {
  FASTIO;

  cin >> v >> e;
  graph.resize(v + 1);

  for (int i = 0; i < e; i++) {
    int v1, v2;
    cin >> v1 >> v2;
    graph[v1].push_back(v2);
    graph[v2].push_back(v1);
  }
}

int bfs(int st) {
  int count = 0;

  queue<int> q;
  vector<bool> visited(v + 1, false);

  q.push(st);
  visited[st] = true;

  while (!q.empty()) {
    int current = q.front();
    q.pop();

    for (auto v : graph[current]) {
      if (visited[v]) continue;

      q.push(v);
      visited[v] = true;
      count++;
    }
  }

  return count;
}

int main() {
  input();

  int answer = bfs(1);
  cout << answer;

  return 0;
}
