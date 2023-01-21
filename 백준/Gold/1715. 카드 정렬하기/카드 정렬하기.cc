#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  priority_queue<int, vector<int>, greater<int>> pq;
  int count = 0;

  for (int i = 0; i < n; i++) {
    int cards;
    cin >> cards;
    pq.push(cards);
  }

  while (pq.size() > 1) {
    int min1 = pq.top();
    pq.pop();
    int min2 = pq.top();
    pq.pop();

    count += (min1 + min2);
    pq.push(min1 + min2);
  }

  cout << count;

  return 0;
}
