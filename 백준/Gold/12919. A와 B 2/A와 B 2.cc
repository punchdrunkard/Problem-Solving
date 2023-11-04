#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

using namespace std;

string s, t;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> s >> t;
}

bool answer = false;

// TODO: 두 문자열 S와 T가 주어졌을 때, S를 T로 바꾸기
// s에 추가하지 말고 t에서 제거하는 방법으로 구현해보세요
void dfs() {
  // 크기가 같을 때 검사
  if (s.size() == t.size()) {
    if (s == t) {
      answer = true;
    }

    return;
  }

  // 연산 1의 경우
  string original = t;

  // 연산 1. 문자열 뒤에 a를 추가한다.
  // => t에 맨 뒤에있는 문자열 a를 뺀다.
  if (t[t.size() - 1] == 'A') {
    t.pop_back();
    dfs();
    t = original;
  }

  // 연산 2. 문자열 뒤에 b를 추가하고, 문자열을 뒤집는다.
  if (t[0] == 'B') {
    t = t.substr(1, t.length() - 1);
    reverse(t.begin(), t.end());
    dfs();
    t = original;
  }
}

int main() {
  input();
  dfs();

  cout << answer;

  return 0;
}
