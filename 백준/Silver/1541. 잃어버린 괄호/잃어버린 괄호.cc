#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

string expression;
vector<int> plusNumber, minusNumber;

int main() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> expression;

  int result = 0;
  bool isMinus = false;

  string temp = "";

  for (char c : expression) {
    if (c == '-' || c == '+') {
      if (isMinus) {
        result -= stoi(temp);
        temp = "";
      } else {
        result += stoi(temp);
        temp = "";
      }
    } else {  // 숫자인 경우
      temp += c;
    }

    if (c == '-') {
      isMinus = true;
    }
  }

  // 마지막 숫자에 대한 처리
  if (isMinus) {
    result -= stoi(temp);

  } else {
    result += stoi(temp);
  }

  cout << result;

  return 0;
}
