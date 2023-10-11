#include <bits/stdc++.h>
using namespace std;

vector<int> answer;
int n, k;

void solve() {
  queue<int> q;

  for (int i = 1; i <= n; i++) {
    q.push(i);
  }

  int count = 0;

  while (!q.empty()) {
    int current = q.front();
    q.pop();
    count++;

    if (count == k) {
      answer.push_back(current);
      count = 0;
    } else {
      q.push(current);
    }
  }
}

void print() {
  cout << "<";

  for (int i = 0; i < n; i++) {
    if (i == n - 1) {
      cout << answer[i];
    } else {
      cout << answer[i] << ", ";
    }
  }

  cout << ">";
}

int main() {
  cin >> n >> k;

  solve();
  print();

  return 0;
}
