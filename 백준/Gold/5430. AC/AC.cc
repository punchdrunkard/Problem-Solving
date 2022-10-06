#include <bits/stdc++.h>

using namespace std;

int main(void) {
  ios::sync_with_stdio(0);
  cin.tie(0);

  int T;
  cin >> T;

  while (T--) {
    string operations;
    cin >> operations;
    int n;
    cin >> n;

    string array;
    cin >> array;

    deque<string> deq;
    string currentNumber = "";

    for (auto c : array) {
      if (c == '[') continue;
      if (c == ',' || c == ']') {
        if (currentNumber == "") continue;
        deq.push_back(currentNumber);
        currentNumber = "";

      } else {
        currentNumber += c;
      }
    }

    // 연산
    bool isReversed = false;
    bool isError = false;

    for (auto operation : operations) {
      switch (operation) {
        case 'R':

          isReversed = !isReversed;
          break;
        case 'D':

          if (deq.size() == 0) {
            isError = true;
            break;
          }

          !isReversed ? deq.pop_front() : deq.pop_back();
          break;
      }
    }

    if (isError) {
      cout << "error" << '\n';
      continue;
    }

    // 출력
    cout << '[';
    if (!isReversed) {
      for (int i = 0; i < deq.size(); i++) {
        if (i != deq.size() - 1)
          cout << deq[i] << ',';
        else
          cout << deq[i];
      }
    } else {
      for (int i = deq.size() - 1; i >= 0; i--) {
        if (i != 0)
          cout << deq[i] << ',';
        else
          cout << deq[i];
      }
    }
    cout << ']' << '\n';
  }
}