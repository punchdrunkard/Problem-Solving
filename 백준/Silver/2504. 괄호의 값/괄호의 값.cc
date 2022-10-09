#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  string brackets;
  cin >> brackets;

  stack<char> stk;
  int multiple = 1, answer = 0;

  for (int i = 0; i < brackets.size(); i++) {
    if (brackets[i] == '(') {
      stk.push(brackets[i]);
      multiple *= 2;
    } else if (brackets[i] == '[') {
      stk.push(brackets[i]);
      multiple *= 3;
    } else if (brackets[i] == ')') {
      // 종료 조건 : 괄호 쌍이 안맞음
      if (stk.empty() || stk.top() == '[') {
        cout << 0;
        return 0;
      }
      // 그 전의 원소와 자신이 같으면 -> 값이 되어 사라짐.
      stk.pop();
      if (brackets[i - 1] == '(') {
        answer += multiple;
      }
      multiple /= 2;
    } else if (brackets[i] == ']') {
      if (stk.empty() || stk.top() == '(') {
        cout << 0;
        return 0;
      }

      stk.pop();

      if (brackets[i - 1] == '[') {
        answer += multiple;
      }
      multiple /= 3;
    }
  }

  if (!stk.empty()) answer = 0;
  cout << answer;
}
