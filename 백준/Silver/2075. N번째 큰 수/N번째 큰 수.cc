#include <bits/stdc++.h>
using namespace std;

// 힙을 이용하여 N번 째 큰 수 찾기
// 크기가 n인 최소 힙을 만들고 원소들을 차례대로 넣고
// 크기가 n이 넘는 순간 위에서 부터 원소들을 뺀다면
// 그 전에 있던 작은 원소들은 모두 빠져나가고
// 마지막에는 n번째 큰 수가 제일 위에 있는 구조가 된다.

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  // 최소 힙
  priority_queue<int, vector<int>, greater<int>> pq;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      int value;
      cin >> value;
      pq.push(value);

      if (pq.size() > n) {
        pq.pop();
      }
    }
  }

  cout << pq.top();

  return 0;
}
