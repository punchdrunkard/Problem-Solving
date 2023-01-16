// 일부 학생들의 키를 비교한 결과가 유지되면서, 학생들의 줄을 세워야 한다.

#include <bits/stdc++.h>

using namespace std;

#define MAX 32001

vector<int> adj[MAX];
int inDegree[MAX];
int n, m;

vector<int> topologySort() {
  vector<int> result;
  queue<int> q;

  for (int i = 1; i <= n; i++) {
    if (inDegree[i] == 0) {
      q.push(i);
    }
  }

  while (!q.empty()) {
    int front = q.front();
    q.pop();

    result.push_back(front);

    for (int edge = 0; edge < adj[front].size(); edge++) {
      int current = adj[front][edge];
      inDegree[current] -= 1;

      if (inDegree[current] == 0) {
        q.push(current);
      }
    }
  }

  return result;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cin >> n >> m;

  for (int i = 1; i <= m; i++) {
    int a, b;
    cin >> a >> b;

    adj[a].push_back(b);
    inDegree[b] += 1;
  }

  vector<int> result = topologySort();

  for (auto v : result) {
    cout << v << ' ';
  }

  cout << '\n';

  return 0;
}
