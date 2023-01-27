#include <bits/stdc++.h>
using namespace std;

int main() {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  priority_queue<int> maxHeap;
  priority_queue<int, vector<int>, greater<int>> minHeap;

  for (int i = 0; i < n; i++) {
    int number;
    cin >> number;

    if (maxHeap.size() <= minHeap.size()) {
      maxHeap.push(number);
    } else {
      minHeap.push(number);
    }

    if (!minHeap.empty() && minHeap.top() < maxHeap.top()) {
      int minTop = minHeap.top();
      minHeap.pop();
      int maxTop = maxHeap.top();
      maxHeap.pop();

      minHeap.push(maxTop);
      maxHeap.push(minTop);
    }

    cout << maxHeap.top() << '\n';
  }

  return 0;
}
