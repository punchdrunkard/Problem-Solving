#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int n;
  cin >> n;

  stack<int> stk;

  while (n--) {
    int k;
    cin >> k;
    if (k == 0) {
      stk.pop();
    } else {
      stk.push(k);
    }
  }

  int answer = 0;

  if (stk.size() != 0) {
    while (stk.size()) {
      answer += stk.top();
      stk.pop();
    }
  }

  cout << answer;
}