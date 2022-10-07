#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  string brackets;
  cin >> brackets;

  stack<pair<char, int>> stk;
  vector<int> razors;
  int count = 0;

  for (int i = 0; i < brackets.size(); i++) {
    if (brackets[i] == '(') {
      // 레이저가 아닌 경우 (막대기인 경우)
      stk.push({brackets[i], i});
    } else if (brackets[i] == ')') {
      pair<char, int> stick = stk.top();

      // 레이저인 경우
      if (i - stick.second == 1) {
        stk.pop();
        razors.push_back(stick.second);
        continue;
      }

      int razorCount = 0;

      // 막대기의 구간 안에 레이저가 있는지 체크
      for (int j = 0; j < razors.size(); j++) {
        // 레이저가 현재 구간에 있을 때
        if (stick.second < razors[j] && razors[j] < i) {
          razorCount++;
        }
      }

      count += (razorCount + 1);
      stk.pop();
    }
  }

  cout << count;
}
