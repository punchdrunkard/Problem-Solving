#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

int n, m;
vector<vector<int>> adj;
vector<int> inDegree;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);
  cin >> n >> m;

  // 1-indexed
  adj.resize(n + 1);
  inDegree.resize(n + 1, 0);

  for (int i = 0; i < m; i++) {
    int a, b;  // a번 문제는 b번 문제보다 먼저 푸는 것이 좋다.
    cin >> a >> b;

    adj[a].push_back(b);
    inDegree[b]++;
  }
}

// 난이도가 쉬운 문제 부터 풀어야 한다?
// 숫자가 작은게 큐에 먼저 와야함 -> 우선 순위 큐
vector<int> topologySort() {
  vector<int> result;

  priority_queue<int, vector<int>, greater<int>> q;  // 최소힙

  // 1. inDegree가 0인 정점부터 큐에 넣는다.
  for (int i = 1; i <= n; i++) {
    if (inDegree[i] == 0) {
      q.push(i);
    }
  }

  while (!q.empty()) {
    int current = q.top();
    q.pop();
    result.push_back(current);

    for (int edge = 0; edge < adj[current].size(); edge++) {
      int nowEndV = adj[current][edge];
      inDegree[nowEndV]--;

      if (inDegree[nowEndV] == 0) {
        q.push(nowEndV);
      }
    }
  }

  return result;
}

int main() {
  input();
  vector<int> answer = topologySort();

  for (auto ans : answer) {
    cout << ans << ' ';
  }

  return 0;
}
