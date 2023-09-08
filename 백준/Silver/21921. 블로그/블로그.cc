#include <bits/stdc++.h>

#define FASTIO ios_base::sync_with_stdio(0), cin.tie(0), cout.tie(0)

typedef long long ll;
using namespace std;

int n, x;  // 블로그를 시작한지 n일, x일 동안 가장 많이 들어온 방문자 수
vector<int> visitors;

void input() {
  FASTIO;

  // 테스트를 위한 파일 입력 코드 (TODO: 제출 전 삭제)
  // freopen("sample_input.txt", "r", stdin);

  cin >> n >> x;
  visitors.resize(n);

  for (int i = 0; i < n; i++) {
    cin >> visitors[i];
  }
}

int main() {
  input();

  // 만약 최대 방문자 수가 0명이라면 SAD를 출력
  // 매번 고정된 x개의 크기의 기간을 봐야 함 -> sliding window
  // O(N)으로 배열을 한 번만 훑으면서 방문자수를 계산해보자.

  ll temp = 0;

  // init: 첫 번째 기간
  for (int i = 0; i < x; i++) {
    temp += visitors[i];
  }

  ll answer = temp;
  int count = 1;

  for (int i = 1; i <= n - x; i++) {
    temp -= visitors[i - 1];
    temp += visitors[i + x - 1];

    if (temp > answer) {
      count = 1;
      answer = temp;
    } else if (temp == answer) {
      count++;
    }
  }

  if (answer == 0) {
    cout << "SAD";
  } else {
    cout << answer << "\n" << count;
  }

  return 0;
}
