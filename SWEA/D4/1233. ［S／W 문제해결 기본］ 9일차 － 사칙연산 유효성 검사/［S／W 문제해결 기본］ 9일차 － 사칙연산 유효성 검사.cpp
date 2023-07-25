#include <bits/stdc++.h>

using namespace std;

bool isOper(string c) {
  if (c == "+" || c == "-" || c == "*" || c == "/") {
    return true;
  }

  return false;
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);
  cout.tie(0);

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("input.txt", "r", stdin);

  for (int i = 1; i <= 10; i++) {
    int n;  // 총 정점의 수
    cin >> n;
    cin.ignore();

    bool flag = true;

    for (int i = 0; i < n; i++) {
      string line;
      getline(cin, line);  // 한 줄을 입력 받는다.

      istringstream iss(line);
      vector<string> tokens;
      string token;

      while (getline(iss, token, ' ')) {
        tokens.push_back(token);
      }

      // 연산자 노드인 경우 : 자식이 두 개
      if (isOper(tokens[1])) {
        if (tokens.size() != 4) {
          flag = false;
          continue;
        }
      } else {  // 숫자 노드인 경우 : 자식이 없어야 함
        if (tokens.size() != 2) {
          flag = false;
          continue;
        }
      }
    }

    cout << "#" << i << " " << flag << '\n';
  }

  return 0;
}
